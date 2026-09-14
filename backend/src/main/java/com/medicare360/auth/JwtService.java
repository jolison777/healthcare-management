package com.medicare360.auth;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Service public class JwtService {
 private static final String SECRET="MediCare360-JWT-2026-Development-Secret";
 public String generate(User u){String h=enc("{\"alg\":\"HS256\",\"typ\":\"JWT\"}");String p=enc("{\"sub\":\""+u.getEmail()+"\",\"role\":\""+u.getRole()+"\",\"userId\":"+u.getId()+",\"exp\":"+(System.currentTimeMillis()/1000+86400)+"}");return h+"."+p+"."+sign(h+"."+p);}
 public String email(String token){String[] a=token.split("\\.");if(a.length!=3||!sign(a[0]+"."+a[1]).equals(a[2]))throw new IllegalArgumentException("Invalid token");String p=new String(Base64.getUrlDecoder().decode(a[1]),StandardCharsets.UTF_8);int s=p.indexOf("\"sub\":\"")+7,e=p.indexOf("\"",s);return p.substring(s,e);}
 private String enc(String s){return Base64.getUrlEncoder().withoutPadding().encodeToString(s.getBytes(StandardCharsets.UTF_8));}
 private String sign(String value){try{Mac m=Mac.getInstance("HmacSHA256");m.init(new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8),"HmacSHA256"));return Base64.getUrlEncoder().withoutPadding().encodeToString(m.doFinal(value.getBytes(StandardCharsets.UTF_8)));}catch(Exception e){throw new IllegalStateException(e);}}
}
