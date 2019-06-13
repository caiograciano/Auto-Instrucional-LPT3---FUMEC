package model;

import java.io.Serializable;
import java.util.Date;

public class Vendedor extends Pessoa implements Serializable{
	/**
	 * @author Caio
	 */
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private double metaMensal;
	
	
	@Override
	public String toString() 
	{
		return super.toString() +
		"CPF    : " + cpf + "\n" +
		"Meta mensal   : " + metaMensal+ "\n";
	}
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public double getMetaMensal() {
		return metaMensal;
	}
	public void setMetaMensal(double metaMensal) {
		this.metaMensal = metaMensal;
	}

	@Override
	public String tipoPessoa() {
		return "vendedor";

	}

	public Vendedor(int codigo, String nome, String telefone, String email, Date dataCad, String cpf, double metaMensal) {
		super(codigo, nome, telefone, email, dataCad);
		this.cpf = cpf;
		this.metaMensal = metaMensal;
	}
	

}
