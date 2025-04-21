package model.entity;

public class Livro {
	private String idcadastro;
	private String titulo;
	private String autor;
	private String genero;
	private String estado;
	private String descricao;
	private String iduser;

	public Livro() {
		super();
	}

	public Livro(String id, String titulo, String autor, String genero, String estado, String descricao,
			String iduser) {
		super();
		this.idcadastro = id;
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		this.estado = estado;
		this.descricao = descricao;
		this.iduser = iduser;
	}

	public String getIdcadastro() {
		return idcadastro;
	}

	public void setIdcadastro(String id) {
		this.idcadastro = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}

	@Override
	public String toString() {
		return "Livro [id=" + idcadastro + ", titulo=" + titulo + ", autor=" + autor + ", genero=" + genero
				+ ", estado=" + estado + ", descricao=" + descricao + "]";
	}

}
