package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DatosListadoMedico;
import med.voll.api.medico.DatosRegistroMedicos;
import med.voll.api.medico.Medico;
import med.voll.api.paciente.DatosActualizarPaciente;
import med.voll.api.paciente.DatosListadoPaciente;
import med.voll.api.paciente.DatosRegistroPacientes;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	PacienteRepository pacienteRepository;
	
	@PostMapping
	public void registrarMedico(@RequestBody @Valid DatosRegistroPacientes datosRegistroPacientes) {
		//System.out.println(parametro);
		//System.out.println(parametro.nombre());
		//System.out.println(parametro.direccion().ciudad());
		//medicoRepository.save(new Medico(datosRegistroMedicos));
		pacienteRepository.save(new Paciente(datosRegistroPacientes));
		
	}
	
	@GetMapping
	public Page<DatosListadoPaciente> listadoPacientes(@PageableDefault(size=5) Pageable paginacion){
		//return pacienteRepository.findAll(paginacion).map(DatosListadoPaciente::new);
		return pacienteRepository.findByActivoTrue(paginacion).map(DatosListadoPaciente::new);
	}
	
	@PutMapping
	@Transactional
	public void actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente) {
		Paciente paciente=pacienteRepository.getReferenceById(datosActualizarPaciente.id());
		paciente.actualizarDatos(datosActualizarPaciente);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void EliminarPaciente(@PathVariable Long id) {
		Paciente paciente=pacienteRepository.getReferenceById(id);
		paciente.desactivarPaciente();
	}
	
}
