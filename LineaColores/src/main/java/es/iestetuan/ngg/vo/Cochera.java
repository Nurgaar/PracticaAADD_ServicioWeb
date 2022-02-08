package es.iestetuan.ngg.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="T_COCHERA")
public class Cochera {
	@Id
    @Column(name="cod_cochera")
	private int codigoCochera;
	
    @Column(name="nombre")
	private String nombre;
    
    @Column(name="direccion")
	private String direccion;
    
    @Column(name="deposito")
	private int deposito;
	
	@OneToMany(mappedBy="cochera")
	private List<Tren> trenes;
	
	
}
