package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
public class PerfilServices {

	private Logger logger = Logger.getLogger(PerfilServices.class.getName());

	@Autowired
	private PerfilRepository repository;

	@Autowired
	private LoginRepository loginRepository;

	public PerfilVo create(PerfilVo perfil) {
		logger.info("Creating a new Perfil ");

		var entity = DozerMapper.parseObject(perfil, Perfil.class);

		var vo = DozerMapper.parseObject(repository.save(entity), PerfilVo.class);

		return vo;
	}

	public PerfilVo update(PerfilVo perfil) {

		logger.info("updating one Perfil");

		var entity = repository.findById(perfil.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Not found by this id"));

		entity.setNome(perfil.getNome());

		if (perfil.getParent() != null) {

			Perfil parenteEntity = repository.findById(perfil.getParent().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Parent Perfil not found by id "));
			entity.setParent(parenteEntity);

		} else {

			entity.setParent(null);

		}

		if (perfil.getUsuarios() != null) {

			List<Login> usuarioEntities = new ArrayList<>();

			for (LoginVO login : perfil.getUsuarios()) {

				Login usuarioEntity = loginRepository.findById(login.getId())
						.orElseThrow(() -> new ResourceNotFoundException("User not found by id: " + login.getId()));

				usuarioEntities.add(usuarioEntity);

			}
			entity.setUsuarios(usuarioEntities);

		} else {

			entity.setUsuarios(null);

		}

		var vo = DozerMapper.parseObject(repository.save(entity), PerfilVo.class);

		return vo;

	}

	public void delete(Long id) {

		logger.info("deleting Perfil! ");

		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found by this id "));

		repository.delete(entity);

	}

	public List<PerfilVo> findall() {

		logger.info("Find all Perfil ");

		return DozerMapper.parseListObjects(repository.findAll(), PerfilVo.class);

	}

	public PerfilVo findById(Long id) {

		logger.info("Find one PerfilVo ");

		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not founded"));

		return DozerMapper.parseObject(entity, PerfilVo.class);

	}

}
