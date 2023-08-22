package br.com.erudio.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

public class LoginVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2940908236477755756L;

	
	private Long id;
	
	private String username;
	
	private String password;
	
	private int status;

	private PerfilVo perfilVo;
	
	public PerfilVo getPerfilVo() {
		return perfilVo;
	}


	public void setPerfilVo(PerfilVo perfilVo) {
		this.perfilVo = perfilVo;
	}


	public LoginVO() {}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, status, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginVO other = (LoginVO) obj;
		return Objects.equals(id, other.id) && status == other.status && Objects.equals(username, other.username);
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
