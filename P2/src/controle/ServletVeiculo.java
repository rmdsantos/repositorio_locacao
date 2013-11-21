package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Veiculo;

@WebServlet("/ServletVeiculo")
public class ServletVeiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg;
		String placa = request.getParameter("placa");
		String fabricante = request.getParameter("fabricante");
		String modelo = request.getParameter("modelo");
		int kmAtual = Integer.parseInt(request.getParameter("kmAtual"));
		float taxaDiaria = Float.parseFloat(request.getParameter("taxaDiaria"));
		
		Veiculo veic = new Veiculo();
		
		veic.setPlaca(placa);
		veic.setFabricante(fabricante);
		veic.setModelo(modelo);
		veic.setKmAtual(kmAtual);
		veic.setTaxaDiaria(taxaDiaria);
		
		VeiculoDAO veicDAO = new VeiculoDAO();
		if (veicDAO.inserir(veic))
			msg = "Veiculo Cadastrado";
		else
			msg = "ERRO AO CADASTRAR";
		
		request.setAttribute("msg", msg);
		
		RequestDispatcher dVeiculo = request.getRequestDispatcher("msg.jsp");
		dVeiculo.forward(request, response);
	}

}