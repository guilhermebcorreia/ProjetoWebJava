package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ModelLogin;

import java.io.IOException;

import connection.Conexao;
import dao.daoUsuarioRepository;

/**
 * Servlet implementation class servletUsuarioController
 */
public class servletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private daoUsuarioRepository daoUsuario = new daoUsuarioRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletUsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String idUser = request.getParameter("idUser");
			String nome = request.getParameter("nomeUsuario");
			String email = request.getParameter("emailUsuario");
			String login = request.getParameter("loginUsuario");
			String senha = request.getParameter("senhaUsuario");
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setIdUser(idUser != null && !idUser.isEmpty() ? Long.parseLong(idUser) : null );
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			daoUsuario.validarUsuario(modelLogin);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		doGet(request, response);
	}

}
