package com.zzx.filter;

import com.zzx.model.File;
import com.zzx.model.User;
import com.zzx.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class DownloadFilter implements Filter {


    @Autowired
    private FileService fileService;

    /**
     * 服务启动,调用初始化方法
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    /**
     * 请求时调用
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.sendRedirect("/");
    }

    /**
     * 销毁调用
     */
    @Override
    public void destroy() {
    }
}
