package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ModelLogin;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import dao.daoUsuarioRepository;

/**
 * @WebServlet("/servletUsuarioController")
 */
public class servletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private daoUsuarioRepository daoUsuario = new daoUsuarioRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletUsuarioController() {
//        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluir")) {
				
				String idUser = request.getParameter("id");
								
				daoUsuario.excluirUser(idUser);
				
				request.setAttribute("msg", "Usuário excluído");
				request.getRequestDispatcher("views/principal.jsp").forward(request, response);
				
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluirajax")) {
				
				String idUser = request.getParameter("id");
				
				daoUsuario.excluirUser(idUser);
				
				response.getWriter().write("Usuário Excluído com Sucesso!");
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUsuario")) {
				
				String nomeBusca = request.getParameter("nomeBusca");
				
				List<ModelLogin> dadosJsonUser = daoUsuario.consultaUsuarioList(nomeBusca);
				 
				ObjectMapper mapper = new ObjectMapper();			
				
				String json = mapper.writeValueAsString(dadosJsonUser);
				
				response.getWriter().write(json);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("editarUsuario")) {
				
				String  id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuario.consultaUsuarioID(id);
				
				request.setAttribute("msg", "Usuário em modo de edição");
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("views/usuarios.jsp").forward(request, response);
				
				
				
			} else {
				
				request.getRequestDispatcher("views/principal.jsp").forward(request, response);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String msg = "Usuário Cadastrado";
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
			
			if (daoUsuario.validarUsuario(login)) {
				msg = "Usuário já cadastrado. Tente outro";
			} else { 
					if (modelLogin.isNovo()) {
					msg = "Usuário cadastrado.";
				} else {
					msg = "Usuário atualizado.";
				}
					modelLogin = daoUsuario.gravarUsuario(modelLogin);
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", modelLogin);
			request.getRequestDispatcher("views/usuarios.jsp").forward(request, response);
		} catch (Exception e) {
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

}