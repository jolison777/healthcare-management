package com.medicare360.auth;
import jakarta.servlet.*; import jakarta.servlet.http.*; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.authority.SimpleGrantedAuthority; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.web.filter.OncePerRequestFilter; import java.io.IOException; import java.util.List;
public class JwtFilter extends OncePerRequestFilter{
 private final JwtService jwt; private final UserRepository users; public JwtFilter(JwtService jwt,UserRepository users){this.jwt=jwt;this.users=users;}
 protected void doFilterInternal(HttpServletRequest r,HttpServletResponse s,FilterChain c)throws ServletException,IOException{String h=r.getHeader("Authorization");if(h!=null&&h.startsWith("Bearer "))try{String email=jwt.email(h.substring(7));users.findByEmail(email).ifPresent(u->{var a=new UsernamePasswordAuthenticationToken(u.getEmail(),null,List.of(new SimpleGrantedAuthority("ROLE_"+u.getRole())));SecurityContextHolder.getContext().setAuthentication(a);});}catch(Exception ignored){}c.doFilter(r,s);}
}
