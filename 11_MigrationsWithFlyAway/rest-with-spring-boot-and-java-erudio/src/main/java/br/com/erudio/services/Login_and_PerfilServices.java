package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PerfilVo;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Login;
import br.com.erudio.model.Perfil;
import br.com.erudio.repositorys.LoginRepository;
import br.com.erudio.repositorys.PerfilRepository;

@Service
public class Login_and_PerfilServices {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private PerfilRepository perfilRepository;

	public PerfilVo addUser(Long perfilId, Long usuarioId) {
		Perfil perfil = perfilRepository.findById(perfilId)
				.orElseThrow(() -> new ResourceNotFoundException("Perfil not found with id: " + perfilId));

		Login usuario = loginRepository.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id: " + usuarioId));

		usuario.setPerfil(perfil);
		Login updatedUsuario = loginRepository.save(usuario);

		return DozerMapper.parseObject(updatedUsuario.getPerfil(), PerfilVo.class);
	}

}
