package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="TipoCancha")
public class TipoCancha implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTipoCancha;
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "nombreTipoCancha", length=60, nullable=false)
	private String nombreTipoCancha;
	public double precioHora;
	@ManyToOne
	@JoinColumn(name="idKit", nullable=false)
	private Kit kit;
	public TipoCancha() {
		super();
	}
	
	public TipoCancha(int idTipoCancha,
			String nombreTipoCancha,
			double precioHora, Kit kit) {
		super();
		this.idTipoCancha = idTipoCancha;
		this.nombreTipoCancha = nombreTipoCancha;
		this.precioHora = precioHora;
		this.kit = kit;
	}
	public double getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(double precioHora) {
		this.precioHora = precioHora;
	}

	public int getIdTipoCancha() {
		return idTipoCancha;
	}
	public void setIdTipoCancha(int idTipoCancha) {
		this.idTipoCancha = idTipoCancha;
	}
	public String getNombreTipoCancha() {
		return nombreTipoCancha;
	}
	public void setNombreTipoCancha(String nombreTipoCancha) {
		this.nombreTipoCancha = nombreTipoCancha;
	}
	
	public Kit getKit() {
		return kit;
	}
	public void setKit(Kit kit) {
		this.kit = kit;
	}
	
	
	
}
