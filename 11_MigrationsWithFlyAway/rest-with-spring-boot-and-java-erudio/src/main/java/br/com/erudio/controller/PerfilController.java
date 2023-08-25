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

import br.com.erudio.data.vo.v1.PerfilVo;
import br.com.erudio.services.PerfilServices;

@RestController
@RequestMapping("/api/perfil")
public class PerfilController {

	@Autowired
	private PerfilServices services;

	

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PerfilVo findById(@PathVariable(value = "id") Long id) {
		PerfilVo perfil = services.findById(id);
		return perfil;
	}

	@GetMapping()
	public List<PerfilVo> findAll() {

		List<PerfilVo> perfis = services.findall();

		return perfis;

	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PerfilVo create(@RequestBody PerfilVo perfil) {

		if (perfil.getParent() != null && perfil.getParent().getId() != null) {
			PerfilVo parentPerfil = services.findById(perfil.getParent().getId());
			perfil.setParent(parentPerfil);
		}

		PerfilVo created = services.create(perfil);

		return created;
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PerfilVo update(@PathVariable(value = "id") Long id, @RequestBody PerfilVo perfil) {
		PerfilVo updatedPerfil = services.update(perfil);

		return updatedPerfil;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		services.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	 @PostMapping("/{perfilId}/parent/{parentId}")
	    public PerfilVo addParentToPerfil(
	    		@PathVariable Long perfilId, @PathVariable Long parentId) {
	        PerfilVo perfilVo = services.addParentToPerfil(perfilId, parentId);
	        
	        return perfilVo;
	    }

	
	
}