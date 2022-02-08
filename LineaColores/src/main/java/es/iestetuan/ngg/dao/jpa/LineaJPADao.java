package es.iestetuan.ngg.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import es.iestetuan.ngg.dao.ILineaDao;
import es.iestetuan.ngg.exception.EmpresaException;
import es.iestetuan.ngg.utilidades.GestorEntityManagerJPA;
import es.iestetuan.ngg.vo.Linea;

public class LineaJPADao implements ILineaDao{
	private EntityManager entityManager;

	public void crear(Linea linea) throws EmpresaException{
		EntityTransaction transaccion=null;
		try {
			entityManager=GestorEntityManagerJPA.getEntityManager();
			transaccion=entityManager.getTransaction();
			transaccion.begin();
			
			entityManager.persist(linea);
            
            transaccion.commit();
        } catch (RollbackException err) {
        }
        catch (PersistenceException e) {
            if(transaccion != null)
            	transaccion.rollback();
            throw new  EmpresaException(EmpresaException.EXCEPCION_CREAR, e);            
        } finally {
        	if (entityManager!=null)
        		entityManager.close();
        }				
	}


	public void borrar(Linea linea) throws EmpresaException{
		EntityTransaction transaccion=null;
		try {
			entityManager=GestorEntityManagerJPA.getEntityManager();
			transaccion=entityManager.getTransaction();
			transaccion.begin();
			
			if (!entityManager.contains(linea))
				linea = entityManager.merge(linea);

			entityManager.remove(linea);
            
            transaccion.commit();
        } catch (PersistenceException e) {
            if(transaccion != null)
            	transaccion.rollback();
            throw new  EmpresaException(EmpresaException.EXCEPCION_BORRAR, e);            
        } finally {
        	if (entityManager!=null)
        		entityManager.close();
        }				
	}

	public void actualizar(Linea linea) throws EmpresaException{
		EntityTransaction transaccion=null;
		try {
			entityManager=GestorEntityManagerJPA.getEntityManager();
			transaccion=entityManager.getTransaction();
			transaccion.begin();
			
			// Para poder actulizarse ha de encontarse en el ámbito del entityManager
			if (!entityManager.contains(linea))
				linea=entityManager.merge(linea);

//			Se actualizará cualquier cambio existente entre el objeto que está en la base de datos y el que 
//			se maneja como entidad, siempre que esté entre el comienzo y la confirmación de una transacción. 
            
            transaccion.commit();
        } catch (PersistenceException e) {
            if(transaccion != null)
            	transaccion.rollback();
            throw new  EmpresaException(EmpresaException.EXCEPCION_ACTUALIZAR, e);            
        } finally {
        	if (entityManager!=null)
        		entityManager.close();
        }				
	}


	public Linea getLineaPorID(int idLinea) throws EmpresaException{
		// TODO Auto-generated method stub
		Linea linea = null;
	    try {
			entityManager=GestorEntityManagerJPA.getEntityManager();

			linea = entityManager.find(Linea.class, idLinea);
        }catch (NoResultException e) {
        	linea =null;            
        } catch (PersistenceException e) {
            throw new  EmpresaException(EmpresaException.EXCEPCION_CONSULTAR, e);            
        } finally {
        	if (entityManager!=null)
        		entityManager.close();
        }
		return linea;
	}

	public List<Linea> getListaLineas() throws EmpresaException{
		List<Linea> lineas = null;
	    try {
			entityManager=GestorEntityManagerJPA.getEntityManager();

			String sentenciaJPQL="SELECT linea FROM Linea linea";

			TypedQuery<Linea> query =entityManager.createQuery(sentenciaJPQL, Linea.class);			
			lineas= query.getResultList();
        }catch (NoResultException e) {
        	lineas =null;            
        }catch (PersistenceException e) {
            throw new  EmpresaException(EmpresaException.EXCEPCION_CONSULTAR, e);            
        } finally {
        	if (entityManager!=null)
        		entityManager.close();
        }		

		return lineas;
	}

	@Override
	public int getSiguienteCodigoLinea()  throws EmpresaException{
		int siguienteCodigoLinea= 0;
	    try {
			entityManager=GestorEntityManagerJPA.getEntityManager();

			String sentenciaJPQL="SELECT MAX(linea.codigoLinea) FROM Linea linea";

			TypedQuery<Integer> query =entityManager.createQuery(sentenciaJPQL, Integer.class);			
			siguienteCodigoLinea= query.getSingleResult();
			siguienteCodigoLinea++;
        }catch (NoResultException e) {
        	siguienteCodigoLinea =0;            
        }catch (PersistenceException e) {
            throw new  EmpresaException(EmpresaException.EXCEPCION_CONSULTAR, e);            
        } finally {
        	if (entityManager!=null)
        		entityManager.close();
        }		

		return siguienteCodigoLinea;
	}

}
