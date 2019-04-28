/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Date;



public class Fornecedor extends Pessoa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cnpj;
	private String nomeContato;
	
	public Fornecedor(int codigo, String nome, String telefone, String email, Date dataCadastrada,String cnpj,String nomeContato) {
		super(codigo, nome, telefone, email, dataCadastrada);
		this.cnpj = cnpj;
		this.nomeContato = nomeContato;
	}
	/**
	 * Construtor vazio
	 */
	public Fornecedor() {
		
	}

	/**
	 * M�todo toString
	 */
	public String toString() 
	{
		return super.toString() +
		"CNPJ    : " + cnpj + "\n" +
		"Nome contato  : " + nomeContato+ "\n";
	}

	/**
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * @return the nomeContato
	 */
	public String getNomeContato() {
		return nomeContato;
	}

	/**
	 * @param nomeContato the nomeContato to set
	 */
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	@Override
	public String tipoPessoa() {
		
		return "fornecedor";
	}


}
