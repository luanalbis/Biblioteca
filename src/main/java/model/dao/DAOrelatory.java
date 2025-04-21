package model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.entity.Livro;
import model.relatory.AutorRelatory;
import model.relatory.GeneroRelatory;
import model.relatory.LidosRelatory;
import model.relatory.MainRelatory;
import model.relatory.NaoLidosRelatory;

public class DAOrelatory {

	public MainRelatory mainRelatory(ArrayList<Livro> livros) {
		ArrayList<AutorRelatory> relatorioAutor = autorData(livros);
		ArrayList<GeneroRelatory> relatorioGenero = generoData(livros);
		ArrayList<LidosRelatory> relatorioLidos = lidosData(livros);
		ArrayList<NaoLidosRelatory> relatorioNaoLidos = naoLidosData(livros);

		return new MainRelatory(livros, relatorioAutor, relatorioGenero, relatorioLidos, relatorioNaoLidos);

	}

	public ArrayList<AutorRelatory> autorData(ArrayList<Livro> livros) {
		ArrayList<AutorRelatory> lista = new ArrayList<AutorRelatory>();

		HashMap<String, int[]> autoresMap = new HashMap<String, int[]>();

		for (Livro l : livros) {
			String autor = l.getAutor();

			if (!autoresMap.containsKey(autor)) {
				autoresMap.put(autor, new int[] { 0, 0 });
			}
			int[] contadores = autoresMap.get(autor);
			contadores[0]++;
			if ("Lido".equalsIgnoreCase(l.getEstado())) {
				contadores[1]++;
			}

		}
		for (Map.Entry<String, int[]> entry : autoresMap.entrySet()) {
			String autor = entry.getKey();
			int total = entry.getValue()[0];
			int lidos = entry.getValue()[1];

			lista.add(new AutorRelatory(autor, lidos, total));
		}

		return lista;
	}

	public ArrayList<GeneroRelatory> generoData(ArrayList<Livro> livros) {
		ArrayList<GeneroRelatory> lista = new ArrayList<GeneroRelatory>();
		HashMap<String, int[]> generoMap = new HashMap<String, int[]>();

		for (Livro l : livros) {
			String genero = l.getGenero();
			if (!generoMap.containsKey(genero)) {
				generoMap.put(genero, new int[] { 0, 0 });
			}

			int[] controladores = generoMap.get(genero);
			controladores[0]++;

			if ("Lido".equalsIgnoreCase(l.getEstado())) {
				controladores[1]++;
			}
		}

		for (Map.Entry<String, int[]> entry : generoMap.entrySet()) {
			String genero = entry.getKey();
			int total = entry.getValue()[0];
			int lidos = entry.getValue()[1];

			lista.add(new GeneroRelatory(genero, lidos, total));
		}

		return lista;
	}

	public ArrayList<LidosRelatory> lidosData(ArrayList<Livro> livros) {
		ArrayList<LidosRelatory> lista = new ArrayList<LidosRelatory>();
		for (Livro l : livros) {
			if (l.getEstado().equals("Lido")) {
				lista.add(new LidosRelatory(l.getTitulo(), l.getAutor(), l.getGenero()));
			}
		}
		return lista;
	}

	public ArrayList<NaoLidosRelatory> naoLidosData(ArrayList<Livro> livros) {
		ArrayList<NaoLidosRelatory> lista = new ArrayList<NaoLidosRelatory>();
		for (Livro l : livros) {
			if (l.getEstado().equals("Não Lido")) {
				lista.add(new NaoLidosRelatory(l.getTitulo(), l.getAutor(), l.getGenero()));
			}
		}
		return lista;
	}

}
