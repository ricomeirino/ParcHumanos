package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Alocacao;
import modelo.Atividade;
import modelo.Ocupacao;
import modelo.Projeto;
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
	
	/**
	 * Método para listar as alocações de um projeto
	 * 
	 * @return List<Alocacao> - lista com as alocações das atividades do projeto existentes na base
	 */

	public List<Alocacao> listaAlocacoes(Projeto projetoSelecionado) {
		List<Alocacao> alocacoes = new ArrayList<Alocacao>();
		this.con = new ConnectionFactory().getConnection();

		String sql = "select al.idalocacao, at.idatividade, at.codigo, at.nome nomeAtv, at.inicio, at.fim, at.finalizada,"
				+ " r.idRecurso, r.matricula, r.nome nomeRec, o.idocupacao, o.nome nomeOcup "
				+ " from alocacao al "
				+ " inner join atividade at on (al.atividadeId = at.idatividade) "
				+ " inner join recurso r on (al.recursoId = r.idRecurso) "
				+ " inner join ocupacao o on (r.ocupacaoId = o.idocupacao) "
				+ " where at.projetoId = ? "
				+ " order by at.codigo";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Atividade atividade = new Atividade();
				atividade.setIdAtividade(rs.getInt("idatividade"));
				atividade.setCodigo(rs.getString("codigo"));
				atividade.setNome(rs.getString("nomeAtv"));
				atividade.setInicio(rs.getDate("inicio"));
				atividade.setFim(rs.getDate("fim"));
				atividade.setFinalizada(rs.getBoolean("finalizada"));
				
				
				Recurso recurso = new Recurso();
				Ocupacao ocupacao = new Ocupacao();
				recurso.setIdRecurso(rs.getInt("idRecurso"));
				recurso.setNome(rs.getString("nomeRec"));
				recurso.setMatricula(rs.getString("matricula"));
				ocupacao.setIdOcupacao(rs.getInt("idocupacao"));
				ocupacao.setNome(rs.getString("nomeOcup"));
				recurso.setOcupacao(ocupacao);

				Alocacao alocacao = new Alocacao();
				alocacao.setIdAlocacao(rs.getInt("idalocacao"));
				
				alocacao.setAtividade(atividade);
				alocacao.setRecurso(recurso);
				
				alocacoes.add(alocacao);
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return alocacoes;
	}
	
}
