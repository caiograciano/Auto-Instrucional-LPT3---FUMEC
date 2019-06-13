package view;

import java.io.File;
import java.util.Date;

import controller.Comercial;
import model.Cliente;
import model.Compra;
import model.Fornecedor;
import model.Produto;
import model.Vendedor;
import utilitarios.Console;
import utilitarios.LtpLib;

public class Main {
	//obj para "pegar" a compra
	//private static Compra objCompra = new Compra();
	public static void main(String[] args) {
	

		lerArquivos();

		// Chama a tela de SPLASH
		Splash splash = new Splash(4000);
		splash.showSplashAndExit();

		new Menu();

		consoleMenu();

		GravarArquivos();

	}

	private static void consoleMenu() {
		int opcao = 0;
		do {
			System.out.println("\n=========== MENU ========== ");
			System.out.println("1 - Inserir novo Cliente");
			System.out.println("2 - Listar Clientes");
			System.out.println("3 - Excluir Cliente");
			System.out.println("============================");
			System.out.println("4 - Inserir novo Fornecedor");
			System.out.println("5 - Listar Fornecedor");
			System.out.println("6 - Excluir Fornecedor");
			System.out.println("============================");
			System.out.println("7 - Inserir novo Produto");
			System.out.println("8 - Listar Produto");
			System.out.println("9 - Excluir Produto");
			System.out.println("============================");
			System.out.println("10 - Inserir novo Produto");
			System.out.println("11 - Listar Produto");
			System.out.println("12 - Excluir Produto");

			System.out.println("0-Sair");
			opcao = Console.readInt("\nOpção: ");
			switch (opcao) {
			case 1:
				inserirCliente();
				break;
			case 2:
				imprimirListaClientes();
				break;
			case 3:
				excluirCliente();
				break;
			case 4:
				inserirFornecedor();
				break;
			case 5:
				imprimirListaFornecedor();
			case 6:
				excluirFonecedor();
				break;
			case 7:
				inserirVendedor();
				break;
			case 8:
				imprimirListaVendedor();
				break;
			case 9:
				excluirVendedor();
				break;
			case 10:
				inserirProduto();
				break;
			case 11:
				imprimirListaProduto();
				break;
			case 12:
				excluirProduto();
				break;
			case 0:
				// sai do sistema e salva.
				break;
			default:
				System.out.println("Opção Inválida.");
				break;
			}
		} while (opcao != 0);

	}

	private static void excluirVendedor() {
		// TODO Auto-generated method stub
		
	}

	private static void imprimirListaVendedor() {
		// TODO Auto-generated method stub
		
	}

	private static void inserirVendedor() {
		int codigo = 0;
		String nome = "";
		String telefone;
		String email;
		Date dataCadastrada;
		String cpf;
		double metaMensal;
		
		if(Comercial.getListaPessoas().isEmpty()) {
			codigo = 1;
		}else {
			codigo = Comercial.getListaPessoas().size() + 1;
		}
		
		nome = Console.readLine("Nome do Vendedor: ");
		telefone = Console.readLine("Telefone do Vendedor: ");
		email = Console.readLine("Email do Vendedor: ");
		dataCadastrada = new Date();
		cpf = Console.readLine("CPF do Vendedor : ");
		metaMensal = Console.readDouble("Meta Mensal: ");
		
		Vendedor vendedor = new Vendedor(codigo, nome, telefone, email, dataCadastrada, cpf, metaMensal);
		Comercial.getListaPessoas().add(vendedor);
		
	}

	private static void excluirProduto() {
		// TODO Auto-generated method stub

	}

