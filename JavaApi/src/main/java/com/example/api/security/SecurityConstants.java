package com.example.api.security;

public class SecurityConstants {
    public static final String SIGN_UP_URLS = "/API/USERS/**";
    public static final String H2_URL = "h2-console/**";
    public static final String SECRET_KEY = "SecretKeyToGenJWTs";
    public static final String TOKEN_PReFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization ";
    public static final long EXPIRATION_TIME = 30_000;

}
