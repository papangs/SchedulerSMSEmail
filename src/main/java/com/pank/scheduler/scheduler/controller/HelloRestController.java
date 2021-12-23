package com.pank.scheduler.scheduler.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("jwt")
public class HelloRestController {

	private String secret = "papank";
	private long validity = 2 * 60 * 60 * 1000;
	
//	@Value("${jwt.secret}")
//	private String secret;
//	
//	@Value("${jwt.validity}")
//	private String validity;
	
	@RequestMapping(value = "/request", method = RequestMethod.POST)
	private Map<String, Object> generateTokenJWT(@RequestBody String body){
		long timestamp = System.currentTimeMillis();
		
		ObjectMapper mapper = new ObjectMapper();

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			HashMap<String, Object> has = mapper.readValue(body, HashMap.class);
			
			String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, secret)
					.setIssuedAt(new Date(timestamp))
					.setExpiration(new Date(timestamp + validity))
					.claim("userid", has.get("userId"))
					.claim("password", has.get("password"))
					.compact();
			
			map.put("token", token);
			return map;
			
		} catch (Exception e) {
			
			return map;
		}
		
	}
}