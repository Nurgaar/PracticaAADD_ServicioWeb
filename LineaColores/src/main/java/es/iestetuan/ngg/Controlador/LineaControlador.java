package es.iestetuan.ngg.Controlador;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.iestetuan.ngg.dao.IColorDao;
import es.iestetuan.ngg.dao.ILineaDao;
import es.iestetuan.ngg.dao.jpa.ColorJPADao;
import es.iestetuan.ngg.dao.jpa.LineaJPADao;
import es.iestetuan.ngg.exception.EmpresaException;
import es.iestetuan.ngg.vo.Color;
import es.iestetuan.ngg.vo.Linea;

@Controller
public class LineaControlador {

	
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/lineas")
	public String listarLineas(Model modelo) throws EmpresaException {
		List<Linea> lineas = new ArrayList<Linea>();
		ILineaDao acciones = new LineaJPADao();
		
		
			lineas=acciones.getListaLineas();

		modelo.addAttribute("lineas", lineas);
		
		return "linea/listar";
	}
	

	//método para solo buscar 1 color
	@GetMapping(value= {"lineaseleccionada/{codigoLinea}"})
	public String buscarporLinea (Model modelo, @PathVariable int codigoLinea) {
		
		try {
			
			ILineaDao acciones = new LineaJPADao();
			Linea linea = acciones.getLineaPorID(codigoLinea);
			modelo.addAttribute("linea",linea);
			
			
		}catch(Exception err) {
			err.printStackTrace();
		}
		
		return "linea/buscar";
		
		
		
	}
}
