package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ModelLogin;

import java.awt.Window;
import java.io.IOException;

import dao.DaoLoginRepository;


/** @WebServlet("/servletLogin")*/	//mapping
public class servletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   DaoLoginRepository daoLogin = new DaoLoginRepository();
    
	/**
     * Recebe por passagem de parametros
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		} else {
			doPost(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("User");
		String password = request.getParameter("senha");
		String url = request.getParameter("url");
		
		if (login != null && !login.isEmpty() && password != null && !password.isEmpty() ) {
			
//			if (modelLogin.getLogin().equals("Gui") && (modelLogin.getSenha().equals("123"))) {
			try {
				
				ModelLogin modelLogin = new ModelLogin();
				modelLogin.setLogin(login);
				modelLogin.setSenha(password);
				
				if (daoLogin.verify(modelLogin)) {
					
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					
					if(url == null || url.equals("null")) {
						url = "views/principal.jsp";
					}
					
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);
				} else {
					
					RequestDispatcher redirect = request.getRequestDispatcher("index.jsp");
					request.setAttribute("msg", "Usuario nao encontrado, tente novamente !");
					redirect.forward(request, response);
				}
			} catch (Exception e) {
				RequestDispatcher redireciona = request.getRequestDispatcher("erro.jsp");
				request.setAttribute("msg", e.getMessage());
				redireciona.forward(request, response);
			}
			
		} else {
			RequestDispatcher redirect = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente !");
			redirect.forward(request, response);
		}
	}

}
