package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Financeiro;
import models.ModelLogin;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.daoFinanceiroRepository;
import dao.daoUsuarioRepository;

/**
 * @WebServlet("/servletFinanceiroController")
 */
public class servletFinanceiroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private daoFinanceiroRepository daoFinan = new daoFinanceiroRepository();   
    private daoUsuarioRepository daoUser = new daoUsuarioRepository();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletFinanceiroController() {
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
								
				daoFinan.excluir(id);
				
				request.setAttribute("msg", "Baixa Financeira excluído");
				request.getRequestDispatcher("views/principal.jsp").forward(request, response);
				
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluirajax")) {
				
				String id = request.getParameter("id");
				
				daoFinan.excluir(id);
				
				response.getWriter().write("Baixa Financeira Excluído com Sucesso!");
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarFinan")) {
				
				String nomeBusca = request.getParameter("nomeBusca");
				
				List<Financeiro> dadosJson = daoFinan.consultaFinanceiroList(nomeBusca);
				 
				ObjectMapper mapper = new ObjectMapper();			
				
				String json = mapper.writeValueAsString(dadosJson);
				
				response.getWriter().write(json);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("editarFinan")) {
				
				String  id = request.getParameter("id");
				
				Financeiro financeiro = daoFinan.consultaFinanceiroID(id);
				
				request.setAttribute("msg", "Financeiro em modo de edição");
				request.setAttribute("financeiro", financeiro);
				request.getRequestDispatcher("views/financeiro.jsp").forward(request, response);
				
				
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("getUser")) {
				
				List<ModelLogin> dadosJson = daoUser.findAll();
				 
				ObjectMapper mapper = new ObjectMapper();			
				
				String json = mapper.writeValueAsString(dadosJson);
				
				response.getWriter().write(json);
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
			String msg = "Financeiro Cadastrado";
			String id = request.getParameter("id");
			String idUser = request.getParameter("idUser");
			String nome = request.getParameter("nome");
			String valor = request.getParameter("valor");
			String descricao = request.getParameter("descricao");
			String tipo = request.getParameter("tipo");
			
			Financeiro financeiro = new Financeiro();
			
			financeiro.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null );
			financeiro.setIdUser(idUser);
			financeiro.setNome(nome);
			financeiro.setValor(valor);
			financeiro.setDescricao(descricao);
			financeiro.setTipo(tipo);
			
			if (daoFinan.validarFinanceiro(nome)) {
				msg = "Financeiro já cadastrado. Tente outro";
			} else { 
					if (financeiro.isNovo()) {
					msg = "Financeiro cadastrado.";
				} else {
					msg = "Financeiro atualizado.";
				}
					financeiro = daoFinan.gravarFinanceiro(financeiro);
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("financeiro", financeiro);
			request.getRequestDispatcher("views/financeiro.jsp").forward(request, response);
		} catch (Exception e) {
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
