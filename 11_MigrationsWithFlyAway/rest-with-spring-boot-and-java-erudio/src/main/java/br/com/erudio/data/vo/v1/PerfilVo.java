package br.com.erudio.data.vo.v1;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PerfilVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2473058391576133894L;


	private Long id;
	private String nome;
	private List<LoginVO> usuarios;
	private PerfilVo parent;
	private List<LoginVO> loginVo;
	
	
	public  PerfilVo() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<LoginVO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<LoginVO> usuarios) {
		this.usuarios = usuarios;
	}

	public PerfilVo getParent() {
		return parent;
	}

	public void setParent(PerfilVo parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, parent, usuarios);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilVo other = (PerfilVo) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && Objects.equals(parent, other.parent)
				&& Objects.equals(usuarios, other.usuarios);
	}

	public List<LoginVO> getLoginVo() {
		return loginVo;
	}

	public void setLoginVo(List<LoginVO> loginVo2) {
		this.loginVo = loginVo2;
	}
	
	
	
	
	
}
