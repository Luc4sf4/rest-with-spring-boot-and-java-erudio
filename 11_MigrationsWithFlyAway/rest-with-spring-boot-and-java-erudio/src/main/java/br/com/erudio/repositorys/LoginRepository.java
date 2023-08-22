package br.com.erudio.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.erudio.model.Login;
import br.com.erudio.model.Perfil;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{

	@Query("Selec 1 FROM Login 1 WHERE 1.perfil = :perfil")
	List<Login> findByPerfil(@Param("perfil") Perfil perfil);
	
	  List<Login> findByPerfilId(Long perfilId);
	
}
