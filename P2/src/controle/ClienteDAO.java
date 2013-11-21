package controle;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Cliente;
import org.hibernate.HibernateException;
import util.Conexao;

public class ClienteDAO {

	private EntityManager manager;		
	private List <Cliente> listCliente = null;

	public boolean inserir(Cliente cliente){
		try{
			Conexao.conectar();
			manager = Conexao.getConexao().createEntityManager();
			manager.getTransaction().begin();
			manager.persist(cliente);
			manager.getTransaction().commit();
			Conexao.fechar();
			
			return true;
		 }
		catch (HibernateException e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> selecionar(){
		try{
			Conexao.conectar();
			manager = Conexao.getConexao().createEntityManager();
			manager.getTransaction().begin();
			//Cliente c = manager.getReference (Cliente.class , 1L);
			Query query = manager.createQuery("SELECT c FROM Cliente c ");
//			manager.getTransaction().commit();
			listCliente = query.getResultList();
			
			for ( Cliente cc : listCliente) {
				System.out.println("Cliente: " + cc. getNome ());
			}
			Conexao.fechar();
			
			return listCliente;
		 }catch (HibernateException e){
			e.printStackTrace();
		}
		
		return listCliente;
		
	}
}