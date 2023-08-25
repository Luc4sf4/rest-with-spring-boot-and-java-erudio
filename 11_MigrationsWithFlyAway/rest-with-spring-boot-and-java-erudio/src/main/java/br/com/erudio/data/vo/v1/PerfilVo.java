package br.com.erudio.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

public class PerfilVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2473058391576133894L;


	private Long id;
	private String nome;

	private PerfilVo parent;

	
	
	public PerfilVo() {}
	
	public PerfilVo(Long id, String nome, PerfilVo parent) {
		
		this.id = id;
		this.nome = nome;
		this.parent = parent;
		
		
	}

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


	public PerfilVo getParent() {
		return parent;
	}

	public void setParent(PerfilVo parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, parent);
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
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && Objects.equals(parent, other.parent);
	}

	


	
	
	
	
	
	
}
