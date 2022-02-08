package es.iestetuan.ngg.dao;

import java.util.List;

import es.iestetuan.ngg.exception.EmpresaException;
import es.iestetuan.ngg.vo.Color;

public interface IColorDao {
    public void crear(Color infoPersonal) throws EmpresaException;

    public void borrar(Color infoPersonal) throws EmpresaException;

    public void actualizar (Color infoPersonal) throws EmpresaException;

    public Color getColorPorID(int idInfoPersonal) throws EmpresaException ;

    public List<Color> getListaColores() throws EmpresaException;
    
    public int getSiguienteCodigoColor() throws EmpresaException;
}
