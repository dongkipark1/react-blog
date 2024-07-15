package shop.mtcoding.blog._core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CorsFilter implements Filter {

    private String host;

    public CorsFilter(String host) {
        this.host = host;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("CORS 필터 작동");
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        resp.setHeader("Access-Control-Allow-Origin", host);
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Expose-Headers", "*");

        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            System.out.println("Authorization이 포함될때만?");
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // 해당 헤더가 없으면 아래 7가지의 header값만 응답할 수 있다.
        // Cache-Control
        //Content-Language
        //Content-Length
        //Content-Type
        //Expires
        //Last-Modified
        //Pragma


        chain.doFilter(request, response);
    }

}







