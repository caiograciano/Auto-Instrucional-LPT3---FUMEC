package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Comercial;
import controller.SisComException;
import model.Cliente;
import model.Fornecedor;
import model.Vendedor;
import utilitarios.LtpLib;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class TelaVendedor extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textNome;
	private JTextField textCPF;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JTextField textMetaMensal;
	private JTextField textPesquisarVendedor;
	private JTable tableConsultaVendedor;

	public TelaVendedor() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			// AO FECHAR ESSA JANELA ELE MOSTRA O MENU INICIAL NOVAMENTE
			public void windowClosing(WindowEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
			}
		});

		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaVendedor.class.getResource("/icones/vendedor.png")));
		setTitle("SISCOM - VENDEDOR");

		// DEFINE AS DIMENSÔES DO JFRAME
		int width = 1200;
		int height = 589;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panelClienteCadastro = new JPanel();
		panelClienteCadastro.setBackground(Color.WHITE);
		tabbedPane.addTab("CADASTRO", new ImageIcon(TelaVendedor.class.getResource("/icones/vendedor32x32.png")),
				panelClienteCadastro, null);
		panelClienteCadastro.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(185, 30, 70, 50);
		lblNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panelClienteCadastro.add(lblNome);

		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textNome.setBounds(270, 30, 721, 50);
		panelClienteCadastro.add(textNome);
		textNome.setColumns(10);

		JLabel lblEmail = new JLabel("Email ");
		lblEmail.setBounds(185, 108, 70, 50);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panelClienteCadastro.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textEmail.setBounds(270, 110, 721, 50);
		textEmail.setColumns(10);
		panelClienteCadastro.add(textEmail);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(175, 193, 80, 50);
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panelClienteCadastro.add(lblTelefone);

		textTelefone = new JTextField();
		textTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textTelefone.setBounds(270, 190, 721, 50);
		textTelefone.setColumns(10);
		panelClienteCadastro.add(textTelefone);

		JLabel lblCnpj = new JLabel("CPF");
		lblCnpj.setBounds(191, 268, 64, 50);
		lblCnpj.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panelClienteCadastro.add(lblCnpj);

		textCPF = new JTextField();
		textCPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textCPF.setBounds(270, 270, 721, 50);
		textCPF.setColumns(10);
		panelClienteCadastro.add(textCPF);

		JLabel lblLimiteDeCredito = new JLabel("Meta Mensal");
		lblLimiteDeCredito.setBounds(145, 348, 110, 50);
		lblLimiteDeCredito.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panelClienteCadastro.add(lblLimiteDeCredito);

		textMetaMensal = new JTextField();
		textMetaMensal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textMetaMensal.setBounds(270, 350, 721, 50);
		textMetaMensal.setColumns(10);
		panelClienteCadastro.add(textMetaMensal);

		JButton btnCadastro = new JButton("Cadastrar");
		btnCadastro.setIcon(new ImageIcon(TelaVendedor.class.getResource("/icones/check32x32.png")));
		btnCadastro.setBounds(270, 430, 721, 50);
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// SALVA NOVO CLIENTE
				try {

					int codigo = 0;
					/*
					 * if(Comercial.getListaPessoas().isEmpty()) { codigo = 1; }else { codigo =
					 * Comercial.getListaPessoas().size() + 1; }
					 */

					String nome = textNome.getText();

					if (nome.isEmpty()) {
						JOptionPane.showMessageDialog(null, "NOME NÂO PODE ESTAR VAZIO!");
					}
					String telefone = textTelefone.getText();
					if (telefone.isEmpty()) {
						JOptionPane.showMessageDialog(null, "TELEFONE NÂO PODE ESTAR VAZIO!");
					}
					String email = textEmail.getText();
					if (email.isEmpty()) {
						JOptionPane.showMessageDialog(null, "EMAIL NÂO PODE ESTAR VAZIO!");
					}
					Date dataCad = new Date();

					String cpf = textCPF.getText();

					double metaMensal = Double.parseDouble(textMetaMensal.getText());

					Vendedor vendedor = new Vendedor(codigo, nome, telefone, email, dataCad, cpf, metaMensal);
					Comercial.getListaPessoas().add(vendedor);

					JOptionPane.showMessageDialog(null, "Vendedor Cadastrado com Sucesso!");
					// mostarTabelaCliente();
					try {
						LtpLib.gravarArquivoObjetosArray("Pessoas.obj", Comercial.getListaPessoas());
						System.out.println("ARQUVO GRAVADO");
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
						System.exit(7);
					}

				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}

			}
		});

		btnCadastro.setBackground(new Color(144, 238, 144));
		btnCadastro.setForeground(new Color(0, 0, 0));
		btnCadastro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		panelClienteCadastro.add(btnCadastro);

		JLabel bg = new JLabel("");
		bg.setIcon(new ImageIcon(TelaVendedor.class.getResource("/icones/bg_image.jpg")));
		bg.setBounds(0, 0, 1186, 513);
		panelClienteCadastro.add(bg);

		JPanel panelBuscaVendedor = new JPanel();
		tabbedPane.addTab("Busca", new ImageIcon(TelaCliente.class.getResource("/icones/busca.png")),
				panelBuscaVendedor, null);
		panelBuscaVendedor.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(20, 11, 1159, 61);
		panelBuscaVendedor.add(panel);

		JLabel lblDigiteOCdigo = new JLabel("Digite o C\u00F3digo");
		lblDigiteOCdigo.setBounds(5, 15, 130, 31);
		lblDigiteOCdigo.setForeground(Color.BLACK);
		lblDigiteOCdigo.setFont(new Font("Dialog", Font.BOLD, 16));

		textPesquisarVendedor = new JTextField();
		textPesquisarVendedor.setBounds(134, 15, 652, 31);
		textPesquisarVendedor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPesquisarVendedor.setColumns(10);
		panel.setLayout(null);
		panel.add(lblDigiteOCdigo);
		panel.add(textPesquisarVendedor);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) tableConsultaVendedor.getModel();

				try {
					LimpaJtable(model);

					Vendedor vendedor = (Vendedor) Comercial.consultarCpf(textPesquisarVendedor.getText(), "vendedor");

					Object[] row = new Object[7];

					row[0] = vendedor.getCodigo();
					row[1] = vendedor.getNome();
					row[2] = vendedor.getEmail();
					row[3] = vendedor.getCpf();
					row[4] = LtpLib.obterDataFormatada(vendedor.getDataCad());
					row[5] = vendedor.getTelefone();
					row[6] = vendedor.getMetaMensal();

					model.addRow(row);

				} catch (SisComException e) {
					System.err.println(e.getMessage());
				}

			}

			
		});
		btnPesquisar.setIcon(new ImageIcon(TelaVendedor.class.getResource("/icones/busca.png")));
		btnPesquisar.setBounds(812, 13, 163, 34);
		panel.add(btnPesquisar);

		JButton btnListarTodos = new JButton("Listar Todos");
		btnListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mostarTabelaVendedor();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		btnListarTodos.setIcon(new ImageIcon(TelaVendedor.class.getResource("/icones/lista32x32.png")));
		btnListarTodos.setBounds(986, 13, 163, 34);
		panel.add(btnListarTodos);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 83, 1159, 420);
		panelBuscaVendedor.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1159, 420);
		panel_1.add(scrollPane);

		tableConsultaVendedor = new JTable();
		tableConsultaVendedor.setFillsViewportHeight(true);

		DefaultTableModel model = (DefaultTableModel) tableConsultaVendedor.getModel();
		Object[] titleJTable = { "ID", "Nome", "Email", "CPF", "Data cadastrada", "Telefone", "Meta Mensal  " };

		for (int i = 0; i < titleJTable.length; i++) {
			model.addColumn(titleJTable[i]);
		}
		scrollPane.setViewportView(tableConsultaVendedor);

		setVisible(true);
		
		
	}
	public void mostarTabelaVendedor() throws SisComException{
		DefaultTableModel model = (DefaultTableModel) tableConsultaVendedor.getModel();
		LimpaJtable(model);
			
			ArrayList<Vendedor> listaVendedores = Comercial.listaVendedor();
			
				Object[] linha = new Object[7];
				
				for (int i = 0; i < listaVendedores.size(); i++) {
					
					linha[0] = listaVendedores.get(i).getCodigo();
					linha[1] = listaVendedores.get(i).getNome();
					linha[2] = listaVendedores.get(i).getEmail();
					linha[3] = listaVendedores.get(i).getCpf();
					linha[4] = LtpLib.obterDataFormatada(listaVendedores.get(i).getDataCad());	
					linha[5] = listaVendedores.get(i).getTelefone();
					linha[6] = listaVendedores.get(i).getMetaMensal();
					
					model.addRow(linha);
				}
				
		
	}
	private void LimpaJtable(DefaultTableModel model) {
		model.setRowCount(0);
		
	}
}
