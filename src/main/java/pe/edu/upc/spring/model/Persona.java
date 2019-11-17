package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Persona")

public class Persona implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPersona;
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "nombrePersona", length=60, nullable=false)
	private String nombrePersona;
	private String apellidoPersona;
	@Length(min = 8, max = 8)
	@Column(unique=true)
	private String numeroDni;
	@Length(min = 9, max = 9)
	private String numeroCelular;
	@Pattern(message = "Formato invalido de email", regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String direccionEmail;
	private String tipoUsuario;
	@Column(length = 30, unique = true)
	private String username;
	@Column(length = 60)
	private String password;
	@ManyToOne
	@JoinColumn(name="idDistrito", nullable=false)
	private Distrito distrito;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "persona_idPersona")
	private List<Role> roles;
	public Persona() {
		super();
	}
	public Persona(int idPersona,String nombrePersona,String apellidoPersona, String numeroDni, String numeroCelular, String direccionEmail, String tipoUsuario,
			Distrito distrito,String username,String password) {
		super();
		this.idPersona = idPersona;
		this.nombrePersona = nombrePersona;
		this.apellidoPersona = apellidoPersona;
		this.numeroDni = numeroDni;
		this.numeroCelular = numeroCelular;
		this.direccionEmail = direccionEmail;
		this.tipoUsuario = tipoUsuario;
		this.distrito = distrito;
		this.username=username;
		this.password=password;
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombrePersona() {
		return nombrePersona;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	public String getApellidoPersona() {
		return apellidoPersona;
	}
	public void setApellidoPersona(String apellidoPersona) {
		this.apellidoPersona = apellidoPersona;
	}
	public String getNumeroDni() {
		return numeroDni;
	}
	public void setNumeroDni(String numeroDni) {
		this.numeroDni = numeroDni;
	}
	public String getNumeroCelular() {
		return numeroCelular;
	}
	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	public String getDireccionEmail() {
		return direccionEmail;
	}
	public void setDireccionEmail(String direccionEmail) {
		this.direccionEmail = direccionEmail;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		String usuario="Cliente";
		this.tipoUsuario = usuario;
	}
	public Distrito getDistrito() {
		return distrito;
	}
	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
