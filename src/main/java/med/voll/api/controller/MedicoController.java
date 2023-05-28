package med.voll.api.controller;

import java.util.List;

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
import med.voll.api.medico.DatosActualizarMedico;
import med.voll.api.medico.DatosListadoMedico;
import med.voll.api.medico.DatosRegistroMedicos;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@PostMapping
	public void registrarMedico(@RequestBody @Valid DatosRegistroMedicos datosRegistroMedicos) {
		//System.out.println(parametro);
		//System.out.println(parametro.nombre());
		//System.out.println(parametro.direccion().ciudad());
		medicoRepository.save(new Medico(datosRegistroMedicos));
	}
	
	@GetMapping
	public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size=5) Pageable paginacion){
		//return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
		return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
		
	}
	
	@PutMapping
	@Transactional
	public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
		Medico medico=medicoRepository.getReferenceById(datosActualizarMedico.id());
		medico.actualizarDatos(datosActualizarMedico);
	}

	//Delete Base Datos	
//	@DeleteMapping("/{id}")
//	@Transactional
//	public void eliminarMedico(@PathVariable Long id) {
//		Medico medico=medicoRepository.getReferenceById(id);
//		medicoRepository.delete(medico);
//	}
	
	
//Delete logico
	@DeleteMapping("/{id}")
	@Transactional
	public void eliminarMedico(@PathVariable Long id) {
		Medico medico=medicoRepository.getReferenceById(id);
		medico.desactivarMedico();
	}
	
	
}


