package br.com.erudio.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.erudio.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{
}