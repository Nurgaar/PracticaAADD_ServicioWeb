package es.iestetuan.ngg.procesoxml.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JacksonXmlRootElement(localName = "empleado")
public class LineaXML {
	@JacksonXmlProperty(localName = "cod_linea")
	private int codigoLinea;
	
	@JacksonXmlProperty(localName = "nombre_corto")
	private String nombreCorto;
    
	@JacksonXmlProperty(localName = "nombre_largo")
	private String nombreLargo;

	@JacksonXmlProperty(localName = "kilometros")
	private float kilometros;

	@JacksonXmlProperty(localName = "url_img_tmp")
	private String urlImagen;

	@JacksonXmlProperty(localName = "cod_color")
	private int codigoColor;


}
