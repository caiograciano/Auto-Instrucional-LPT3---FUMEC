package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Comercial;
import controller.SisComException;
import model.Cliente;
import model.Fornecedor;
import model.Produto;
import utilitarios.LtpLib;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.UIManager;

public class TelaFornecedor extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textNome;
	private JTextField textCNPJ;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JTextField textContato;
	private JTextField txtPesquisar;
	private JTextField textCnpjCompra;
	private JTable tableConsultaFornecedor;
	private static JTable tableConsultaCNPJ;
	private JTable tableProdutos;
	private JTable tableCarrinho;

	public TelaFornecedor() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			// AO FECHAR ESSA JANELA ELE MOSTRA O MENU INICIAL NOVAMENTE
			public void windowClosing(WindowEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
			}

		});

		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCliente.class.getResource("/icones/fornecedor.png")));
		setTitle("SISCOM - FORNECEDOR");

		// DEFINE AS DIMENSÔES DO JFRAME
		int width = 1200;
		int height = 589;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panelFornecedorCadastro = new JPanel();
		panelFornecedorCadastro.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("CADASTRO", new ImageIcon(TelaFornecedor.class.getResource("/icones/fornecedor.png")),
				panelFornecedorCadastro, null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(137, 15, 62, 16);
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 20));

		textNome = new JTextField();
		textNome.setFont(new Font("Dialog", Font.PLAIN, 16));
		textNome.setBackground(new Color(255, 255, 255));
		textNome.setBounds(242, 12, 804, 46);
		textNome.setColumns(10);

		JLabel lblEmail = new JLabel("Email ");
		lblEmail.setBounds(137, 100, 62, 16);
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 20));

		textEmail = new JTextField();
		textEmail.setFont(new Font("Dialog", Font.PLAIN, 16));
		textEmail.setBounds(242, 87, 804, 46);
		textEmail.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(Color.DARK_GRAY);
		lblTelefone.setBounds(103, 162, 76, 16);
		lblTelefone.setFont(new Font("Dialog", Font.PLAIN, 20));

		textTelefone = new JTextField();
		textTelefone.setFont(new Font("Dialog", Font.PLAIN, 16));
		textTelefone.setBounds(242, 162, 804, 46);
		textTelefone.setColumns(10);

		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setBounds(139, 249, 60, 16);
		lblCnpj.setFont(new Font("Dialog", Font.PLAIN, 20));

		textCNPJ = new JTextField();
		textCNPJ.setFont(new Font("Dialog", Font.PLAIN, 16));
		textCNPJ.setBounds(242, 237, 804, 46);
		textCNPJ.setColumns(10);

		JLabel lblLimiteDeCredito = new JLabel("Contato");
		lblLimiteDeCredito.setBounds(114, 323, 85, 16);
		lblLimiteDeCredito.setFont(new Font("Dialog", Font.PLAIN, 20));

		textContato = new JTextField();
		textContato.setFont(new Font("Dialog", Font.PLAIN, 16));
		textContato.setBounds(242, 312, 804, 46);
		textContato.setColumns(10);

		JButton btnCadastro = new JButton("Cadastrar");
		btnCadastro.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/check32x32.png")));
		btnCadastro.setSelectedIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/check32x32.png")));
		btnCadastro.setBounds(242, 393, 804, 60);
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// SALVA NOVO CLIENTE
				try {

					int codigo = 0;
					if (Comercial.getListaPessoas().isEmpty()) {
						codigo = 1;
					} else {
						codigo = Comercial.getListaPessoas().size() + 1;
					}

					String nome = textNome.getText();
					if (nome.isEmpty()) {
						// JOptionPane.showMessageDialog(null, "NOME NÂO PODE ESTAR VAZIO!");
						JOptionPane.showMessageDialog(null, "NOME NÂO PODE ESTAR VAZIO!", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
					String telefone = textTelefone.getText();
					if (telefone.isEmpty()) {
						// JOptionPane.showMessageDialog(null, "TELEFONE NÂO PODE ESTAR VAZIO!");
						JOptionPane.showMessageDialog(null, "TELEFONE NÂO PODE ESTAR VAZIO!", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
					String email = textEmail.getText();
					if (email.isEmpty()) {
						// JOptionPane.showMessageDialog(null, "EMAIL NÂO PODE ESTAR VAZIO!");
						JOptionPane.showMessageDialog(null, "EMAIL NÂO PODE ESTAR VAZIO!", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
					Date dataCadastrada = new Date();

					String cnpj = textCNPJ.getText();

					String nomeContato = textContato.getText();

					// CRIA OBJ DO TIPO FORNECEDOR
					Fornecedor fornecedor = new Fornecedor(codigo, nome, telefone, email, dataCadastrada, cnpj,
							nomeContato);
					Comercial.getListaPessoas().add(fornecedor);

					JOptionPane.showMessageDialog(null, "Fornecedor Cadastrado com Sucesso!");

					// GravarArquivo();

					// LIMPA O FORMULARIO ACIMA SALVO
					textNome.setText("");
					textTelefone.setText("");
					textEmail.setText("");
					textCNPJ.setText("");
					textContato.setText("");

				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}

			}
		});

		btnCadastro.setBackground(new Color(144, 238, 144));
		btnCadastro.setForeground(new Color(0, 0, 0));
		btnCadastro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		panelFornecedorCadastro.setLayout(null);
		panelFornecedorCadastro.add(lblNome);
		panelFornecedorCadastro.add(textNome);
		panelFornecedorCadastro.add(lblEmail);
		panelFornecedorCadastro.add(textEmail);
		panelFornecedorCadastro.add(lblTelefone);
		panelFornecedorCadastro.add(textTelefone);
		panelFornecedorCadastro.add(lblCnpj);
		panelFornecedorCadastro.add(textCNPJ);
		panelFornecedorCadastro.add(lblLimiteDeCredito);
		panelFornecedorCadastro.add(textContato);
		panelFornecedorCadastro.add(btnCadastro);

		JLabel bgCadastro = new JLabel("");
		bgCadastro.setBounds(12, -12, 1189, 517);
		panelFornecedorCadastro.add(bgCadastro);
		bgCadastro.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/bg_image.jpg")));

		JPanel panelFornecedorBusca = new JPanel();

		tabbedPane.addTab("BUSCA", new ImageIcon(TelaCliente.class.getResource("/icones/busca.png")),
				panelFornecedorBusca, null);

		panelFornecedorBusca.setLayout(null);
		// create table with data

		JTable tableConsultaFornecedor = new JTable();
		tableConsultaFornecedor.setFillsViewportHeight(true);

		DefaultTableModel model = (DefaultTableModel) tableConsultaFornecedor.getModel();
		Object[] titleJTable = { "ID", "Nome", "Email", "CNPJ", "Data cadastrada", "Telefone", "Nome Contato " };

		for (int i = 0; i < titleJTable.length; i++) {
			model.addColumn(titleJTable[i]);
		}

		setVisible(true);

		JPanel panelBusca = new JPanel();
		panelBusca.setBounds(10, 11, 1169, 64);
		panelFornecedorBusca.add(panelBusca);
		panelBusca.setLayout(null);

		JLabel lblDigiteOCnpj = new JLabel("Digite o CNPJ :");
		lblDigiteOCnpj.setBounds(10, 9, 119, 46);
		panelBusca.add(lblDigiteOCnpj);
		lblDigiteOCnpj.setForeground(Color.BLACK);
		lblDigiteOCnpj.setFont(new Font("Dialog", Font.BOLD, 16));

		txtPesquisar = new JTextField();
		txtPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPesquisar.setBounds(131, 9, 521, 46);
		panelBusca.add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JButton btnPesquisar = new JButton("Listar Todos");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) tableConsultaFornecedor.getModel();

				try {
					LimpaJtable(model);

					ArrayList<Fornecedor> fornecedor = Comercial.listaFornecedor();

					// ArrayList<Cliente> clientes = Comercial.listaClientes();

					Object[] linha = new Object[7];

					for (int i = 0; i < fornecedor.size(); i++) {

						linha[0] = fornecedor.get(i).getCodigo();
						linha[1] = fornecedor.get(i).getNome();
						linha[2] = fornecedor.get(i).getEmail();
						linha[3] = fornecedor.get(i).getCnpj();
						linha[4] = LtpLib.obterDataFormatada(fornecedor.get(i).getDataCad());
						linha[5] = fornecedor.get(i).getTelefone();
						linha[6] = fornecedor.get(i).getNomeContato();

						model.addRow(linha);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		});
		btnPesquisar.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/lista32x32.png")));
		btnPesquisar.setBounds(948, 9, 218, 46);
		panelBusca.add(btnPesquisar);

		JButton btnPesquisarCNPJ = new JButton("Pesquisar");
		btnPesquisarCNPJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DefaultTableModel model = (DefaultTableModel) tableConsultaFornecedor.getModel();

				try {
					LimpaJtable(model);
					String pesquisa = txtPesquisar.getText();
					if (pesquisa.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite Um CNPJ Para Realizar a Busca!", "Erro",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Fornecedor fornecedor = (Fornecedor) Comercial.consultarCpf(txtPesquisar.getText(),
								"fornecedor");
						Object[] row = new Object[7];

						row[0] = fornecedor.getCodigo();
						row[1] = fornecedor.getNome();
						row[2] = fornecedor.getEmail();
						row[3] = fornecedor.getCnpj();
						row[4] = LtpLib.obterDataFormatada(fornecedor.getDataCad());
						row[5] = fornecedor.getTelefone();
						row[6] = fornecedor.getNomeContato();

						model.addRow(row);
					}

				} catch (SisComException e) {
					System.err.println(e.getMessage());
				}
			}
		});
		btnPesquisarCNPJ.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/busca.png")));
		btnPesquisarCNPJ.setBounds(662, 9, 276, 46);
		panelBusca.add(btnPesquisarCNPJ);
		tableConsultaFornecedor.setFillsViewportHeight(true);

		// add the table to the frame
		JScrollPane scrollPane = new JScrollPane(tableConsultaFornecedor);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(tableConsultaFornecedor, popupMenu);

		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/trash.gif")));
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DefaultTableModel model = (DefaultTableModel) tableConsultaFornecedor.getModel();
				int selectedRowIndex = tableConsultaFornecedor.getSelectedRow();

				int idPessoa = (int) model.getValueAt(selectedRowIndex, 0);

				model.removeRow(selectedRowIndex);

				try {
					Comercial.removerPessoaId(idPessoa);
					System.out.println("Pessoa removida com Sucesso!");
					JOptionPane.showMessageDialog(null, "Fornecedor Excluido  com Sucesso!");

				} catch (SisComException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		popupMenu.add(mntmExcluir);
		scrollPane.setBounds(14, 86, 1162, 417);
		panelFornecedorBusca.add(scrollPane);

		JLabel label = new JLabel("");
		label.setBounds(0, 0, 1186, 513);
		panelFornecedorBusca.add(label);
		label.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/bg_image.jpg")));

		JPanel panelFornecedorCompra = new JPanel();
		tabbedPane.addTab("COMPRAR", new ImageIcon(TelaFornecedor.class.getResource("/icones/online-store32x32.png")),
				panelFornecedorCompra, null);
		panelFornecedorCompra.setLayout(null);

		JPanel panelBuscaCNPJ = new JPanel();
		panelBuscaCNPJ.setBorder(new TitledBorder(null, "Pesquisa", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(102, 150, 153)));
		panelBuscaCNPJ.setBounds(22, 0, 1155, 66);
		panelFornecedorCompra.add(panelBuscaCNPJ);

		JLabel lblNewLabel = new JLabel("Digite o CNPJ :");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));

		textCnpjCompra = new JTextField();
		textCnpjCompra.setColumns(10);

		JButton btnNewButton = new JButton("Procurar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String FornCNPJ = textCnpjCompra.getText();
				if (FornCNPJ.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite Um CNPJ Para Realizar a Busca!", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					DefaultTableModel model = (DefaultTableModel) tableConsultaCNPJ.getModel();

					try {
						LimpaJtable(model);

						Fornecedor fornecedor = (Fornecedor) Comercial.consultarCpf(textCnpjCompra.getText(),
								"fornecedor");
						Object[] row = new Object[7];

						row[0] = fornecedor.getCodigo();
						row[1] = fornecedor.getNome();
						row[2] = fornecedor.getEmail();
						row[3] = fornecedor.getCnpj();
						row[4] = LtpLib.obterDataFormatada(fornecedor.getDataCad());
						row[5] = fornecedor.getTelefone();
						row[6] = fornecedor.getNomeContato();

						model.addRow(row);

					} catch (SisComException e) {
						System.err.println(e.getMessage());

					}

				}

			}
		});
		btnNewButton.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/busca.png")));
		GroupLayout gl_panelBuscaCNPJ = new GroupLayout(panelBuscaCNPJ);
		gl_panelBuscaCNPJ.setHorizontalGroup(gl_panelBuscaCNPJ.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBuscaCNPJ.createSequentialGroup().addContainerGap()
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textCnpjCompra, GroupLayout.PREFERRED_SIZE, 664, GroupLayout.PREFERRED_SIZE)
						.addGap(31).addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panelBuscaCNPJ.setVerticalGroup(gl_panelBuscaCNPJ.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBuscaCNPJ.createSequentialGroup()
						.addGroup(gl_panelBuscaCNPJ.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(textCnpjCompra, GroupLayout.PREFERRED_SIZE, 32,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelBuscaCNPJ.setLayout(gl_panelBuscaCNPJ);

		JScrollPane scrollPane_CNPJ = new JScrollPane();
		scrollPane_CNPJ.setBounds(22, 70, 1155, 41);
		panelFornecedorCompra.add(scrollPane_CNPJ);

		tableConsultaCNPJ = new JTable();
		scrollPane_CNPJ.setViewportView(tableConsultaCNPJ);
		tableConsultaCNPJ.setFillsViewportHeight(true);
		
		DefaultTableModel model1 = (DefaultTableModel) tableConsultaCNPJ.getModel();
		Object[] titleJTable1 = { "ID", "Nome", "Email", "CNPJ", "Data cadastrada", "Telefone", "Nome Contato " };

		for (int i = 0; i < titleJTable1.length; i++) {
			model1.addColumn(titleJTable1[i]);
		}
		setVisible(true);
		
		JPanel panelProdutos = new JPanel();
		panelProdutos.setBorder(new TitledBorder(null, "Produtos", TitledBorder.CENTER, TitledBorder.TOP, null,new Color(102, 150, 153)));

		panelProdutos.setBounds(32, 123, 277, 382);
		panelFornecedorCompra.add(panelProdutos);
		panelProdutos.setLayout(null);

		JScrollPane scrollPane_Produtos = new JScrollPane();
		scrollPane_Produtos.setBounds(12, 22, 253, 348);
		panelProdutos.add(scrollPane_Produtos);
		tableProdutos = new JTable();
		tableProdutos.setFillsViewportHeight(true);
		tableProdutos.setLocation(0, 75);
		tableProdutos.setModel(mostraTabelaDeProdutosTotal(Comercial.getListaProduto()));
		scrollPane_Produtos.setViewportView(tableProdutos);
		tableProdutos.setDragEnabled(true);
	
		JPanel panelCarrinho = new JPanel();
		panelCarrinho.setBorder(new TitledBorder(null, "Carrinho", TitledBorder.CENTER, TitledBorder.TOP, null,new Color(102, 150, 153)));
		panelCarrinho.setBounds(394, 123, 783, 344);
		panelFornecedorCompra.add(panelCarrinho);
		panelCarrinho.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 59, 759, 273);
		panelCarrinho.add(scrollPane_2);
		
		tableCarrinho = new JTable();
		

		scrollPane_2.setViewportView(tableCarrinho);
		tableCarrinho.setFillsViewportHeight(true);
		DefaultTableModel modelProduto = (DefaultTableModel) tableCarrinho.getModel();
		Object[] titleJTable1Produto = { "Código", "Nome", "Preço Unitário", "Quantidade" };
		for (int i = 0; i < titleJTable1Produto.length; i++) {
			modelProduto.addColumn(titleJTable1Produto[i]);
		}
		setVisible(true);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/online-store32x32.png")));
		lblNewLabel_1.setBounds(372, 22, 38, 32);
		panelCarrinho.add(lblNewLabel_1);
		
		JLabel LabelTotal = new JLabel("Valor Total : R$");
		LabelTotal.setForeground(Color.BLACK);
		LabelTotal.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/total.png")));
		LabelTotal.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		LabelTotal.setBackground(Color.GREEN);
		LabelTotal.setBounds(579, 479, 198, 16);
		panelFornecedorCompra.add(LabelTotal);
		
		
		JLabel VrTotal = new JLabel("0,00");
		VrTotal.setForeground(Color.BLACK);
		VrTotal.setBackground(Color.GREEN);
		VrTotal.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		VrTotal.setBounds(772, 479, 107, 16);
		panelFornecedorCompra.add(VrTotal);
		JButton btnNewButton_1 = new JButton("Comprar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				efetuarCompra(VrTotal);
			}

			
		});
		
		
		btnNewButton_1.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/check32x32.png")));
		btnNewButton_1.setBounds(976, 467, 201, 38);
		panelFornecedorCompra.add(btnNewButton_1);
		
		tableCarrinho.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				
				calcularValorTotalProdutos(VrTotal);
			}
		});
		tableCarrinho.getModel().addTableModelListener(new TableModelListener() {

		      public void tableChanged(TableModelEvent e) {
		    	  
		    	  calcularValorTotalProdutos(VrTotal);
		      }

			
		    });
		
		JButton btnAddCarrinho = new JButton("");
		btnAddCarrinho.setBackground(new Color(152, 251, 152));
		btnAddCarrinho.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAddCarrinho.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/Add-Cart-32x32.png")));
		btnAddCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					DefaultTableModel model = (DefaultTableModel) tableProdutos.getModel();
					DefaultTableModel model2 = (DefaultTableModel) tableCarrinho.getModel();
					
					int linhaSelecionada = tableProdutos.getSelectedRow();
					int idProduto = (int) model.getValueAt(linhaSelecionada, 0);
					String nomeProduto = (String) model.getValueAt(linhaSelecionada, 1);
					Double qntProduto = (Double) model.getValueAt(linhaSelecionada, 2);
									
					
					Object[] row = new Object[4];
					row[0] = idProduto;
					row[1] = nomeProduto;
					row[2] = qntProduto;
					row[3] = "0";
					model2.addRow(row);
					
					//REMOVE ITEM QUE FOI PARA A TABELA AO LADO 
					model.removeRow(linhaSelecionada);
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selecione um Produto Para Adicionar ao Carrinho ! ", "Erro",JOptionPane.ERROR_MESSAGE);
				}
						
				
			}
		});
		btnAddCarrinho.setFont(new Font("Dialog", Font.BOLD, 25));
		btnAddCarrinho.setBounds(321, 196, 61, 98);
		panelFornecedorCompra.add(btnAddCarrinho);
		
		JButton btnRemoveCarrinho = new JButton("");
		btnRemoveCarrinho.setBackground(new Color(255, 182, 193));
		btnRemoveCarrinho.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/cart-remove-32x32.png")));
		btnRemoveCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultTableModel model = (DefaultTableModel) tableProdutos.getModel();
					DefaultTableModel model2 = (DefaultTableModel) tableCarrinho.getModel();
					
					int linhaSelecionada = tableCarrinho.getSelectedRow();
					int idProduto = (int) model2.getValueAt(linhaSelecionada, 0);
					String nomeProduto = (String) model2.getValueAt(linhaSelecionada, 1);
					Double qntProduto = (Double) model2.getValueAt(linhaSelecionada, 2);
									
					
					Object[] row = new Object[4];
					row[0] = idProduto;
					row[1] = nomeProduto;
					row[2] = qntProduto;
					model.addRow(row);
					
					//REMOVE ITEM QUE FOI PARA A TABELA AO LADO 
					model2.removeRow(linhaSelecionada);
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selecione um Item Para Devolução", "Erro",JOptionPane.ERROR_MESSAGE);
				}
						
			}
		});
		btnRemoveCarrinho.setFont(new Font("Dialog", Font.BOLD, 25));
		btnRemoveCarrinho.setBounds(321, 326, 61, 110);
		panelFornecedorCompra.add(btnRemoveCarrinho);
		

		JLabel bg = new JLabel("New label");
		bg.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/icones/bg_image.jpg")));
		bg.setBounds(12, 12, 1189, 517);
		panelFornecedorCompra.add(bg);

		setVisible(true);

	}

	protected void efetuarCompra(JLabel VrTotal) {
		
		
	}

	public DefaultTableModel mostraTabelaDeProdutosTotal(ArrayList<Produto> listaProdutos) {
		
		Object[] titleRow = {"Código","Nome", "Preço Unitário"};
		Object[][] data = new Object[listaProdutos.size()][titleRow.length];
		
		for (int i = 0; i < listaProdutos.size(); i++) {
			
			
			data[i][0] = listaProdutos.get(i).getCodigo();
			data[i][1] = listaProdutos.get(i).getNome();
			data[i][2] = listaProdutos.get(i).getPrecoUnitario();			
		}
		
		DefaultTableModel model = new DefaultTableModel(data,titleRow);
		
		
		
		return model;
	}

	private void mostarTabelaFornecedor() {

		// DefaultTableModel model = (DefaultTableModel) tableConsultaCNPJ.getModel();

		/*
		 * try { LimpaJtable(model);
		 * 
		 * Fornecedor fornecedor = (Fornecedor)
		 * Comercial.consultarCpf(txtPesquisar.getText(), "fornecedor"); Object[] row =
		 * new Object[7];
		 * 
		 * row[0] = fornecedor.getCodigo(); row[1] = fornecedor.getNome(); row[2] =
		 * fornecedor.getEmail(); row[3] = fornecedor.getCnpj(); row[4] =
		 * fornecedor.getDataCad(); row[5] = fornecedor.getTelefone(); row[6] =
		 * fornecedor.getNomeContato();
		 * 
		 * model.addRow(row);
		 * 
		 * } catch (SisComException e) { System.err.println(e.getMessage()); }
		 */

		DefaultTableModel model = (DefaultTableModel) tableConsultaFornecedor.getModel();

		try {
			LimpaJtable(model);

			ArrayList<Fornecedor> fornecedor = Comercial.listaFornecedor();

			// ArrayList<Cliente> clientes = Comercial.listaClientes();

			Object[] linha = new Object[7];

			for (int i = 0; i < fornecedor.size(); i++) {

				linha[0] = fornecedor.get(i).getCodigo();
				linha[1] = fornecedor.get(i).getNome();
				linha[2] = fornecedor.get(i).getEmail();
				linha[3] = fornecedor.get(i).getCnpj();
				linha[4] = fornecedor.get(i).getDataCad();
				linha[5] = fornecedor.get(i).getTelefone();
				linha[6] = fornecedor.get(i).getNomeContato();

				model.addRow(linha);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void LimpaJtable(DefaultTableModel tabela) {

		// LIMPA TODAS AS LINHAS DA JTABLE
		tabela.setRowCount(0);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	private static void GravarArquivo() {
		try {
			LtpLib.gravarArquivoObjetosArray("Pessoas.obj", Comercial.getListaPessoas());
			System.out.println("ARQUVO GRAVADO");
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			System.exit(7);
		}
	}
	
	public void calcularValorTotalProdutos(JLabel valor) {
		DefaultTableModel tableModel = (DefaultTableModel) tableCarrinho.getModel();
		
			try {
				double total = 0;
				double valorProduto;
				int quantidade;
				
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					valorProduto = Double.parseDouble(tableModel.getValueAt(i, 2).toString().trim());
					
					quantidade = Integer.parseInt(tableModel.getValueAt(i, 3).toString().trim());
					
					total += valorProduto * quantidade;
				}
				
				valor.setText(Double.toString(total));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
}
