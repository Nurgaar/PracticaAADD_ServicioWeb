package es.iestetuan.ngg.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="T_COLOR")
public class Color{
	@Id
    @Column(name="cod_color")
	@JacksonXmlProperty(localName = "cod_color")	
	private int codigoColor;
    
    @Column(name="nombre")
	@JacksonXmlProperty(localName = "nombre")
	private String nombre;
    
    @Column(name="cod_hexadecimal")
	@JacksonXmlProperty(localName = "cod_hexadecimal")
	private String codigoHexadecimal;
}
