package model;

import java.io.Serializable;
import java.util.Date;


public abstract class Pessoa implements Serializable , Comparable<Pessoa>{
	/**
	 * @author Caio
	 */
	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String nome;
	private String telefone;
	private String email;
	private Date dataCad;
	

	public Pessoa(int codigo, String nome, String telefone, String email, Date dataCad) {
		this.codigo = codigo;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.dataCad = dataCad;
	}
	
	public Pessoa() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome; 
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataCad() {
		return dataCad;
	}
	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}
	
	public abstract String  tipoPessoa();
	
	
	@Override
	/**
	 * Método toString
	 */
	public String toString() 
	{
		return
		"Código    : " + codigo + "\n" +
		"Nome    : " + nome + "\n" +
		"Telefone   : " + telefone + "\n" +
		"E-mail    : " + email + "\n" +
		"Data cadastrada   : " + dataCad + "\n";
	}
	
	/**
	 * Método compareTo
	 */
	public int compareTo(Pessoa objPessoa){
		return this.nome.compareTo(objPessoa.nome);
	}
	
	
}
