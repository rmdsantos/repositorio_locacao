package controle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Veiculo;
import org.hibernate.HibernateException;
import util.Conexao;

public class VeiculoDAO {

	private EntityManager manager;		
	private List <Veiculo> listVeiculo = null;
	
	public boolean inserir(Veiculo veiculo){
		try{
			Conexao.conectar();
			manager = Conexao.getConexao().createEntityManager();
			manager.getTransaction().begin();
			manager.persist(veiculo);
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
	public List<Veiculo> selecionar(){
		try{
			Conexao.conectar();
			manager = Conexao.getConexao().createEntityManager();
			manager.getTransaction().begin();
			
			Query query = manager.createQuery("SELECT v FROM Veiculo v ");

			listVeiculo = query.getResultList();
			
			for ( Veiculo vv : listVeiculo) {
				System.out.println("Veiculo: " + vv.getModelo());
			}
			Conexao.fechar();
			
			return listVeiculo;
			
		 }catch (HibernateException e){
			e.printStackTrace();
		}
		
		return listVeiculo;
		
	}
	
}
