package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.LoginVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Login;
import br.com.erudio.repositorys.LoginRepository;



@Service
public class LoginServices {

	private Logger logger = Logger.getLogger(LoginServices.class.getName());

	
	
	@Autowired
	private LoginRepository repository;
	
	public LoginVO create(LoginVO login) {
		logger.info("Createing one person");
		
		var entity = DozerMapper.parseObject(login, Login.class);
		
		var vo = DozerMapper.parseObject(repository.save(entity), LoginVO.class );
		
		return vo;
		
	}
	
	
	public LoginVO update(LoginVO login){
		
		logger.info("Updating one Login! ");
		
		var entity = repository.findById(login.getId()).
				orElseThrow(() -> new ResourceNotFoundException("Not found for this id "));
		
		entity.setUsername(login.getUsername());
		entity.setPassword(login.getPassword());
		entity.setStatus(login.getStatus());
		
		var vo = DozerMapper.parseObject(repository.save(entity), LoginVO.class );
		
		return vo;
		
	}
	
	public void delete(Long id) {
		
		logger.info("FInd all people! ");
		
		var entity = repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Not found for this id"));
		
		
		repository.delete(entity);
	}
	
	
	public List<LoginVO> findAll(){
		
		logger.info("Find all logins ");
		
		return DozerMapper.parseListObjects(repository.findAll(), LoginVO.class);
		
	}
	
	public LoginVO findByID(Long id){
		
		logger.info("Finding one LoginVo");
		
		var entity = repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Not found for this id "));
		
		return DozerMapper.parseObject(entity, LoginVO.class);
		
	}
	

    public List<LoginVO> findByPerfilId(Long perfilId) {
    	
        List<Login> loginEntities = repository.findByPerfilId(perfilId);

    	return DozerMapper.parseListObjects(loginEntities, LoginVO.class);
  
     }
	
	
}
