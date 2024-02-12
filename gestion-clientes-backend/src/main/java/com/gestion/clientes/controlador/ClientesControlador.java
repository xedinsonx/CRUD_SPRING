package com.gestion.clientes.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gestion.clientes.modelo.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.clientes.excepciones.ResourceNotFoundException;
import com.gestion.clientes.repositorio.ClientesRepositorio;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientesControlador {

	@Autowired
	private ClientesRepositorio repositorio;

	//este metodo sirve para listar todos los clientes
	@GetMapping("/clientes")
	public List<Clientes> listarTodosLosClientes() {
		return repositorio.findAll();
	}
	

	//este metodo sirve para guardar el clientes
	@PostMapping("/clientes")
	public Clientes guardarClientes(@RequestBody Clientes clientes) {
		return repositorio.save(clientes);
	}
    
	//este metodo sirve para buscar un clientes
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Clientes> obtenerClientesPorId(@PathVariable Long id){
			Clientes clientes = repositorio.findById(id)
					            .orElseThrow(() -> new ResourceNotFoundException("No existe el clientes con el ID : " + id));
			return ResponseEntity.ok(clientes);
	}
	
	//este metodo sirve para actualizar clientes
	@PutMapping("/clientes/{id}")
	public ResponseEntity<Clientes> actualizarClientes(@PathVariable Long id, @RequestBody Clientes detallesClientes){
		Clientes clientes = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el clientes con el ID : " + id));
		
		clientes.setNombre(detallesClientes.getNombre());
		clientes.setDocumento(detallesClientes.getDocumento());
		clientes.setEmail(detallesClientes.getEmail());
		
		Clientes clientesActualizado = repositorio.save(clientes);
		return ResponseEntity.ok(clientesActualizado);
    }
	
	//este metodo sirve para eliminar un empleado
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarClientes(@PathVariable Long id){
		Clientes clientes = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el clientes con el ID : " + id));
		
		repositorio.delete(clientes);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}














