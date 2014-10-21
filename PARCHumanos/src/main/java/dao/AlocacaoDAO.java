package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Recurso;
import connection.ConnectionFactory;

public class AlocacaoDAO {

	private Connection con;
	
	//TODO: implementar métodos de acesso a dados de alocacao
	
	public boolean recursoEstaAlocado(Recurso recurso){
		boolean retorno = false;

		this.con = new ConnectionFactory().getConnection();

		String sql = "select idalocacao "
				+ " from alocacao "
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
}
