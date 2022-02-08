package es.iestetuan.ngg.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="T_LINEA", 
	   indexes = @Index(name="INDICE_COLOR", columnList="cod_color", unique = true))
public class Linea{
	@Id
    @Column(name="cod_linea")
	private int codigoLinea;
	
    @Column(name="nombre_corto")
	private String nombreCorto;
    
    @Column(name="nombre_largo")
	private String nombreLargo;
    
    @Column(name="kilometros")
	private float kilometros;

    @Column(name="url_imagen")
	private String urlImagen;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="imagen_linea")
    private byte[] imagenLinea;    
    
	@OneToOne   
	@JoinColumn(name="cod_color",referencedColumnName="cod_color", nullable = false, updatable=false,
				foreignKey = @ForeignKey(name = "FK_COLOR"))	
	private Color color;
}
