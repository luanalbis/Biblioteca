package model.relatory;

public class AutorRelatory {

	private String autor;
	private int totalLidos;
	private int total;

	public AutorRelatory() {
		super();
	}
	
	public AutorRelatory(String autor, int estado) {
		super();
		this.autor = autor;
		this.totalLidos = estado;
	}

	public AutorRelatory(String autor, int estado, int total) {
		super();
		this.autor = autor;
		this.totalLidos = estado;
		this.total = total;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getEstado() {
		return totalLidos;
	}

	public void setEstado(int estado) {
		this.totalLidos = estado;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
