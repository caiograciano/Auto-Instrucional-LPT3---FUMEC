package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Venda implements Serializable{
	
	/**
	 * @author Caio
	 */
	private static final long serialVersionUID = 1L;


	private ArrayList<ItemVenda> vendaItens = new ArrayList<ItemVenda>();
	
	private int numVenda;
	private Cliente cliente;
	private Vendedor vendedor;
	private int formaPagamento;
	private Date dataVenda;
	
	@Override
	public String toString() {
		return
				"Numero de vendas    : " + numVenda + "\n" +
				"Cliente  : " + cliente + "\n"+
				"Vendedor : "+ vendedor + "\n" +
				"Forma de pagamento: " + formaPagamento + "\n" +
				"Data da venda: "+ dataVenda + "\n";
	}
	public Venda(ArrayList<ItemVenda> vendaItens, int numVenda, Cliente cliente, Vendedor vendedor, int formaPagamento,
			Date dataVenda) {
		this.vendaItens = vendaItens;
		this.numVenda = numVenda;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.formaPagamento = formaPagamento;
		this.dataVenda = dataVenda;
	}
	
	
	public ArrayList<ItemVenda> getVendaItens() {
		return vendaItens;
	}
	public void setVendaItens(ArrayList<ItemVenda> vendaItens) {
		this.vendaItens = vendaItens;
	}
	public int getNumVenda() {
		return numVenda;
	}
	public void setNumVenda(int numVenda) {
		this.numVenda = numVenda;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public int getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(int formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	
}
