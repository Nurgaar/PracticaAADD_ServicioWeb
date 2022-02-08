package es.iestetuan.ngg.exception;

public class EmpresaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static long NO_EXCEPCION=0;
	public final static long EXCEPCION_CREAR=1;
	public final static long EXCEPCION_ACTUALIZAR=2;
	public final static long EXCEPCION_BORRAR=3;
	public final static long EXCEPCION_CONSULTAR=4;
	
	private long codigoError; 


	public EmpresaException(long  codigoError, Exception excepcion){
		super(excepcion);
		this.codigoError=codigoError;
	}

	public long getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(long codigoError) {
		this.codigoError = codigoError;
	}
}
