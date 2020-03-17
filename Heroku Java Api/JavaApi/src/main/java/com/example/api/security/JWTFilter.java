package com.example.api.security;

import com.example.api.model.User;
import com.example.api.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.example.api.security.SecurityConstants.HEADER_STRING;
import static com.example.api.security.SecurityConstants.TOKEN_PREFIX;

public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = getJwtFromRequest(httpServletRequest);
            if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)){
                Long userId = jwtProvider.getUserIdFromJwt(jwt);
                User userDetails = customUserDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, Collections.emptyList()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (Exception e){
            logger.error("Could not set user authentication in security context", e);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getJwtFromRequest(HttpServletRequest request){
        String bearer = request.getHeader(HEADER_STRING);
        if (StringUtils.hasText(bearer) && bearer.startsWith(TOKEN_PREFIX)){
            return bearer.substring(7, bearer.length());
        }
        return null;
    }
}
