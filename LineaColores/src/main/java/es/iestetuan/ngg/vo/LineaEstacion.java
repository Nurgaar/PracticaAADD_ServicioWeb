package es.iestetuan.ngg.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_LINEA_ESTACION")
public class LineaEstacion{
		@EmbeddedId
		private LineaEstacionId idLineaEstacion = new LineaEstacionId();
		
		@Column(name = "orden")
		private int orden;

		@ManyToOne
		@JoinColumn(name="cod_linea", insertable = false, updatable = false,
				foreignKey = @ForeignKey(name = "FK_LINEA_ESTACION"))
		private Linea linea;
		
		@ManyToOne
		@JoinColumn(name="cod_estacion", insertable = false, updatable = false, 
					foreignKey = @ForeignKey(name = "FK_ESTACION_LINEA"))
		private Estacion estacion;
		
		
		/* getters y setters */
		@Embeddable
		public static class LineaEstacionId implements Serializable {		
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Column(name = "cod_linea")
			private int codigoLinea;

			@Column(name = "cod_estacion")
			private int codigoEstacion;
			
			public LineaEstacionId() {
				
			}
			
			public LineaEstacionId(int codigLinea, int codigoEstacion) {
				this.codigoLinea = codigLinea;
				this.codigoEstacion = codigoEstacion;
				}
			
			public boolean equals(Object o) {
				boolean resultado=false;
				if (o != null && o instanceof LineaEstacionId) {
					LineaEstacionId that = (LineaEstacionId) o;
					resultado= (this.codigoLinea==that.codigoLinea) && (this.codigoEstacion == that.codigoEstacion);
				}
				return resultado;
			}
			public int hashCode() {
				return Long.hashCode(codigoLinea)+ Long.hashCode(codigoEstacion);
			}
			
		}
		
		/* getters y setters */
		public void setLinea(Linea linea) {
			this.linea = linea;
			this.idLineaEstacion.codigoLinea=linea.getCodigoLinea();
		}
		public void setEstacion(Estacion estacion) {
			this.estacion = estacion;
			this.idLineaEstacion.codigoEstacion=estacion.getCodigoEstacion();
		}	
}