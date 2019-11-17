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
@Table(name="Material")

public class Material  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMaterial;
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "nombreMaterial", length=60, nullable=false)
	private String nombreMaterial;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {
					CascadeType.PERSIST,
					CascadeType.MERGE
					
			})
	@JoinTable(name="material_kit",
	joinColumns= {@JoinColumn(name="material_idMaterial")},
	inverseJoinColumns = {@JoinColumn(name="kit_idKit")})
	private Set<Kit> kits=new HashSet<>();

	public Material() {
		super();
	}

	public Material(int idMaterial,
			String nombreMaterial,
			Set<Kit> kits) {
		super();
		this.idMaterial = idMaterial;
		this.nombreMaterial = nombreMaterial;
		this.kits = kits;
	}

	public int getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}

	public String getNombreMaterial() {
		return nombreMaterial;
	}

	public void setNombreMaterial(String nombreMaterial) {
		this.nombreMaterial = nombreMaterial;
	}

	public Set<Kit> getKits() {
		return kits;
	}

	public void setKits(Set<Kit> kits) {
		this.kits = kits;
	}
	

}
