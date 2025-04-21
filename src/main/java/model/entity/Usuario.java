package model.entity;

public class Usuario {
	private String idusuario;
	private String nome;
	private String email;
	private String senha;
	private byte[] foto;

	@Override
	public String toString() {
		return "Usuario [idusuario=" + idusuario + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "foto =]"+foto;
	}

	public Usuario() {
		super();

	}
	
	public Usuario(String idusuario, String nome, String email) {
		super();
		this.idusuario = idusuario;
		this.nome = nome;
		this.email = email;
	}

	public Usuario(String idusuario, String nome, String email, String senha) {
		super();
		this.idusuario = idusuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public Usuario(String idusuario, String nome, String email, String senha ,byte[] foto) {
		super();
		this.idusuario = idusuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.foto = foto;
	}

	public String getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	

}
