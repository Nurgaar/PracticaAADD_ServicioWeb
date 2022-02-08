package es.iestetuan.ngg.Controlador;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iestetuan.ngg.dao.IColorDao;
import es.iestetuan.ngg.dao.jpa.ColorJPADao;
import es.iestetuan.ngg.exception.EmpresaException;
import es.iestetuan.ngg.vo.Color;


@RestController
public class ColorControladorRest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	
	@GetMapping(path="/rest/colores", produces= {"application/json"})
	public List<Color> GetColor() throws EmpresaException {
		
		IColorDao acciones = new ColorJPADao();
		List<Color> listacolores= acciones.getListaColores();
		 
		
		
	
		
		return listacolores;
		
		
			}
}
