package es.iestetuan.ngg.dao;

import java.util.List;

import es.iestetuan.ngg.exception.EmpresaException;
import es.iestetuan.ngg.vo.Linea;

public interface ILineaDao {
    public void crear(Linea linea) throws EmpresaException;

    public void borrar(Linea linea) throws EmpresaException;

    public void actualizar (Linea linea) throws EmpresaException;

    public Linea getLineaPorID(int idLinea) throws EmpresaException ;

    public List<Linea> getListaLineas() throws EmpresaException;
    
    public int getSiguienteCodigoLinea() throws EmpresaException;
}
