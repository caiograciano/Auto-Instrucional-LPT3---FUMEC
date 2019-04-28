package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import controller.SisComException;
import model.Cliente;
import model.Fornecedor;
import model.Pessoa;
import model.Produto;
import model.Vendedor;

public class Comercial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Todos os ArrayList do Sistema
	 */

	private static ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
	private static ArrayList<Produto> listaProduto = new ArrayList<Produto>();

	public static ArrayList<Produto> getListaProduto() {
		return listaProduto;
	}

	public static void setListaProduto(ArrayList<Produto> listaProduto) {
		Comercial.listaProduto = listaProduto;
	}

	public static ArrayList<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public static void setListaPessoas(ArrayList<Pessoa> listaPessoas) {
		Comercial.listaPessoas = listaPessoas;
	}

	public void inserirPessoa(Pessoa objPessoa) {
		listaPessoas.add(objPessoa);
	}

	public void ExcluirPessoa(Pessoa objPessoa) {
		listaPessoas.remove(objPessoa);
	}

	public static ArrayList<Pessoa> consultarPessoas(String tipo) {
		ArrayList<Pessoa> listadepessoas = new ArrayList<Pessoa>();
		for (Pessoa pessoas : listaPessoas) {
			if (tipo == "fornecedor") {
				if (pessoas instanceof Fornecedor) {
					listadepessoas.add(pessoas);
				}
			} else if (tipo == "cliente") {
				if (pessoas instanceof Cliente) {
					listadepessoas.add(pessoas);
				}
			} else if (tipo == "vendedor") {
				if (pessoas instanceof Vendedor) {
					listadepessoas.add(pessoas);
				}
			}
		}
		if (listadepessoas.isEmpty()) {
			System.out.println("Não Há Registro de Pessoas ");
		}
		;
		return listadepessoas;
	}

	// RETORNO LISTA DE CLIENTE *TESTE*
	public static ArrayList<Cliente> listaClientes() {
		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

		try {
			for (Pessoa cliente : listaPessoas) {
				if (cliente instanceof Cliente) {
					listaClientes.add((Cliente) cliente);
				}
			}
		} catch (Exception e) {
			System.out.println("Não tem Clientes na lista" + e.getMessage());
		}

		return listaClientes;
	}

	public static ArrayList<Produto> obterListaProdutosNomeOrdenada() throws SisComException {
		if (listaProduto.isEmpty()) {
			throw new SisComException("Erro, Não existe produtos na Lista");
		}

		ArrayList<Produto> listaProdutosOrdenada = new ArrayList<Produto>();
		listaProdutosOrdenada.addAll(listaProduto);
		Collections.sort(listaProdutosOrdenada, new Comparator<Produto>() {
			@Override
			public int compare(Produto o1, Produto o2) {

				return o1.getNome().compareTo(o2.getNome());
			}
		});

		return listaProdutosOrdenada;

	}

	public static ArrayList<Fornecedor> listaFornecedor() {
		ArrayList<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();

		try {
			for (Pessoa fornecedor : listaPessoas) {
				if (fornecedor instanceof Fornecedor) {
					listaFornecedor.add((Fornecedor) fornecedor);
				}
			}
		} catch (Exception e) {
			System.out.println("Não tem Clientes na lista" + e.getMessage());
		}

		return listaFornecedor;
	}

	public static Pessoa consultarCpf(String cpf, String tipo) throws SisComException {
		for (Pessoa pessoa : listaPessoas) {
			if (tipo == "fornecedor") {
				if (pessoa instanceof Fornecedor) {
					if (((Fornecedor) pessoa).getCnpj().contains(cpf)) {
						return (Fornecedor) pessoa;
					}
				}

			} else if (tipo == "cliente") {
				if (pessoa instanceof Cliente) {
					if (((Cliente) pessoa).getCpf().contains(cpf)) {
						return (Pessoa) pessoa;
					}
				}
			} else if (tipo == "vendedor") {
				if (pessoa instanceof Vendedor) {
					if (((Vendedor) pessoa).getCpf().contains(cpf)) {
						return (Vendedor) pessoa;
					}
				}
			}
		}

		throw new SisComException("Erro! Pessoa não existe.");
	}

	public static boolean removerPessoaId(int id) throws SisComException {

		for (Pessoa pessoa : listaPessoas) {

			if (pessoa.getCodigo() == id) {

				listaPessoas.remove(pessoa);

				return true;
			}

		}

		throw new SisComException("Não foi possível remover");

	}

	public static ArrayList<Vendedor> listaVendedor() throws SisComException {
		ArrayList<Vendedor> listaVendedores = new ArrayList<Vendedor>();

		try {
			for (Pessoa vendedor : listaPessoas) {
				if (vendedor instanceof Vendedor) {
					listaVendedores.add((Vendedor) vendedor);
				}
			}
		} catch (Exception e) {
			System.out.println("Não tem Fornecedores na lista" + e.getMessage());
		}

		return listaVendedores;
	}

	public static ArrayList<Produto> obterListaProdutosEstoqueMin() throws SisComException {
		if (listaProduto.isEmpty()) {
			throw new SisComException("Erro, não existem produtos");
		}
		ArrayList<Produto> listaProdutosEstoqueMin = new ArrayList<Produto>();
		for (Produto produto : listaProduto) {
			if (produto.getEstoque() < produto.getEstoqueMinimo()) {
				listaProdutosEstoqueMin.add(produto);
			}
		}

		Collections.sort(listaProdutosEstoqueMin, new Comparator<Produto>() {
			@Override
			public int compare(Produto o1, Produto o2) {

				return o1.getNome().compareTo(o2.getNome());
			}
		});

		return listaProdutosEstoqueMin;
	}

	public static Produto consultarCodigoProduto(int codigoProduto) throws SisComException {
		for (Produto produto : listaProduto) {
			if (produto.getCodigo() == codigoProduto) {
				return produto;
			}
		}
		throw new SisComException("Erro, produto não existe");
	}

	public static ArrayList<Produto> listaProdutos() {
		ArrayList<Produto> listaProduto = new ArrayList<Produto>();
		for (Produto produto : listaProduto) {
			listaProduto.add((Produto) produto);
		}
		return listaProduto;  
	}

	public static boolean validarCPF(String cpf) {
		// TODO Auto-generated method stub
		return false;
	}

}
