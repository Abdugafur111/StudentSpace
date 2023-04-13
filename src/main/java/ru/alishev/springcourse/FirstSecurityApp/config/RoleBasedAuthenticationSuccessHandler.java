package ru.alishev.springcourse.FirstSecurityApp.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoleBasedAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final RequestMatcher adminMatcher;
    private final String adminTargetUrl;

    public RoleBasedAuthenticationSuccessHandler(String defaultTargetUrl, String adminTargetUrl) {
        super.setDefaultTargetUrl(defaultTargetUrl);
        this.adminMatcher = new RoleBasedRequestMatcher("ROLE_ADMIN");
        this.adminTargetUrl = adminTargetUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException, ServletException {
        if (adminMatcher.matches(request)) {
            getRedirectStrategy().sendRedirect(request, response, adminTargetUrl);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
