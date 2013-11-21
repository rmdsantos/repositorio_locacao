package controle;

import java.io.IOException;
import java.io.PrintWriter;
//import java.text.DateFormat;
//import java.text.Format;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;
//import javax.xml.crypto.Data;

//import org.apache.jasper.tagplugins.jstl.core.Set;

//import sun.text.resources.FormatData;

import model.Cliente;
import model.Locacao;
import model.Veiculo;
import java.util.Date;

@WebServlet("/Servletregloc")
public class Servletregloc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClienteDAO cliDao = new ClienteDAO();
		PrintWriter msg2 = response.getWriter();
		
		Locacao loc = new Locacao();
		
		Date data = new Date();
		String dataDev =request.getParameter("dataDev");
		
		//Calendar calendario = new GregorianCalendar();
		
		//calendario.setTimeInMillis(data.getTime());
		
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		
		
		
		List<Cliente> listCli = cliDao.selecionar();
		
		
			if (listCli.size() != 0){
				
				msg2.println("<form action='Servletregloc'>");
				msg2.println("<select name='clienteBox'>");
				msg2.println("<option>Cliente</option>   ");
				
				for(Cliente c : listCli){
					
					msg2.println("<option value='" + c.getId() + "'> "+ c.getNome() + "</option><br>");
			
					//msg.println("Nome : " + c.getNome() + "<br>");
				}
				msg2.print("</select>");	
				
			} else {
				msg2.println("Nenhum Cliente Cadastrado");
			}
	

			
			VeiculoDAO veiDao = new VeiculoDAO();

			List<Veiculo> listVei = veiDao.selecionar();
			
			
				if (listVei.size() != 0){
					
					msg2.println("<select name='veiculoBox'>");
					msg2.println("<option>Veiculo</option>");
					
					for(Veiculo v : listVei){
					
						msg2.println("<option value='" +  v.getPlaca() + "' > "+ v.getModelo() + "   -   " + v.getPlaca() + "</option>");
											
					}
					msg2.println("</select>");
				} else {
					msg2.println("Nenhum Veiculo Cadastrado");
				}	
				
				msg2.println("<br><label id=dataDevolucao>Data Locação</label>");
				msg2.println("<input type='text' value='"+ formatador.format(data) +"' disabled >");
				
				msg2.println("<br><label>Data da Devolução</label>");
				msg2.println("<input type ='text' name='dataDev'>");
				
				msg2.println("<br><label id=valorPago>Valor Pago</label>");
				msg2.println("<input type ='text' name='valorPago'>");
				
				msg2.println("<br> <input type ='submit' value ='Cadastrar'>");
	
				
				msg2.println("</form>");

				//////////////////////////////////////////////////////////////////////////////////
				try{
					
	
					if(null != request.getParameter("dataDev") && !request.getParameter("dataDev").isEmpty()  ) {
						
						Cliente cliente = new Cliente();
						Veiculo veiculo = new Veiculo();				
						
						String msg;
//						String dataString = request.getParameter(formatador.format(data));
						
						String dataString = request.getParameter("dataDev");
						Date dataD = formatador.parse(dataString);
						float valorPago = Float.parseFloat(request.getParameter("valorPago"));
						
						
						loc.setDataLocacao(data);
						loc.setDataDevolucao(dataD);
						loc.setValorPago(valorPago);
						
//						cliente.setNome();
						String idCliente = request.getParameter("clienteBox");
						cliente.setId(Integer.parseInt(idCliente));
//						cliente.setId(request.getParameter("clienteBox"));
						veiculo.setPlaca(request.getParameter("veiculoBox"));
						loc.setCliente(cliente);
						loc.setVeiculo(veiculo);
							
					LocacaoDAO locDAO = new LocacaoDAO();
					if (locDAO.registrar(loc))
						msg = "Locação Registrada";
					else
						msg = "ERRO AO REGISTRAR";
				
					request.setAttribute("msg", msg);
				
					RequestDispatcher dLocacao = request.getRequestDispatcher("msg.jsp");
					dLocacao.forward(request, response);
		
					
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
	}
	
	
}