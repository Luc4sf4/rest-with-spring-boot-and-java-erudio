package br.com.erudio.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "perfil")
    private List<Login> usuarios;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Perfil parent;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Login> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Login> usuarios) {
		this.usuarios = usuarios;
	}

	public Perfil getParent() {
		return parent;
	}

	public void setParent(Perfil parent) {
		this.parent = parent;
	}

   
}