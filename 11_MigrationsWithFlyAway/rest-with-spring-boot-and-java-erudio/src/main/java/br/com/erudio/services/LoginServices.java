package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.LoginVO;
import br.com.erudio.data.vo.v1.PerfilVo;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Login;
import br.com.erudio.model.Perfil;
import br.com.erudio.repositorys.LoginRepository;
import br.com.erudio.repositorys.PerfilRepository;



@Service
public class LoginServices {

	private Logger logger = Logger.getLogger(LoginServices.class.getName());

	@Autowired
	private PerfilRepository perfilRepository;
	
	
	@Autowired
	private LoginRepository repository;
	
	public LoginVO create(LoginVO loginVo) {
		logger.info("Createing one person");
		
		Login entity = new Login();
		entity.setUsername(loginVo.getUsername());
		
		Login savedLogin = repository.save(entity);
		
		LoginVO savedLoginVo = new LoginVO();
		savedLoginVo.setId(savedLogin.getId());
		savedLoginVo.setPassword(savedLogin.getPassword());
		savedLoginVo.setStatus(savedLogin.getStatus());
		
		return savedLoginVo;
	}
	
		
		public LoginVO update(LoginVO login){
			
			logger.info("Updating one Login! ");
			
			Login entity = repository.findById(login.getId()).
					orElseThrow(() -> new ResourceNotFoundException("Not found for this id "));
			
			entity.setUsername(login.getUsername());
			entity.setPassword(login.getPassword());
			entity.setStatus(login.getStatus());
			
			Login updatedLogin = repository.save(entity);
			
			LoginVO updatedLoginVo = new LoginVO();
			updatedLoginVo.setId(updatedLogin.getId());			
			updatedLoginVo.setUsername(updatedLogin.getUsername());
			updatedLoginVo.setPassword(updatedLogin.getPassword());
			updatedLoginVo.setStatus(updatedLogin.getStatus());
			updatedLogin.setPerfil(updatedLogin.getPerfil());
			
			return updatedLoginVo;
			
		}
	
		public void delete(Long id) {
		    logger.info("Deleting perfil with ID: " + id);

		    Login entity = repository.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException("Perfil not found with id: " + id));

		    repository.delete(entity);
		}
	
	public List<LoginVO> findAll(){
		
		logger.info("Find all logins ");
	
		List<Login> logins = repository.findAll();
	
		List<LoginVO> loginVos = logins.stream().map(
	 login -> {
			
		LoginVO loginVo = new LoginVO();	
		loginVo.setId(login.getId());	
		loginVo.setUsername(login.getUsername());
		loginVo.setStatus(login.getStatus());
		loginVo.setPerfilVo(null);
		return loginVo;
		
		}).collect(Collectors.toList());
	
		return loginVos;
		
	}
	
	public LoginVO findByID(Long id){
		
		logger.info("Finding one LoginVo");
		
		Login entity = repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Not found for this id "));
		
		LoginVO loginVo = new LoginVO();
		loginVo.setId(entity.getId());
		loginVo.setUsername(entity.getUsername());
		loginVo.setPassword(entity.getPassword());
		loginVo.setStatus(entity.getStatus());
		
		
		return loginVo;         
		
	}
	
	public LoginVO addPerfilToUsuario(Long perfilId, Long usuarioId) {
        Perfil perfil = perfilRepository.findById(perfilId)
            .orElseThrow(() -> new ResourceNotFoundException("Perfil not found with id: " + perfilId));

        Login usuario = repository.findById(usuarioId)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id: " + usuarioId));

        usuario.setPerfil(perfil);
        
        
        
        Login updateLogin = repository.save(usuario);
        
        
        
        /*
         Pegar o parent e transformar em Vo
         Parent está na classe Perfil 
         usando o "updatedlogin.getPerfil.getParent()"
         usando uma condição como esta: if(updatedlogin.getPerfil().getParent() == null ){}
         
         */
        
        PerfilVo parentVo  = null;
  
        if(updateLogin.getPerfil().getParent() != null) {
        	
        	parentVo = new PerfilVo(updateLogin.getPerfil().getParent().getId(), updateLogin.getPerfil().getNome(), null);
        	
        }
        
        
        PerfilVo perfilVo = new PerfilVo(updateLogin.getPerfil().getId(), updateLogin.getPerfil().getNome(), parentVo);

        LoginVO loginVo =  new LoginVO(updateLogin.getId(),updateLogin.getUsername(),updateLogin.getPassword(), updateLogin.getStatus(), perfilVo);
        
        loginVo.setPerfilVo(perfilVo);
        
        return loginVo;
    }

	
	
	
}
