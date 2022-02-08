package es.iestetuan.ngg.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="T_ESTACION")
public class Estacion{
	@Id
    @Column(name="cod_estacion")
	private int codigoEstacion;
	
    @Column(name="nombre")
	private String nombre;
    
    @Column(name="direccion")
	private String direccion;

    @OneToMany(mappedBy="estacion")
    List<LineaEstacion> estacionesLinea;

    /* Aquí comienzan los getters y setters */    
	public int getCodigoEstacion() {
		return codigoEstacion;
	}
	public List<LineaEstacion> getEstacionesLinea() {
		return estacionesLinea;
	}
	public void setEstacionesLinea(List<LineaEstacion> estacionesLinea) {
		this.estacionesLinea = estacionesLinea;
	}
	public void setCodigoEstacion(int codigoEstacion) {
		this.codigoEstacion = codigoEstacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
