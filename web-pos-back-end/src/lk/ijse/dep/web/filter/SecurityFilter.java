package lk.ijse.dep.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
/*

*
 * @author : Damika Anupama Nanayakkara <damikaanupama@gmail.com>
 * @since : 14/12/2020
 *
*/
@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Incoming Request");

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("Response");
    }

    @Override
    public void destroy() {

    }
}
