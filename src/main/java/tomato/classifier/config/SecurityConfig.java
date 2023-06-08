package tomato.classifier.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tomato.classifier.auth.LoginCheckInterceptor;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/article/add", "/article/edit", "/article/delete", "/article/**/comment-add", "/comment-edit/**", "/comment-delete/**");
    }
}
