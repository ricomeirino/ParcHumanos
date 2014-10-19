package modelo;

import java.io.Serializable;
import java.util.Date;
/**Classe que faz o mapeamento da tabela projeto da base de dados
 * @author Ricardo
 * 
 */
public class Atividade implements Serializable {

	private static final long serialVersionUID = -3649194778475658434L;

	private int idAtividade;
	private String codigo;
	private String nome;
	private Date inicio;
	private Date fim;
	private boolean finalizada;
	private Projeto projeto;

	public int getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(int idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

}
