package model;

import java.io.Serializable;
import java.util.Date;


public class Cliente extends Pessoa implements Serializable{
	/**
	 * @author Caio 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private double limiteCredito;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public double getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	
	public Cliente(int codigo, String nome, String telefone, String email, Date dataCadastrada, String cpf,double limiteCredito) {
		super(codigo, nome, telefone, email, dataCadastrada);
		this.cpf = cpf;
		this.limiteCredito = limiteCredito;
	}
	
	@Override
	public String toString() 
	{
		return super.toString() +
		"CPF    : " + cpf + "\n" +
		"Limite Credito    : " + limiteCredito + "\n";
	}
	
	@Override
	public String tipoPessoa() {
		return "cliente";
	}
	

}
