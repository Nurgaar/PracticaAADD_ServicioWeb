package es.iestetuan.ngg.Controlador;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iestetuan.ngg.dao.ILineaDao;
import es.iestetuan.ngg.dao.jpa.LineaJPADao;
import es.iestetuan.ngg.exception.EmpresaException;
import es.iestetuan.ngg.vo.Linea;

	@RestController
	public class LineaControladorRest {
		
		private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//Muestra en JSON
		
	
	@GetMapping(path="/rest/lineas", produces= {"application/json"})
	public List<Linea> GetLinea() throws EmpresaException {
		
		ILineaDao acciones = new LineaJPADao();
		List<Linea> listalineas = acciones.getListaLineas();
		 
		
		
	
		
		return listalineas;
		
		
			}
	
	}