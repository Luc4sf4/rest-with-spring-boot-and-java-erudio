package br.com.erudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/perfil")
public class PerfilController {

	@Autowired
	private PerfilServices services;
	
	@Autowired
	private LoginServices loginServices;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PerfilVo findById(@PathVariable(value = "id") Long id) {
	    PerfilVo perfil = services.findById(id);
	    List<LoginVO> loginVoList = loginServices.findByPerfilId(id);
	    perfil.setLoginVo(loginVoList);
	    return perfil;
	}
	
	@GetMapping()
	public List<PerfilVo> findAll(){
		
		List<PerfilVo> perfis = services.findall();
		
		for(PerfilVo perfil : perfis){
		
			List<LoginVO> loginVo= loginServices.findByPerfilId(perfil.getId());
			
			perfil.setLoginVo(loginVo);
			
		}
		
		
		return perfis;
		
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PerfilVo create(@RequestBody PerfilVo perfil) {
	    PerfilVo created = services.create(perfil);

	    if (perfil.getLoginVo() != null) {
	        for (LoginVO loginVo : perfil.getLoginVo()) {
	            loginVo.setPerfilVo(created); // Ajuste esta linha
	            LoginVO createdLogin = loginServices.create(loginVo);
	            created.getLoginVo().add(createdLogin);
	        }
	    }

	    return created;
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PerfilVo update(@PathVariable(value = "id") Long id, @RequestBody PerfilVo perfil) {
	    PerfilVo updatedPerfil = services.update(perfil);

	    if (perfil.getLoginVo() != null) {
	        for (LoginVO loginVo : perfil.getLoginVo()) {
	            loginVo.setPerfilVo(updatedPerfil);
	            if (loginVo.getId() != null) {
	                loginServices.update(loginVo); 
	            } else {
	                LoginVO createdLogin = loginServices.create(loginVo); 
	                updatedPerfil.getLoginVo().add(createdLogin);
	            }
	        }
	    }

	    return updatedPerfil;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    services.delete(id);
	    return ResponseEntity.noContent().build();
	}
	
	
	
}
