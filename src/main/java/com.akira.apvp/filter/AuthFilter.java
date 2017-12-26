package com.akira.apvp.filter;

import javax.servlet.*;
import java.io.IOException;


public class AuthFilter implements Filter {

    private FilterConfig filterConfig;

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        chain.doFilter(req, resp);//sends request to next resource
    }

    public FilterConfig getFilterConfig() {
        return this.filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }
}