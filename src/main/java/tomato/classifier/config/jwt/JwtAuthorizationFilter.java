package tomato.classifier.config.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import tomato.classifier.config.auth.LoginUser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/*
 * 모든 주소에서 동작함 (토큰 검증)
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    // JWT 토큰 헤더를 추가하지 않아도 해당 필터는 통과는 할 수 있지만, 결국 시큐리티단에서 세션 값 검증에 실패함.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (isCookieVerify(request, response)) {
            // 토큰이 존재함
            Cookie[] cookies = request.getCookies();
            String value = cookies[0].getValue();
            String jwtToken = URLDecoder.decode(value, "utf-8");

            String token = jwtToken.replace(JwtVO.TOKEN_PREFIX, "");
            LoginUser loginUser = JwtProcess.verify(token);

            // 임시 세션 (UserDetails 타입 or username)
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser, null,
                    loginUser.getAuthorities()); // id, role 만 존재
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private boolean isCookieVerify(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        try {
            Cookie[] cookies = request.getCookies();
            String value = cookies[0].getValue();
            String jwtToken = URLDecoder.decode(value, "utf-8");
            if (jwtToken == null || !jwtToken.startsWith(JwtVO.TOKEN_PREFIX)) {
                return false;
            } else {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }
}