package es.iestetuan.ngg.vo;

import java.util.Date;

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
@Table(name="T_TREN")
public class Tren{
	@Id
    @Column(name="cod_tren")
	private int codigoTren;
	
    @Column(name="modelo")	
	private String modelo;
    
    @Column(name="fecha_incorporacion")
	private Date anyoIncorporacion;
    
    @Column(name="empresa_constructora")
	private String empresaConstructora;
    
    @ManyToOne
    @JoinColumn(name="cod_linea", referencedColumnName="cod_linea",
    		foreignKey = @ForeignKey(name = "FK_LINEA"))
	private Linea linea;
    
    @ManyToOne
    @JoinColumn(name="cod_cochera", referencedColumnName="cod_cochera",
    		foreignKey = @ForeignKey(name = "FK_COCHERA"))
    private Cochera cochera;
	
}
