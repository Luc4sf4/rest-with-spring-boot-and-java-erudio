package br.com.erudio.controller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.LoginVO;
import br.com.erudio.data.vo.v1.PerfilVo;
import br.com.erudio.services.LoginServices;
import br.com.erudio.services.PerfilServices;


@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private LoginServices service;
	
	@Autowired PerfilServices perfilServices;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public LoginVO findByID(@PathVariable(value = "id") Long id) {
		
		return service.findByID(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public LoginVO create(@RequestBody LoginVO login){
		
		 if (login.getPerfilVo() != null) {
	            PerfilVo perfil = perfilServices.findById(login.getPerfilVo().getId());
	            login.setPerfilVo(perfil);
	        }
		
		
		return service.create(login);
		
	}
	
	@GetMapping()
	public List<LoginVO> findAll(){
		
		return service.findAll();
		
	}
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public LoginVO update(@RequestBody LoginVO login){
		
		 if (login.getPerfilVo() != null) {
	            PerfilVo perfil = perfilServices.findById(login.getPerfilVo().getId());
	            login.setPerfilVo(perfil);
	        }
		
		
		return service.update(login);
		
	}
	
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
}
