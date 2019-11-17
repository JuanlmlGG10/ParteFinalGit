package pe.edu.upc.spring.model;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="Cancha")

public class Cancha implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCancha;
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "nombreCancha", length=60, nullable=false)
	private String nombreCancha;
	@ManyToOne
	@JoinColumn(name="idSede", nullable=false)
	private Sede sede;
	@ManyToOne
	@JoinColumn(name="idTipoCancha", nullable=false)
	private TipoCancha tipoCancha;
	public Cancha() {
		super();
	}
	public Cancha(int idCancha,
			String nombreCancha,
			Sede sede, TipoCancha tipoCancha) {
		super();
		this.idCancha = idCancha;
		this.nombreCancha = nombreCancha;
		this.sede = sede;
		this.tipoCancha = tipoCancha;
	}
	public int getIdCancha() {
		return idCancha;
	}
	public void setIdCancha(int idCancha) {
		this.idCancha = idCancha;
	}
	public String getNombreCancha() {
		return nombreCancha;
	}
	public void setNombreCancha(String nombreCancha) {
		this.nombreCancha = nombreCancha;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	public TipoCancha getTipoCancha() {
		return tipoCancha;
	}
	public void setTipoCancha(TipoCancha tipoCancha) {
		this.tipoCancha = tipoCancha;
	}
	
	
	
}
