package es.iestetuan.ngg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import es.iestetuan.ngg.dao.IColorDao;
import es.iestetuan.ngg.dao.ILineaDao;
import es.iestetuan.ngg.dao.jpa.ColorJPADao;
import es.iestetuan.ngg.dao.jpa.LineaJPADao;
import es.iestetuan.ngg.exception.EmpresaException;
import es.iestetuan.ngg.procesoxml.pojo.ColorXML;
import es.iestetuan.ngg.procesoxml.pojo.LineaXML;
import es.iestetuan.ngg.utilidades.GestorConfiguracion;
import es.iestetuan.ngg.vo.Color;
import es.iestetuan.ngg.vo.Linea;

public class AppRedMetroObjectDB {
	private ILineaDao operLineaDao= new LineaJPADao();
	private IColorDao operColorDao= new ColorJPADao();
	private ObjectMapper mapper = new XmlMapper();
	
	public static void main(String[] args) {
		AppRedMetroObjectDB appRedMetroObjectDB = new AppRedMetroObjectDB();
		System.out.println("Inicio del procesado del fichero XML de COLORES y LÍNEAS.");
		appRedMetroObjectDB.procesarFicherosXML();
		System.out.println("Finalización del procesado de los ficheros XML de COLORES y LÍNEAS.");
	}
	public void procesarFicherosXML() {
		procesarColoresXML();
		procesarLineasXML();
	}
	
    private void procesarColoresXML() {
    	System.out.println("Comienza el procesado del fichero XML de COLORES.");
	    List<ColorXML> listaColoresXML=null;
		try {
		    // Tratamiento Fichero XML Colores
			String ficheroXMLColores= GestorConfiguracion.getInfoConfiguracion("xml_colores");
			String contenidoColoresXML = Files.readString(Paths.get(ficheroXMLColores), StandardCharsets.UTF_8);
			System.out.println(contenidoColoresXML);
			
		    CollectionType tipoListaColores= mapper.getTypeFactory().constructCollectionType(List.class, ColorXML.class);
		    listaColoresXML = mapper.readValue(contenidoColoresXML, tipoListaColores);

		    // Actualización en ObjectDB de Colores
		    for(ColorXML colorXML: listaColoresXML) {
		    	Color colorC = operColorDao.getColorPorID(colorXML.getCodigoColor());
		    	if (colorC==null) {
		    		Color color = new Color();
		    		color.setCodigoColor(colorXML.getCodigoColor());
		    		color.setNombre(colorXML.getNombre());
		    		color.setCodigoHexadecimal(colorXML.getCodigoHexadecimal());
		    		operColorDao.crear(color);
		    	}else
		    		operColorDao.actualizar(colorC);
		    }
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmpresaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finaliza el procesado del fichero XML de COLORES.");
    }

    private void procesarLineasXML() {
    	System.out.println("Comienza el procesado del fichero XML de LÍNEAS.");			
	    List<Linea> listaLineas=null;
	    List<LineaXML> listaLineasXML=null;

		try {
		    // Tratamiento Fichero XML Lineas
			String ficheroXMLLineas= GestorConfiguracion.getInfoConfiguracion("xml_lineas");
			String contenidoLineasXML = Files.readString(Paths.get(ficheroXMLLineas), StandardCharsets.UTF_8);
			System.out.println(contenidoLineasXML);

			CollectionType tipoListaLineas= mapper.getTypeFactory().constructCollectionType(List.class, LineaXML.class);
			listaLineasXML = mapper.readValue(contenidoLineasXML, tipoListaLineas);
		    
			listaLineas = getLineasFromLineasXML(listaLineasXML);
			
		    // Actualización en ObjectDB de Líneas
		    for(Linea linea: listaLineas) {
		    	Linea lineaC = operLineaDao.getLineaPorID(linea.getCodigoLinea());
		    	if (lineaC==null)
		    		operLineaDao.crear(linea);
		    	else
		    		operLineaDao.actualizar(linea);
		    }
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmpresaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finaliza el procesado del fichero XML de LÍNEAS.");
	}
    private List<Linea> getLineasFromLineasXML(List<LineaXML> listaLineasXML){
    	List<Linea> listaLineas= null;
    	listaLineas = new ArrayList<Linea>();
    	for (LineaXML lineaXML: listaLineasXML) {
    		Linea linea = new Linea();
    		linea.setCodigoLinea(lineaXML.getCodigoLinea());
    		linea.setKilometros(lineaXML.getKilometros());
    		linea.setNombreCorto(lineaXML.getNombreCorto());
    		linea.setNombreLargo(lineaXML.getNombreLargo());
    		linea.setUrlImagen(lineaXML.getUrlImagen());
    		Color color = null;
    		try {
				color = operColorDao.getColorPorID(lineaXML.getCodigoColor());
			} catch (EmpresaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		linea.setColor(color);
    		byte[] imagen =getImagenFromURL(lineaXML.getUrlImagen()); 
    		linea.setImagenLinea(imagen);
    		
    		listaLineas.add(linea);
		}
    	return listaLineas;
    }
    
	private byte[] getImagenFromURL(String textoUrlImagen) {        
    	byte[] bytesImagen = null;
		URL url=null;
    	try {
			url = new URL(textoUrlImagen);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.addRequestProperty("User-Agent", "Mozilla");
			InputStream inputStream = urlConnection.getInputStream();
			
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			
			while((len = inputStream.read(buffer)) != -1){
				outStream.write(buffer, 0, len);
			}
			inputStream.close();
			bytesImagen= outStream.toByteArray();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytesImagen;
    }
    

	
}
