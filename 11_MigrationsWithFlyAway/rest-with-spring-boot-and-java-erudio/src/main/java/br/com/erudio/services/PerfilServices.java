package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PerfilVo;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Perfil;
import br.com.erudio.repositorys.PerfilRepository;

@Service
public class PerfilServices {

	private Logger logger = Logger.getLogger(PerfilServices.class.getName());

	@Autowired
	private PerfilRepository repository;


	public PerfilVo create(PerfilVo perfilVo) {
		logger.info("Creating a new Perfil ");

	
		Perfil entity = new Perfil();
		entity.setNome(perfilVo.getNome());
		
		Perfil savedPerfil = repository.save(entity);
		
		PerfilVo savedPerfilVo = new PerfilVo();
		savedPerfilVo.setId(savedPerfil.getId());
		savedPerfilVo.setNome(savedPerfil.getNome());
		
		
		return savedPerfilVo;
	}

	
	public PerfilVo update(PerfilVo perfil) {

		logger.info("updating one Perfil");

		Perfil entity = repository.findById(perfil.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Not found by this id"));
	       
		entity.setNome(perfil.getNome());
		
		
        Perfil updatedperfil = repository.save(entity);

        
       
      
        PerfilVo updatedPerfilVo = new PerfilVo(updatedperfil.getParent().getId(), updatedperfil.getParent().getNome(), null );

		return updatedPerfilVo;

	}

	public void delete(Long id) {

		logger.info("deleting Perfil! ");

		Perfil entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found by this id "));

		repository.delete(entity);

	}

	public List<PerfilVo> findall() {

		logger.info("Find all Perfil ");

		List<Perfil> perfis = repository.findAll();
		
		List<PerfilVo> perfilVos = perfis.stream()
				.map(
			perfil -> {
			PerfilVo perfilVo = new PerfilVo();
			perfilVo.setId(perfil.getId());
			perfilVo.setNome(perfil.getNome());
			return perfilVo;
			
		}).collect(Collectors.toList());
		
		return perfilVos;
	}

	public PerfilVo findById(Long id) {

		logger.info("Find one PerfilVo ");

		Perfil entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not founded"));

		PerfilVo perfilVo = new PerfilVo();
		perfilVo.setId(entity.getId());
		perfilVo.setNome(entity.getNome());
		

		return perfilVo;
	}
	
	public PerfilVo addParentToPerfil(Long perfilId, Long parentId) {
        Perfil perfil = repository.findById(perfilId)
            .orElseThrow(() -> new ResourceNotFoundException("Perfil not found with id: " + perfilId));

        Perfil parent = repository.findById(parentId)
        		.orElseThrow(() -> new ResourceNotFoundException("Perfil not found with id: " + parentId));

        
        perfil.setParent(parent);
        
        
       

        Perfil updatedperfil = repository.save(perfil);

        
       
      
        PerfilVo parentvo = new PerfilVo(updatedperfil.getParent().getId(), updatedperfil.getParent().getNome(), null );

        //contructor what i wanna get   //the parametters	
        PerfilVo perfilvo = new PerfilVo(updatedperfil.getId(),updatedperfil.getNome(), parentvo);
               
        return perfilvo;
    }
 

}
