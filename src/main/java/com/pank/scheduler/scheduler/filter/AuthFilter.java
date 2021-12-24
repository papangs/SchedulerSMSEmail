package com.pank.scheduler.scheduler.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthFilter extends GenericFilterBean {

	private String secret = "papank";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;

		String authheader = servletRequest.getHeader("Authorization");
		if (authheader != null) {
			String[] autHeaderArr = authheader.split("Bearer ");
			if (autHeaderArr.length > 1 && autHeaderArr[1] != null) {
				String token = autHeaderArr[1];

				try {
					Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
					servletRequest.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));
				} catch (Exception e) {
					servletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid / Expired Token");
					return;
				}

			} else {
				servletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer");
				return;
			}

		} else {

			servletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
			return;
		}

		chain.doFilter(servletRequest, servletResponse);

	}

}
