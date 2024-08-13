package org.example.projectboard1.config;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.context.annotation.Import;

@Import(SecurityConfig.class)
public class TestSecurityConfig {
}
