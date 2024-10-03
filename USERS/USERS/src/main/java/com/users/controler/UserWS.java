package com.users.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.dominio.Users;
import com.users.service.UsersImp;



@RestController
@RequestMapping(path = "/api/users/")
public class UserWS {

	@Autowired
	private UsersImp imp;
	
	 @PostMapping
	    public ResponseEntity<Users> nuevoUsuario(@RequestBody Users user) {
	        Users newUser = imp.nuevo(user);
	        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	    }

	    @GetMapping
	    public ResponseEntity<?> mostrar() {
	        List<Users> users = imp.mostar();
	        if (users.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay registros disponibles");
	        } else {
	            return ResponseEntity.ok(users);
	        }
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<String> editar(@PathVariable long id, @RequestBody Users user) {
	        Users existingUser = imp.buscarPorId(id);
	        if (existingUser == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
	        }
	        
	        user.setId(id); // Aseguramos que el ID del usuario a editar sea correcto
	        imp.editar(user);
	        return ResponseEntity.ok("Usuario editado: " + user);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> eliminar(@PathVariable long id) {
	        Users existingUser = imp.buscarPorId(id);
	        if (existingUser == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
	        }

	        imp.eliminar(existingUser);
	        return ResponseEntity.ok("Usuario eliminado con ID: " + id);
	    }
}
