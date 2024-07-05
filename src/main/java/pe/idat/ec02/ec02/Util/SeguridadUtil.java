package pe.idat.ec02.ec02.Util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SeguridadUtil {

	
	public static String getCurrentUser() {
		SecurityContext sc= SecurityContextHolder.getContext();
		Authentication au=sc.getAuthentication();
		String _usuarioSession=null;
		if (au!=null && au.isAuthenticated())
			_usuarioSession=au.getName();
		return _usuarioSession;
	}
	
}
