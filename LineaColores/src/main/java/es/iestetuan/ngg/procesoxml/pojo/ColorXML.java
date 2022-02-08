package es.iestetuan.ngg.procesoxml.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ColorXML{
	@JacksonXmlProperty(localName = "cod_color")	
	private int codigoColor;
    
	@JacksonXmlProperty(localName = "nombre")
	private String nombre;
    
	@JacksonXmlProperty(localName = "cod_hexadecimal")
	private String codigoHexadecimal;
}
