<%@ page import ="org.springframework.security.core.*,org.springframework.security.core.context.*" %>
<center>
<h1>Welcome to Cinema User Access Denied Error Page</h1>

<h2>You are in Cinema User Bad Credential Error page</h2>
<br><a href="/login">home</a>
<br><br>
<%
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			out.println("User '" + auth.getName() + "' attempted to access the protected URL: ");
			out.println("<br>auth : "+auth.isAuthenticated());
			out.println("<br>Role : "+auth.getAuthorities());
			out.println("<br>Error Page : "+request.getRequestURL());
		}
%>
</center>