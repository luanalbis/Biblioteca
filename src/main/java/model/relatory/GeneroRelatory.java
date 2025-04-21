package model.relatory;

public class GeneroRelatory {
	private String genero;
	private int totalLidos;
	private int total;

	public GeneroRelatory(String genero, int totalLidos, int total) {
		super();
		this.genero = genero;
		this.totalLidos = totalLidos;
		this.total = total;
	}

	public GeneroRelatory() {
		super();
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getTotalLidos() {
		return totalLidos;
	}

	public void setTotalLidos(int totalLidos) {
		this.totalLidos = totalLidos;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
