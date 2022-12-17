package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Compras;
import models.ModelLogin;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.daoComprasRepository;

/**
 * Servlet implementation class servletComprasController
 */
public class servletComprasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private daoComprasRepository daoCompra = new daoComprasRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletComprasController() {
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
								
				daoCompra.excluir(id);
				
				request.setAttribute("msg", "Compra excluída");
				request.getRequestDispatcher("views/principal.jsp").forward(request, response);
				
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluirajax")) {
				
				String id = request.getParameter("id");
				
				daoCompra.excluir(id);
				
				response.getWriter().write("Compra Excluída com Sucesso!");
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarCompra")) {
				
				String nomeBusca = request.getParameter("nomeBusca");
				
				List<Compras> dadosJson = daoCompra.consultaCompraList(nomeBusca);
				 
				ObjectMapper mapper = new ObjectMapper();			
				
				String json = mapper.writeValueAsString(dadosJson);
				
				response.getWriter().write(json);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("editarCompra")) {
				
				String  id = request.getParameter("id");
				
				Compras compras = daoCompra.consultaID(id);
				
				request.setAttribute("msg", "Compra em modo de edição");
				request.setAttribute("compra", compras);
				request.getRequestDispatcher("views/compras.jsp").forward(request, response);
				
				
				
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
			String msg = "Compra Cadastrado";
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String valor = request.getParameter("valor");
			String descricao = request.getParameter("descricao");
			
			Compras compra = new Compras();
			
			compra.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null );
			compra.setNome(nome);
			compra.setValor(valor);
			compra.setDescricao(descricao);
			
			if (daoCompra.validar(nome)) {
				msg = "Compra já cadastrada. Tente outro";
			} else { 
					if (compra.isNovo()) {
					msg = "Compra cadastrada.";
				} else {
					msg = "Compra atualizada.";
				}
					compra = daoCompra.gravarcompra(compra);
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("compra", compra);
			request.getRequestDispatcher("views/compras.jsp").forward(request, response);
		} catch (Exception e) {
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
