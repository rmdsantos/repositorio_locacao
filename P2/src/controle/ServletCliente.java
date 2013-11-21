package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;


@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String msg;
		String nome = request.getParameter("nome");
		char sexo = request.getParameter("sexo").charAt(0);
		String telefone = request.getParameter("telefone");
		String email = request.getParameter("email");
	
		Cliente cli = new Cliente();
	
		cli.setNome(nome);
		cli.setSexo(sexo);
		cli.setTelefone(telefone);
		cli.setEmail(email);
				
		ClienteDAO cliDAO = new ClienteDAO();
		if (cliDAO.inserir(cli))
			msg = "Cliente Cadastrado";
		else
			msg = "ERRO AO CADASTRAR";
	
		request.setAttribute("msg", msg);
	
		RequestDispatcher dCliente = request.getRequestDispatcher("msg.jsp");
		dCliente.forward(request, response);
	}

}
