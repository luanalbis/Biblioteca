package model.relatory;

public class LidosRelatory {
	private String titulo;
	private String autor;
	private String genero;

	public LidosRelatory(String livro, String autor, String genero) {
		super();
		this.titulo = livro;
		this.autor = autor;
		this.genero = genero;
	}

	public LidosRelatory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLivro() {
		return titulo;
	}

	public void setLivro(String livro) {
		this.titulo = livro;
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

}