	private static void imprimirListaProduto() {
		try {

			for (Produto produto : Comercial.obterListaProdutosNomeOrdenada()) {
				System.out.println(produto.toString());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void inserirProduto() {
		int codigo = 0;
		String nome;
		double precoUnitario;
		int estoque;
		int estoqueMinimo;
		Date dataCadastrada;
		if (Comercial.getListaProduto().isEmpty()) {
			codigo = 1;
		} else {
			codigo = Comercial.getListaProduto().size() + 1;
		}

		nome = Console.readLine("Nome do Produto: ");
		precoUnitario = Console.readDouble("Preço Unitário: ");
		estoque = Console.readInt("Quantidade de estoque: ");
		estoqueMinimo = Console.readInt("Quantidade de Estoque minímo: ");
		dataCadastrada = new Date();

		Produto produto = new Produto(codigo, nome, precoUnitario, estoque, estoqueMinimo, dataCadastrada);
		Comercial.getListaProduto().add(produto);
	}

	public static void lerArquivos() {

		// ler cadastro de Pessoas
		if (new File("Pessoas.obj").exists()) {
			try {
				// System.out.println("Abrindo Arquivo Pessoas...");
				Comercial.setListaPessoas(LtpLib.lerArquivoObjetosArray("Pessoas.obj"));
				System.out.println("Arquivo PESSOAS Aberto");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.exit(3);
			}
		}

		// ARQUIVO DE PRODUTOS
		// ler cadastro de Pessoas
		if (new File("Produtos.obj").exists()) {
			try {
				// System.out.println("Abrindo Arquivo Produtos...");
				Comercial.setListaProduto(LtpLib.lerArquivoObjetosArray("Produtos.obj"));
				System.out.println("Arquivo PRODUTOS Aberto");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.exit(3);
			}
		}
	}

	public static void GravarArquivos() {

		try {
			LtpLib.gravarArquivoObjetosArray("Pessoas.obj", Comercial.getListaPessoas());
			System.out.println("ARQUVO Pessoas GRAVADO");
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			System.exit(7);
		}

		try {
			LtpLib.gravarArquivoObjetosArray("Produtos.obj", Comercial.getListaProduto());
			System.out.println("ARQUVO PRodutos GRAVADO");
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			System.exit(7);
		}
	}

	private static void inserirCliente() {
		int codigo = 0;
		String nome = "";
		String telefone;
		String email;
		Date dataCadastrada;
		String cpf;
		double limiteCredito;

		if (Comercial.getListaPessoas().isEmpty()) {
			codigo = 1;
		} else {
			codigo = Comercial.getListaPessoas().size() + 1;
		}

		nome = Console.readLine("Nome do Cliente: ");
		telefone = Console.readLine("Telefone do Cliente: ");
		email = Console.readLine("Email do Cliente: ");
		dataCadastrada = new Date();
		cpf = Console.readLine("CPF do Cliente: ");
		limiteCredito = Console.readDouble("limite de credito do Cliente: ");

		Cliente cliente = new Cliente(codigo, nome, telefone, email, dataCadastrada, cpf, limiteCredito);
		Comercial.getListaPessoas().add(cliente);

	}

	public static void imprimirListaClientes() {
		try {
			System.out.println(Comercial.consultarPessoas("cliente"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void excluirCliente() {
		// TODO Auto-generated method stub

	}

	private static void inserirFornecedor() {

		int codigo = 0;

		if (Comercial.getListaPessoas().isEmpty()) {
			codigo = 1;
		} else {
			codigo = Comercial.getListaPessoas().size() + 1;
		}

		String nome = Console.readLine("Nome do Fornecedor: ");
		String telefone = Console.readLine("Telefone do Fornecedor: ");
		String email = Console.readLine("Email do Fornecedor: ");
		Date dataCadastrada = new Date();
		String cnpj = Console.readLine("CNPJ do Fornecedor: ");
		String nomeContato = Console.readLine("Nome do Contato: ");

		Fornecedor fornecedor = new Fornecedor(codigo, nome, telefone, email, dataCadastrada, cnpj, nomeContato);
		Comercial.getListaPessoas().add(fornecedor);

	}

	public static void imprimirListaFornecedor() {
		try {
			System.out.println(Comercial.consultarPessoas("fornecedor"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void excluirFonecedor() {
		// TODO Auto-generated method stub

	}
}
