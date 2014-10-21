package controller;

import java.util.List;

import modelo.Projeto;
import util.Mensagem;
import dao.ProjetoDAO;

public class CadastroProjetoControl {

	public List<Projeto> listaDeProjetos() {
		ProjetoDAO projetoDAO = new ProjetoDAO();
		List<Projeto> projetos = projetoDAO.getProjetos();
		return projetos;
	}

	public Mensagem insereProjeto(Projeto projetoNovo) {
		String msg = null;
		ProjetoDAO projetoDAO = new ProjetoDAO();
		// testar se existe projeto com mesmo código
		Projeto projeto = projetoDAO.buscaProjetoPorCodigo(projetoNovo
				.getCodigo());
		int codigoMsg;
		if (projeto != null) {
			codigoMsg = 1;
			msg = "Projeto com código " + projetoNovo.getCodigo()
					+ " já existe.";
		} else {
			boolean inseriu = projetoDAO.insertProjeto(projetoNovo);
			if (inseriu) {
				codigoMsg = 0;
				msg = "Projeto inserido com sucesso";
				projetoNovo = projetoDAO.buscaProjetoPorCodigo(projetoNovo
						.getCodigo());
			} else {
				codigoMsg = 2;
				msg = "Falha na inclusão do projeto. Tente novamente.";
			}
		}

		Mensagem mensagem = new Mensagem(codigoMsg, msg);

		return mensagem;
	}

	public Mensagem deleteProjeto(Projeto projeto) {
		String msg = null;
		ProjetoDAO projetoDAO = new ProjetoDAO();

		boolean retorno = projetoDAO.deleteProjeto(projeto);
		int codigoMsg;
		if (retorno) {
			codigoMsg = 0;
			msg = "Projeto excluído com sucesso";
		} else {
			codigoMsg = 3;
			msg = "Falha na exclusão do projeto. Tente novamente.";
		}

		Mensagem mensagem = new Mensagem(codigoMsg, msg);

		return mensagem;
	}
	
	public Mensagem atualizaProjeto(Projeto projetoEditado){
		String msg = null;
		ProjetoDAO projetoDAO = new ProjetoDAO();
		Projeto projetoNaBase = projetoDAO.buscaProjetoPorCodigo(projetoEditado.getCodigo());
		
		int codigoMsg;
		
		// se for o mesmo projeto ou não tiver projeto com o mesmo código -> pode alterar
		
		if (projetoNaBase == null || projetoNaBase.getIdProjeto() == projetoEditado.getIdProjeto()){
			boolean retorno = projetoDAO.updateProjeto(projetoEditado);
			if (retorno) {
				codigoMsg = 0;
				msg = "Projeto atualizado com sucesso";
			} else {
				codigoMsg = 4;
				msg = "Falha na atualização do projeto. Tente novamente.";
			}

		}else{ // se não for o mesmo projeto -> não pode alterar pois o código de projeto já existe
			codigoMsg = 1;
			msg = "Projeto com código " + projetoEditado.getCodigo()
					+ " já existe.";
		}
				
		Mensagem mensagem = new Mensagem(codigoMsg, msg);

		return mensagem;
	}
}
