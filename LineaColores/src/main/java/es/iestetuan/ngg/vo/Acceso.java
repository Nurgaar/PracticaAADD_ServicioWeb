package es.iestetuan.ngg.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="T_ACCESO")
public class Acceso{
	@Id
    @Column(name="cod_acceso")
	private int codigoAcceso;
	
    @Column(name="nombre")
	private String nombre;
    
    @Column(name="acceso_discapacidad")
	private boolean tieneAccesoDiscapacidad;
    
    @ManyToOne 
    @JoinColumn(name="cod_estacion", referencedColumnName="cod_estacion",
	foreignKey = @ForeignKey(name = "FK_ESTACION"))
	private Estacion estacion;
	
}
