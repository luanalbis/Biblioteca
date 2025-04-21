package model.relatory;

public class NaoLidosRelatory {

	private String livro;
	private String autor;
	private String genero;

	public NaoLidosRelatory(String livro, String autor, String genero) {
		super();
		this.livro = livro;
		this.autor = autor;
		this.genero = genero;
	}

	public NaoLidosRelatory() {
		super();
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
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
