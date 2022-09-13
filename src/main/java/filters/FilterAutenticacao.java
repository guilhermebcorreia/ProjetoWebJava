package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class FilterAutenticacao
 */
@WebFilter(urlPatterns = {"/views/*"})
public class FilterAutenticacao implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FilterAutenticacao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			String userLogin = (String) session.getAttribute("usuario");
			String urlOrigem = req.getServletPath();
			
			if (userLogin == null && !urlOrigem.equalsIgnoreCase("/views/ServletLogin")) {
				RequestDispatcher redireciona = request.getRequestDispatcher("/index,jsp=" + urlOrigem);
				
				request.setAttribute("msg", "Efetuar login no sistema");
				redireciona.forward(req, response);
				return;
			}else {
				chain.doFilter(request, response);
			}
			
		} catch (Exception e) {
			RequestDispatcher redireciona = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redireciona.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
