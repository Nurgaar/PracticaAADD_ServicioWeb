package es.iestetuan.ngg.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import es.iestetuan.ngg.dao.IColorDao;
import es.iestetuan.ngg.exception.EmpresaException;
import es.iestetuan.ngg.utilidades.GestorEntityManagerJPA;
import es.iestetuan.ngg.vo.Color;

public class ColorJPADao implements IColorDao{
	private EntityManager entityManager;

	public void crear(Color color) throws EmpresaException{
		EntityTransaction transaccion=null;
		try {
			entityManager=GestorEntityManagerJPA.getEntityManager();
			transaccion=entityManager.getTransaction();
			transaccion.begin();
			
			entityManager.persist(color);
            
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


	public void borrar(Color color) throws EmpresaException{
		EntityTransaction transaccion=null;
		try {
			entityManager=GestorEntityManagerJPA.getEntityManager();
			transaccion=entityManager.getTransaction();
			transaccion.begin();
			
			if (!entityManager.contains(color))
				color=entityManager.merge(color);

			entityManager.remove(color);
            
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

	public void actualizar(Color color) throws EmpresaException{
		EntityTransaction transaccion=null;
		try {
			entityManager=GestorEntityManagerJPA.getEntityManager();
			transaccion=entityManager.getTransaction();
			transaccion.begin();
			
			// Para poder actulizarse ha de encontarse en el ámbito del entityManager
			if (!entityManager.contains(color))
				color=entityManager.merge(color);
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


	public Color getColorPorID(int idColor) throws EmpresaException{
		// TODO Auto-generated method stub
		Color color = null;
	    try {
			entityManager=GestorEntityManagerJPA.getEntityManager();

			color = entityManager.find(Color.class, idColor);
        }catch (NoResultException e) {
        	color =null;            
        } catch (PersistenceException e) {
            throw new  EmpresaException(EmpresaException.EXCEPCION_CONSULTAR, e);            
        } finally {
        	if (entityManager!=null)
        		entityManager.close();
        }
		return color;
	}

	public List<Color> getListaColores() throws EmpresaException{
		List<Color> colores = null;
	    try {
			entityManager=GestorEntityManagerJPA.getEntityManager();

			String sentenciaJPQL="SELECT colores FROM Color colores";

			TypedQuery<Color> query =entityManager.createQuery(sentenciaJPQL, Color.class);			
			colores= query.getResultList();
        }catch (NoResultException e) {
        	colores =null;            
        }catch (PersistenceException e) {
            throw new  EmpresaException(EmpresaException.EXCEPCION_CONSULTAR, e);            
        } finally {
        	if (entityManager!=null)
        		entityManager.close();
        }		

		return colores;
	}


	@Override
	public int getSiguienteCodigoColor()  throws EmpresaException{
		int siguienteCodigoColor= 0;
	    try {
			entityManager=GestorEntityManagerJPA.getEntityManager();

			String sentenciaJPQL="SELECT MAX(color.codigoColor) FROM Color color";

			TypedQuery<Integer> query =entityManager.createQuery(sentenciaJPQL, Integer.class);			
			siguienteCodigoColor= query.getSingleResult();
        	siguienteCodigoColor++;
        }catch (NoResultException e) {
        	siguienteCodigoColor =0;
        }catch (PersistenceException e) {
            throw new  EmpresaException(EmpresaException.EXCEPCION_CONSULTAR, e);            
        } finally {
        	if (entityManager!=null)
        		entityManager.close();
        }		

		return siguienteCodigoColor;
	}

}
