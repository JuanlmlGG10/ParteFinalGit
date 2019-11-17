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
@Table(name="Kit")

public class Kit implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKit;
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "nombreKit", length=60, nullable=false)
	private String nombreKit;
	private String descripcionKit;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {
					CascadeType.PERSIST,
					CascadeType.MERGE
					
			},
			mappedBy="kits")
	private Set<Material> materiales=new HashSet<>();
	public Kit() {
		super();
	}
	public Kit(int idKit,
			String nombreKit,String descripcionKit,
			Set<Material> materiales) {
		super();
		this.idKit = idKit;
		this.nombreKit = nombreKit;
		this.materiales = materiales;
		this.descripcionKit=descripcionKit;
	}
	public int getIdKit() {
		return idKit;
	}
	public void setIdKit(int idKit) {
		this.idKit = idKit;
	}
	public String getNombreKit() {
		return nombreKit;
	}
	public void setNombreKit(String nombreKit) {
		this.nombreKit = nombreKit;
	}
	public Set<Material> getMateriales() {
		return materiales;
	}
	public void setMateriales(Set<Material> materiales) {
		this.materiales = materiales;
	}
	public String getDescripcionKit() {
		return descripcionKit;
	}
	public void setDescripcionKit(String descripcionKit) {
		this.descripcionKit = descripcionKit;
	}
	
	
}
