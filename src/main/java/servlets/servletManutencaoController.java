package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Manutencao;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.daoManutencaoRepository;

/**
 * @WebServlet("/servletManutencaoController")
 */
public class servletManutencaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private daoManutencaoRepository daoMan = new daoManutencaoRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletManutencaoController() {
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
								
				daoMan.excluir(id);
				
				request.setAttribute("msg", "Manutenção excluído");
				request.getRequestDispatcher("views/principal.jsp").forward(request, response);
				
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluirajax")) {
				
				String id = request.getParameter("id");
				
				daoMan.excluir(id);
				
				response.getWriter().write("Manutenção Excluída com Sucesso!");
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarMan")) {
				
				String nomeBusca = request.getParameter("nomeBusca");
				
				List<Manutencao> dadosJson = daoMan.consultamanutencaoList(nomeBusca);
				 
				ObjectMapper mapper = new ObjectMapper();			
				
				String json = mapper.writeValueAsString(dadosJson);
				
				response.getWriter().write(json);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("editarMan")) {
				
				String  id = request.getParameter("id");
				
				Manutencao manutencao = daoMan.consultamanutencaoID(id);
				
				request.setAttribute("msg", "Manutenção em modo de edição");
				request.setAttribute("manutencao", manutencao);
				request.getRequestDispatcher("views/manutencao.jsp").forward(request, response);
				
				
				
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
			String msg = "Manutenção Cadastrada";
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			
			Manutencao manutencao = new Manutencao();
			
			manutencao.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null );
			manutencao.setNome(nome);
			manutencao.setDescricao(descricao);
	
			
			if (daoMan.validarmanutencao(nome)) {
				msg = "Manutenção já cadastrada. Tente outra";
			} else { 
					if (manutencao.isNovo()) {
					msg = "Manutenção cadastrada.";
				} else {
					msg = "Manutenção atualizada.";
				}
					manutencao = daoMan.gravarmanutencao(manutencao);
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("manutencao", manutencao);
			request.getRequestDispatcher("views/manutencao.jsp").forward(request, response);
		} catch (Exception e) {
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
