package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Conexao {

	private static EntityManagerFactory factory;
	
	public static void conectar(){
		factory = Persistence.createEntityManagerFactory("LocacaoUnit");
}
	
	public static EntityManagerFactory getConexao(){
		return factory;
	}
	
	public static void fechar(){
		factory.close();
	}
	
}
