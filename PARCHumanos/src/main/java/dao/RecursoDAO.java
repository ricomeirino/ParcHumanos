package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import modelo.Recurso;

public class RecursoDAO {
	private Connection con;

	// TODO: implementar métodos de acesso a dados de alocacao

	/**
	 * Método para inserir um recurso na base de dados
	 * 
	 * @param recurso
	 *            Recurso - recurso a ser inserido
	 * @return boolean - se true o recurso foi inserido com sucesso, se false
	 *         ocorreu falha na inclusão do recurso
	 */
	public boolean insereRecurso(Recurso recurso) {
		boolean retorno = false;
		this.con = new ConnectionFactory().getConnection();

		return retorno;
	}

	/**
	 * Método para remover um recurso na base de dados
	 * 
	 * @param recurso
	 *            Recurso - recurso a ser removido
	 * @return boolean - se true o recurso foi removido com sucesso, se false
	 *         ocorreu falha na remoção do recurso
	 */

	public boolean removeRecurso(Recurso recurso) {
		boolean retorno = false;
		this.con = new ConnectionFactory().getConnection();

		return retorno;
	}

	/**
	 * Método para atualizar um recurso na base de dados
	 * 
	 * @param recurso
	 *            Recurso - recurso a ser atualizado
	 * @return boolean - se true o recurso foi atualizado com sucesso, se false
	 *         ocorreu falha na atualização do recurso
	 */

	public boolean atualizaRecurso(Recurso recurso) {
		boolean retorno = false;
		this.con = new ConnectionFactory().getConnection();

		return retorno;
	}

	
	/**
	 * Método para listar os recursos existentes na base de dados
	 * 
	 * @return List<Recurso> - lista com os recurso que existem na base de dados
	 */

	public List<Recurso> listaDeRecursos() {
		List<Recurso> recursos = new ArrayList<Recurso>();
		this.con = new ConnectionFactory().getConnection();

		return recursos;
	}

	/**
	 * Método para listar os recursos sem alocação existentes na base de dados
	 * 
	 * @return List<Recurso> - lista com os recurso sem alocação que existem na base de dados
	 */

	public List<Recurso> listaRecursosDesalocados() {
		List<Recurso> recursosDesalocados = new ArrayList<Recurso>();
		this.con = new ConnectionFactory().getConnection();

		return recursosDesalocados;
	}
	
}
