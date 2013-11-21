package controle;

import javax.persistence.EntityManager;
import model.Locacao;
import org.hibernate.HibernateException;
import util.Conexao;

public class LocacaoDAO {
	
	private EntityManager manager;	
	Locacao locacao;

	public boolean registrar(Locacao locacao){
		try{
			Conexao.conectar();
			manager = Conexao.getConexao().createEntityManager();
			manager.getTransaction().begin();
			manager.merge(locacao);
			manager.getTransaction().commit();
			Conexao.fechar();
			
			return true;
		 }
		catch (HibernateException e){
			e.printStackTrace();
			return false;
		}
	}
	

	public boolean devolver (Locacao locacao){
		try {
			Conexao.conectar();
			manager = Conexao.getConexao().createEntityManager();
			manager.getTransaction().begin();
			manager.merge(locacao);
			//Locacao l = manager.find(Locacao.class, locacao.getId());
			//l.setDataDevolvido(locacao.getDataDevolvido());
			manager.getTransaction().commit();
			
			return true;
			
		}catch (HibernateException e){
			e.printStackTrace();
			return false;		
		}
		
		//public List<Locacao> selecionar() {
			
		//	manager = factory.createEntityManager();
			
		//	Query query = manager.createQuery("SELECT l FROM Produto l");
			
		//	List<Locacao> lista = query.getResultList();
			
		//	return lista;
		}

	}
