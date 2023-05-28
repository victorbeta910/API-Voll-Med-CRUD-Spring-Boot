package med.voll.api.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import med.voll.api.direccion.Direccion;

@Entity(name="Paciente")
@Table(name="pacientes")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Paciente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombre;
	private String email;
	private String documento;
	private String telefono;
	private boolean activo;
	@Embedded
	private Direccion direccion;


	public Paciente() {
		
	}
	
	public Paciente(DatosRegistroPacientes datosRegistroPacientes) {
		this.activo=true;
		this.nombre=datosRegistroPacientes.nombre();
		this.email=datosRegistroPacientes.email();
		this.documento=datosRegistroPacientes.documento();
		this.telefono=datosRegistroPacientes.telefono();
		this.direccion=new Direccion(datosRegistroPacientes.direccion());
	}

	public long getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}



	public void actualizarDatos(@Valid DatosActualizarPaciente datosActualizarPaciente) {
		if(datosActualizarPaciente.nombre()!=null) {
			this.nombre=datosActualizarPaciente.nombre();
		}
		if(datosActualizarPaciente.email()!=null) {
			this.email=datosActualizarPaciente.email();
		}
		if(datosActualizarPaciente.documento()!=null) {
			this.documento=datosActualizarPaciente.documento();
		}
		if(datosActualizarPaciente.telefono()!=null) {
			this.telefono=datosActualizarPaciente.telefono();
		}
		if(datosActualizarPaciente.direccion()!=null) {
			this.direccion=direccion.actualizarDatos(datosActualizarPaciente.direccion());
		}
		
		
		
		
		
		
		
	}

	public void desactivarPaciente() {
		this.activo=false;
		
	}
	
	
}
