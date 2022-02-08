package es.iestetuan.ngg.utilidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GestorEntityManagerJPA {
	private final static String UNIDADPERSISTENCIA_EMPRESA="UP_RedMetro_ODB";
	
	private static EntityManagerFactory entityManagerFactory; 
	//Conexión JPA 	
	public static EntityManager getEntityManager() {
		EntityManager gestorEntidades=null;
		
		if (entityManagerFactory==null) {
			// Se apoya en el fichero persistence.xml
			entityManagerFactory= Persistence.createEntityManagerFactory(UNIDADPERSISTENCIA_EMPRESA);
		}
		
		gestorEntidades = entityManagerFactory.createEntityManager();
		return gestorEntidades;
	}
}
