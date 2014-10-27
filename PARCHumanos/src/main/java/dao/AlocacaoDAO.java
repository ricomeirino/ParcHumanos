package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Atividade;
import modelo.Recurso;
import connection.ConnectionFactory;

public class AlocacaoDAO {

	private Connection con;

	/**
	 * Método para verificar se um recurso está alocado a uma atividade
	 * 
	 * @param recurso
	 *            Recurso - recurso a ser verificado
	 * @return boolean - se true indica que o recurso está alocado, se false
	 *         indica que o recurso NÃO está alocado
	 */
	public boolean recursoEstaAlocado(Recurso recurso) {
		boolean retorno = false;

		this.con = new ConnectionFactory().getConnection();

		String sql = "select idalocacao from alocacao "
				+ " where recursoId = ?";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, recurso.getIdRecurso());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				retorno = true;
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return retorno;
	}
	
	/**
	 * Método para verificar se uma atividade está com recurso alocado
	 * 
	 * @param atividade
	 *            Atividade - atividade a ser verificada
	 * @return boolean - se true indica que a atividade está com recurso alocado, se false
	 *         indica que a atividade NÃO está com recurso alocado
	 */
	public boolean atividadeEstaAlocada(Atividade atividade) {
		boolean retorno = false;

		this.con = new ConnectionFactory().getConnection();

		String sql = "select idalocacao from alocacao "
				+ " where atividadeId = ?";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, atividade.getIdAtividade());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				retorno = true;
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return retorno;
	}	
}
