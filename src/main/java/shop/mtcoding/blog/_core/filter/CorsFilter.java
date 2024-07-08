package shop.mtcoding.blog._core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    @Value("${allow.host}")
    private String host;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("CORS 필터 작동");
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Origin", host);
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        // 해당 헤더가 없으면 아래 7가지의 header값만 응답할 수 있다.
        // Cache-Control
        //Content-Language
        //Content-Length
        //Content-Type
        //Expires
        //Last-Modified
        //Pragma
        resp.setHeader("Access-Control-Expose-Headers", "*");

        chain.doFilter(request, response);
    }

}