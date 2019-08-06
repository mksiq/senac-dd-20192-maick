package model.entity;

public class Telefone {

	private int idTelefone;
	private String codigoPais;
	private String ddd;
	private String numero;
	private String tipoLinha;
	private boolean ativo;
	private int idCliente;
	
	public Telefone() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdTelefone() {
		return idTelefone;
	}
	public void setIdTelefone(int idTelefone) {
		this.idTelefone = idTelefone;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipoLinha() {
		return tipoLinha;
	}
	public void setTipoLinha(String tipoLinha) {
		this.tipoLinha = tipoLinha;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public Telefone(int idTelefone, String codigoPais, String ddd, String numero, String tipoLinha, boolean ativo,
			int idCliente) {
		super();
		this.idTelefone = idTelefone;
		this.codigoPais = codigoPais;
		this.ddd = ddd;
		this.numero = numero;
		this.tipoLinha = tipoLinha;
		this.ativo = ativo;
		this.idCliente = idCliente;
	}
	@Override
	public String toString() {
		String  situacao;
		if  (ativo) {
			situacao = "ativo";
		} else {
			situacao = "nativo";
		}
	
		return "\n"+codigoPais+ "(" + ddd + ")" + numero + ", tipo: " + tipoLinha + ", " + situacao;
	}
	
}
