package pe.edu.upc.spring.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.aspectj.bridge.Message;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="Reserva")
public class Reserva implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idReserva;
	private double montoReserva;
	private String comentarioReserva;
	@NotNull
	@FutureOrPresent(message="No puedes seleccionar un dia que ya ha pasado")
	@Temporal(TemporalType.DATE)
	@Column(name = "fechaReserva")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaReserva;
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(name = "horaInicio")
	@DateTimeFormat(pattern = "HH")
	private Date horaInicio;
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(name = "horaFin")
	@DateTimeFormat(pattern = "HH")
	private Date horaFin;
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "fechaRegistro")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaRegistro;
	private String NTipopago;
	@ManyToOne
	@JoinColumn(name="idPersona", nullable=false)
	private Persona persona;
	@ManyToOne
	@JoinColumn(name="idCancha", nullable=false)
	private Cancha cancha;
	public Reserva() {
		super();
	}
	public Reserva(int idReserva, double montoReserva, String comentarioReserva,
		Date fechaReserva,
			Date horaInicio,Date horaFin,
			Date fechaRegistro,
			String nTipopago, Persona persona, Cancha cancha) {
		super();
		this.idReserva = idReserva;
		this.montoReserva = montoReserva;
		this.comentarioReserva = comentarioReserva;
		this.fechaReserva = fechaReserva;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.fechaRegistro = fechaRegistro;
		NTipopago = nTipopago;
		this.persona = persona;
		this.cancha = cancha;
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public double getMontoReserva() {
		return montoReserva;
	}
	public void setMontoReserva(double montoReserva) {
		/*int tiempoFin=horaFin.getHours();
		int tiempoInicio=horaInicio.getHours();
		int tiempo=tiempoFin-tiempoInicio;
		
		double costo = cancha.getTipoCancha().getPrecioHora();
		this.montoReserva=tiempo*costo;
		*/
		
		
		
		this.montoReserva=montoReserva;
		
	}
	public String getComentarioReserva() {
		return comentarioReserva;
	}
	public void setComentarioReserva(String comentarioReserva) {
		this.comentarioReserva = comentarioReserva;
	}
	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		LocalDateTime now = LocalDateTime.now();
		Date afecharegistro= Date.from( now.atZone( ZoneId.systemDefault()).toInstant());
		this.fechaRegistro = afecharegistro;
	}
	public String getNTipopago() {
		return NTipopago;
	}
	public void setNTipopago(String nTipopago) {
		NTipopago = nTipopago;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Cancha getCancha() {
		return cancha;
	}
	public void setCancha(Cancha cancha) {
		this.cancha = cancha;
	}

	
	
	
}
