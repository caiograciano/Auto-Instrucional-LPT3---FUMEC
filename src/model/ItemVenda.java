package model;

import java.io.Serializable;

public class ItemVenda implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Produto produto;
	private int quantVenda;
	private double valorVenda;
	
	
	public ItemVenda() {
	
	}
	
	@Override
	public String toString() {
		return "ItemVenda [Produto =" + produto + ", Quantidade Vendida =" + quantVenda + ", Valor de Venda=" + valorVenda + "]";
	}
	public ItemVenda(Produto produto, int quantVenda, double valorVenda) {
		this.produto = produto;
		this.quantVenda = quantVenda;
		this.valorVenda = valorVenda;
	}

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantVenda() {
		return quantVenda;
	}
	public void setQuantVenda(int quantVenda) {
		this.quantVenda = quantVenda;
	}
	public double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}
	
	
}
