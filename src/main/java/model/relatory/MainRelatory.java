package model.relatory;

import java.util.ArrayList;

import model.entity.Livro;

public class MainRelatory {
	private ArrayList<Livro> relatorioTotalLivros = new ArrayList<Livro>();
	private ArrayList<AutorRelatory> relatorioAutor = new ArrayList<AutorRelatory>();
	private ArrayList<GeneroRelatory> relatorioGenero = new ArrayList<GeneroRelatory>();
	private ArrayList<LidosRelatory> relatorioLidos = new ArrayList<LidosRelatory>();
	private ArrayList<NaoLidosRelatory> relatorioNaoLidos = new ArrayList<NaoLidosRelatory>();

	public MainRelatory(ArrayList<Livro> relatorioTotalLivros, ArrayList<AutorRelatory> relatorioAutor,
			ArrayList<GeneroRelatory> relatorioGenero, ArrayList<LidosRelatory> relatorioLidos,
			ArrayList<NaoLidosRelatory> relatorioNaoLidos) {
		super();
		this.relatorioTotalLivros = relatorioTotalLivros;
		this.relatorioAutor = relatorioAutor;
		this.relatorioGenero = relatorioGenero;
		this.relatorioLidos = relatorioLidos;
		this.relatorioNaoLidos = relatorioNaoLidos;
	}

	public MainRelatory() {
		super();
	}

	public ArrayList<Livro> getRelatorioTotalLivros() {
		return relatorioTotalLivros;
	}

	public void setRelatorioTotalLivros(ArrayList<Livro> relatorioTotalLivros) {
		this.relatorioTotalLivros = relatorioTotalLivros;
	}

	public ArrayList<AutorRelatory> getRelatorioAutor() {
		return relatorioAutor;
	}

	public void setRelatorioAutor(ArrayList<AutorRelatory> relatorioAutor) {
		this.relatorioAutor = relatorioAutor;
	}

	public ArrayList<GeneroRelatory> getRelatorioGenero() {
		return relatorioGenero;
	}

	public void setRelatorioGenero(ArrayList<GeneroRelatory> relatorioGenero) {
		this.relatorioGenero = relatorioGenero;
	}

	public ArrayList<LidosRelatory> getRelatorioLidos() {
		return relatorioLidos;
	}

	public void setRelatorioLidos(ArrayList<LidosRelatory> relatorioLidos) {
		this.relatorioLidos = relatorioLidos;
	}

	public ArrayList<NaoLidosRelatory> getRelatorioNaoLidos() {
		return relatorioNaoLidos;
	}

	public void setRelatorioNaoLidos(ArrayList<NaoLidosRelatory> relatorioNaoLidos) {
		this.relatorioNaoLidos = relatorioNaoLidos;
	}

}
