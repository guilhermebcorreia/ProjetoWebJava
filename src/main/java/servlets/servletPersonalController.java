package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Personal;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.daoPersonalRepository;


/**  @WebServlet("/servletPersonalController") */
public class servletPersonalController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private daoPersonalRepository daoPersonal = new daoPersonalRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletPersonalController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluir")) {
				
				String id = request.getParameter("id");
								
				daoPersonal.excluir(id);
				
				request.setAttribute("msg", "Personal excluído");
				request.getRequestDispatcher("views/principal.jsp").forward(request, response);
				
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluirajax")) {
				
				String id = request.getParameter("id");
				
				daoPersonal.excluir(id);
				
				response.getWriter().write("Personal Excluído com Sucesso!");
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarPersonal")) {
				
				String nomeBusca = request.getParameter("nomeBusca");
				
				List<Personal> dadosJson = daoPersonal.consultaPersonalList(nomeBusca);
				 
				ObjectMapper mapper = new ObjectMapper();			
				
				String json = mapper.writeValueAsString(dadosJson);
				
				response.getWriter().write(json);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("editarPersonal")) {
				
				String  id = request.getParameter("id");
				
				Personal personal = daoPersonal.consultaID(id);
				
				request.setAttribute("msg", "Personal em modo de edição");
				request.setAttribute("personal", personal);
				request.getRequestDispatcher("views/personal.jsp").forward(request, response);
				
				
				
			} else {
				
				request.getRequestDispatcher("views/principal.jsp").forward(request, response);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);		
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String msg = "Personal Cadastrado";
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String card = request.getParameter("card");
			
			Personal personal = new Personal();
			
			personal.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null );
			personal.setNome(nome);
			personal.setCard(card);
			
			
			if (daoPersonal.validar(nome)) {
				msg = "Personal já cadastrado. Tente outro";
			} else { 
					if (personal.isNovo()) {
					msg = "Personal cadastrado.";
				} else {
					msg = "Personal atualizado.";
				}
					personal = daoPersonal.gravar(personal);
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("personal", personal);
			request.getRequestDispatcher("views/personal.jsp").forward(request, response);
		} catch (Exception e) {
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
