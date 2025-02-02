package com.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.dominio.Users;
import com.users.repository.IUsersRep;

@Service
public class UsersImp {
	
	@Autowired
	private IUsersRep rep;
	
	public Users nuevo(Users user) {
		return rep.save(user);
	}
	
	public List<Users> mostar(){
		return (List<Users>)rep.findAll();
	}
	
	public void editar(Users user) {
		rep.save(user);
	}
	public Users buscarPorId(long id) {
		return rep.findById((int) id).orElse(null);
	}
	public void eliminar(Users user) {
		rep.delete(user);
	}

}
