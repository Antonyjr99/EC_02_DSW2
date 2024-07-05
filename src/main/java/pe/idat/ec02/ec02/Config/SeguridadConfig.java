package pe.idat.ec02.ec02.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import pe.idat.ec02.ec02.Model.Usuario;
import pe.idat.ec02.ec02.Service.IUsuarioService;

@Configuration
@EnableWebSecurity
public class SeguridadConfig {
    @Autowired
	private IUsuarioService service;
	
	@Value("${user.admin.correo}")
	private String admin_email;
	@Value("${user.admin.clave}")
	private String admin_clave;
	@Value("${user.admin.rol}")
	private String admin_rol;
	
	@Value("${user.user.correo}")
	private String user_email;
	@Value("${user.user.clave}")
	private String user_clave;
	@Value("${user.user.rol}")
	private String user_rol;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth->auth
				.requestMatchers("/hola/**","/prueba/**","/api/productos").permitAll()
				.requestMatchers("/api/productos/crear").hasRole("USER")
				.requestMatchers("/user/**").hasRole("USER")
				.requestMatchers("/profile/**").authenticated()
				)
		.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
    // Initialize the list of UserDetails
    List<UserDetails> lstUD = new ArrayList<>();

    // Predefined users
    /*
    lstUD.add(User.withUsername(admin_email)
                    .password(passwordEncoder().encode(admin_clave))
                    .authorities(admin_rol).build());
    lstUD.add(User.withUsername(user_email)
            .password(passwordEncoder().encode(user_clave))
            .authorities(user_rol).build());
    */

    // Fetch users from the database
    List<Usuario> lst = service.listarUsuario();
    for (Usuario usu : lst) {
        lstUD.add(User.withUsername(usu.getEmail())
                    .password(passwordEncoder().encode(usu.getClave()))
                    .authorities(usu.getRol()).build());
    }

    return new InMemoryUserDetailsManager(lstUD);
}


	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
