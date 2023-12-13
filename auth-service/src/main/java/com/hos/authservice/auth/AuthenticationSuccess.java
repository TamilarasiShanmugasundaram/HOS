package com.hos.authservice.auth;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.FileCopyUtils;

import com.hos.authservice.common.util.JwtUtil;

public class AuthenticationSuccess extends SimpleUrlAuthenticationSuccessHandler {
    
    
    /**
     * Response creation in case the authentication is success.
     * @throws IOException
     *
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {

         String token = JwtUtil.generateToken(String.valueOf(authentication.getPrincipal()).toLowerCase());
         response.getWriter().write(token);
         // if (!response.isCommitted()) {
        //     response.setStatus(HttpStatus.OK.value());
        //     response.setContentType(Constants.CONTENT_TEXT_TYPE);
        //     response.setHeader(Constants.CACHE_HEADER_NAME, Constants.CACHE_HEADER_VALUE);
        //     response.setHeader(Constants.ACCESS_CONTROL_EXPOSE_HEADERS, Constants.AUTHORIZATION);
        //     try {
        //         AuthUserDTO user = getLoggedInUser();
        //         if (user != null) {
        //             String client = request.getHeader(Constants.HEADER_CLIENT);
        //             user = assignUserRoleByClient(client, user);
        //             user.setCurrentDate(new Date().getTime());
        //             ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        //             String json = objectWriter.writeValueAsString(user);
        //             response.getWriter().write(json);
        //             responseHeaderUser(response, user, client);
        //         } else {
        //             response.getWriter().write(ErrorConstants.INVALID_USER_ERROR);
        //         }
        //     } catch (IOException e) {
        //         Logger.logError(ErrorConstants.LOGIN_ERROR + e);
        //     }

        // }
        // clearAuthenticationAttributes(request);
    }
}
 