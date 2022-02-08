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
import es.iestetuan.ngg.dao.jpa.ColorJPADao;
import es.iestetuan.ngg.exception.EmpresaException;
import es.iestetuan.ngg.vo.Color;

@Controller
public class ColorControlador {

private final Logger logger = LoggerFactory.getLogger(this.getClass());


	//método para listar todos los colores
	
	@GetMapping("/colores")
	public String listarColores(Model modelo) throws EmpresaException {
		List<Color> colores = new ArrayList<Color>();
		IColorDao acciones = new ColorJPADao();
		
		
			colores=acciones.getListaColores();

		modelo.addAttribute("colores", colores);
		
		return "color/listar";
	}
	
	
	
	//método para solo buscar 1 color
	@GetMapping(value= {"/colorseleccionado/{codigoColor}"})
	public String buscarporcolor (Model modelo, @PathVariable int codigoColor) {
		
		try {
			
			IColorDao acciones = new ColorJPADao();
			Color color = acciones.getColorPorID(codigoColor);
			modelo.addAttribute("color",color);
			
			
		}catch(Exception err) {
			err.printStackTrace();
		}
		
		return "color/buscar";
		
		
		
	}
}
