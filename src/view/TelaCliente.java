package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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

import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

public class TelaCliente extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textNome;
	private JTextField textCPF;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JTextField textLimiteCredito;
	private JTable JTablePesquisaCliente;
	private JTable tableConsulta;
	private JTextField textPesquisarCliente;
	private static Comercial comercial = new Comercial();
	private JTextField textCPFVenda;
	private JTable tableClienteCPF;
	private JTable tableProdutos;
	private JTable tableCarrinho;

	public TelaCliente() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			// AO FECHAR ESSA JANELA ELE MOSTRA O MENU INICIAL NOVAMENTE
			public void windowClosing(WindowEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
			}
		});

		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCliente.class.getResource("/icones/cliente.png")));
		setTitle("SISCOM - CLIENTE");

		// DEFINE AS DIMENSÔES DO JFRAME
		int width = 1200;
		int height = 589;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, 1200, 598);
		getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.MAGENTA);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(0, 0, 1194, 560);
		getContentPane().add(tabbedPane);

		JPanel panelClienteCadastro = new JPanel();
		tabbedPane.addTab("CADASTRO", new ImageIcon(TelaCliente.class.getResource("/icones/cliente.png")),
				panelClienteCadastro, null);
		panelClienteCadastro.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(165, 41, 76, 34);
		lblNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panelClienteCadastro.add(lblNome);

		textNome = new JTextField();
		textNome.setBounds(259, 116, 802, 44);
		panelClienteCadastro.add(textNome);
		textNome.setColumns(10);

		JLabel lblEmail = new JLabel("Email ");
		lblEmail.setBounds(165, 131, 76, 34);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panelClienteCadastro.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBounds(259, 36, 802, 44);
		textEmail.setColumns(10);
		panelClienteCadastro.add(textEmail);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(155, 201, 86, 34);
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panelClienteCadastro.add(lblTelefone);

		textTelefone = new JTextField();
		textTelefone.setBounds(259, 196, 802, 44);
		textTelefone.setColumns(10);
		panelClienteCadastro.add(textTelefone);

		JLabel lblCnpj = new JLabel("CPF");
		lblCnpj.setBounds(165, 282, 70, 34);
		lblCnpj.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panelClienteCadastro.add(lblCnpj);

		textCPF = new JTextField();
		textCPF.setBounds(259, 276, 802, 44);
		textCPF.setColumns(10);
		panelClienteCadastro.add(textCPF);

		JLabel lblLimiteDeCredito = new JLabel("LIMITE DE CREDITO");
		lblLimiteDeCredito.setBounds(81, 383, 158, 34);
		lblLimiteDeCredito.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panelClienteCadastro.add(lblLimiteDeCredito);

		textLimiteCredito = new JTextField();
		textLimiteCredito.setBounds(259, 366, 802, 44);
		textLimiteCredito.setColumns(10);
		panelClienteCadastro.add(textLimiteCredito);

		JButton btnCadastro = new JButton("Cadastrar");
		btnCadastro.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/check32x32.png")));
		btnCadastro.setBounds(259, 446, 802, 44);
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

					Date dataCadastrada = new Date();

					String cpf = textCPF.getText();
					Double limiteCredito = Double.parseDouble(textLimiteCredito.getText());

					// VALIDAÇÃO DE CPF
					//boolean validaCPF = Comercial.validarCPF(cpf);

					Cliente cliente = new Cliente(codigo, nome, telefone, email, dataCadastrada, cpf, limiteCredito);
					Comercial.getListaPessoas().add(cliente);

					JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
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
				DefaultTableModel model = (DefaultTableModel) tableConsulta.getModel();
				model.setRowCount(0);

				ArrayList<Cliente> clientes = Comercial.listaClientes();

				Object[] colum = new Object[7];
				for (int i = 0; i < clientes.size(); i++) {

					colum[0] = clientes.get(i).getCodigo();
					colum[1] = clientes.get(i).getNome();
					colum[2] = clientes.get(i).getEmail();
					colum[3] = clientes.get(i).getCpf();
					colum[4] = LtpLib.obterDataFormatada(clientes.get(i).getDataCad());
					colum[5] = clientes.get(i).getTelefone();
					colum[6] = clientes.get(i).getLimiteCredito();

					model.addRow(colum);

				}

			}
		});

		btnCadastro.setBackground(new Color(144, 238, 144));
		btnCadastro.setForeground(new Color(0, 0, 0));
		btnCadastro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		panelClienteCadastro.add(btnCadastro);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/bg_image.jpg")));
		label_1.setBounds(0, -12, 1189, 517);
		panelClienteCadastro.add(label_1);

		JPanel panelBuscaCliente = new JPanel();
		tabbedPane.addTab("BUSCA", new ImageIcon(TelaCliente.class.getResource("/icones/busca.png")), panelBuscaCliente,
				null);
		panelBuscaCliente.setLayout(null);

		tableConsulta = new JTable();
		tableConsulta.setFillsViewportHeight(true);

		DefaultTableModel model = (DefaultTableModel) tableConsulta.getModel();
		Object[] titleJTable = { "ID", "Nome", "Email", "Cpf", "Data cadastrada", "Telefone", "Limite Credito" };

		for (int i = 0; i < titleJTable.length; i++) {
			model.addColumn(titleJTable[i]);
		}

		JPopupMenu popupMenu = new JPopupMenu();
		LtpLib.addPopup(tableConsulta, popupMenu);

		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/trash.gif")));

		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DefaultTableModel model = (DefaultTableModel) tableConsulta.getModel();
				int selectedRowIndex = tableConsulta.getSelectedRow();

				int idPessoa = (int) model.getValueAt(selectedRowIndex, 0);

				model.removeRow(selectedRowIndex);

				try {
					Comercial.removerPessoaId(idPessoa);
					System.out.println("Pessoa removida com Sucesso!");
				} catch (SisComException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		popupMenu.add(mntmExcluir);

		setVisible(true);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(14, 12, 1163, 54);
		panelBuscaCliente.add(panel);

		JLabel label = new JLabel("Digite o CPF :");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(10, 4, 119, 46);
		panel.add(label);

		textPesquisarCliente = new JTextField();
		textPesquisarCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPesquisarCliente.setColumns(10);
		textPesquisarCliente.setBounds(131, 4, 521, 46);
		panel.add(textPesquisarCliente);

		JButton button = new JButton("Listar Todos");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					mostarTabelaCliente();
				} catch (SisComException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});

		button.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/lista32x32.png")));
		button.setBounds(909, 4, 236, 46);
		panel.add(button);

		JButton button_1 = new JButton("Pesquisar");
		button_1.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) tableConsulta.getModel();

				try {
					LimpaJtable(model);
					String clientecpf = textPesquisarCliente.getText();
					if (clientecpf.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite Um CPF Para Realizar a Busca!", "Erro",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Cliente cliente = (Cliente) comercial.consultarCpf(textPesquisarCliente.getText(), "cliente");
						System.out.println(cliente);
						Object[] row = new Object[7];

						row[0] = cliente.getCodigo();
						row[1] = cliente.getNome();
						row[2] = cliente.getEmail();
						row[3] = cliente.getCpf();
						row[4] = LtpLib.obterDataFormatada(cliente.getDataCad());
						row[5] = cliente.getTelefone();
						row[6] = cliente.getLimiteCredito();

						model.addRow(row);
					}

				} catch (SisComException e) {
					System.err.println(e.getMessage());
				}
			}
		});
		button_1.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/busca.png")));
		button_1.setBounds(662, 4, 236, 46);
		panel.add(button_1);
		tableConsulta.setFillsViewportHeight(true);

		// add the table to the frame
		JScrollPane scrollPane = new JScrollPane(tableConsulta);
		scrollPane.setBounds(12, 78, 1165, 425);
		panelBuscaCliente.add(scrollPane);

		JLabel bgTela = new JLabel("");
		bgTela.setBounds(0, 0, 1193, 519);
		bgTela.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/bg_image.jpg")));
		panelBuscaCliente.add(bgTela);

		JPanel panelVenda = new JPanel();
		tabbedPane.addTab("Venda", new ImageIcon(TelaCliente.class.getResource("/icones/online-store32x32.png")),
				panelVenda, null);
		panelVenda.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 8, 1165, 66);
		panel_1.setBorder(new TitledBorder(null, "Pesquisa", TitledBorder.CENTER, TitledBorder.TOP, null,

				new Color(102, 150, 153)));
		panelVenda.add(panel_1);

		JLabel lblDigiteOCpf = new JLabel("Digite o CPF :");
		lblDigiteOCpf.setFont(new Font("Dialog", Font.BOLD, 16));

		textCPFVenda = new JTextField();
		textCPFVenda.setColumns(10);

		JButton button_2 = new JButton("Procurar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String clienteCPF = textCPFVenda.getText();
				if (clienteCPF.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite Um CPF Para Realizar a Busca!", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					DefaultTableModel model = (DefaultTableModel) tableClienteCPF.getModel();

					try {
						// LimpaJtable(model);

						Cliente cliente = (Cliente) Comercial.consultarCpf(textCPFVenda.getText(), "cliente");
						Object[] row = new Object[7];

						row[0] = cliente.getCodigo();
						row[1] = cliente.getNome();
						row[2] = cliente.getEmail();
						row[3] = cliente.getCpf();
						row[4] = LtpLib.obterDataFormatada(cliente.getDataCad());
						row[5] = cliente.getTelefone();
						row[6] = cliente.getLimiteCredito();

						model.addRow(row);

					} catch (SisComException e1) {
						System.err.println(e1.getMessage());

					}

				}
			}
		});
		button_2.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/busca.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 1155, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addComponent(lblDigiteOCpf, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textCPFVenda, GroupLayout.PREFERRED_SIZE, 664, GroupLayout.PREFERRED_SIZE)
						.addGap(31).addComponent(button_2, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 66, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDigiteOCpf, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(textCPFVenda, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_2))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 86, 1165, 41);
		panelVenda.add(scrollPane_1);

		tableClienteCPF = new JTable();
		tableClienteCPF.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tableClienteCPF);

		DefaultTableModel model1 = (DefaultTableModel) tableClienteCPF.getModel();
		Object[] titleJTable1 = { "ID", "Nome", "Email", "Cpf", "Data cadastrada", "Telefone", "Limite Credito" };

		for (int i = 0; i < titleJTable1.length; i++) {
			model1.addColumn(titleJTable1[i]);
		}
		setVisible(true);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 132, 277, 373);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Produtos", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(102, 150, 153)));
		panelVenda.add(panel_2);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 22, 253, 348);
		panel_2.add(scrollPane_2);

		tableProdutos = new JTable();
		tableProdutos.setSurrendersFocusOnKeystroke(true);
		tableProdutos.setBorder(UIManager.getBorder("Button.border"));
		tableProdutos.setFillsViewportHeight(true);
		tableProdutos.setModel(mostraTabelaDeProdutosTotal(Comercial.getListaProduto()));
		scrollPane_2.setViewportView(tableProdutos);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(374, 135, 803, 344);
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Carrinho", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(102, 150, 153)));
		panelVenda.add(panel_3);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 59, 779, 273);
		panel_3.add(scrollPane_3);

		tableCarrinho = new JTable();
		DefaultTableModel modelProduto = (DefaultTableModel) tableCarrinho.getModel();
		Object[] titleJTable1Produto = { "Código", "Nome", "Preço Unitário", "Quantidade" };
		for (int i = 0; i < titleJTable1Produto.length; i++) {
			modelProduto.addColumn(titleJTable1Produto[i]);
		}
		setVisible(true);
		tableCarrinho.setFillsViewportHeight(true);
		scrollPane_3.setViewportView(tableCarrinho);

		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/online-store32x32.png")));
		label_4.setBounds(382, 22, 38, 32);
		panel_3.add(label_4);

		JButton btnAddCarrinho = new JButton("");
		btnAddCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
					row[3] = "1";
					model2.addRow(row);

					// REMOVE ITEM QUE FOI PARA A TABELA AO LADO
					model.removeRow(linhaSelecionada);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Selecione um Produto Para Adicionar ao Carrinho ! ", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnAddCarrinho.setBounds(301, 175, 61, 98);
		btnAddCarrinho.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/Add-Cart-32x32.png")));
		btnAddCarrinho.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAddCarrinho.setFont(new Font("Dialog", Font.BOLD, 25));
		btnAddCarrinho.setBackground(new Color(152, 251, 152));
		panelVenda.add(btnAddCarrinho);

		JButton btnRemoveCarrinho = new JButton("");
		btnRemoveCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

					// REMOVE ITEM QUE FOI PARA A TABELA AO LADO
					model2.removeRow(linhaSelecionada);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Selecione um Item Para Devolução", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnRemoveCarrinho.setBounds(301, 305, 61, 110);
		btnRemoveCarrinho.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/cart-remove-32x32.png")));
		btnRemoveCarrinho.setFont(new Font("Dialog", Font.BOLD, 25));
		btnRemoveCarrinho.setBackground(new Color(255, 182, 193));
		panelVenda.add(btnRemoveCarrinho);

		JLabel label_5 = new JLabel("Valor Total : R$");
		label_5.setBounds(579, 489, 126, 16);
		label_5.setForeground(Color.BLACK);
		label_5.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		label_5.setBackground(Color.GREEN);
		panelVenda.add(label_5);

		JButton btnVender = new JButton("VENDER");
		btnVender.setBounds(976, 479, 201, 38);
		btnVender.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/check32x32.png")));
		panelVenda.add(btnVender);

		JLabel VrTotal = new JLabel("0,00");
		VrTotal.setForeground(Color.BLACK);
		VrTotal.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		VrTotal.setBackground(Color.GREEN);
		VrTotal.setBounds(702, 489, 107, 16);
		panelVenda.add(VrTotal);
		
				JLabel bg = new JLabel("");
				bg.setBounds(0, 0, 1193, 581);
				bg.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/bg_image.jpg")));
				panelVenda.add(bg);

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 5, 0);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		setVisible(true);

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
	}

	public void mostarTabelaCliente() throws SisComException {
		DefaultTableModel model = (DefaultTableModel) tableConsulta.getModel();
		LimpaJtable(model);

		ArrayList<Cliente> clientes = Comercial.listaClientes();

		Object[] linha = new Object[7];

		for (int i = 0; i < clientes.size(); i++) {

			linha[0] = clientes.get(i).getCodigo();
			linha[1] = clientes.get(i).getNome();
			linha[2] = clientes.get(i).getEmail();
			linha[3] = clientes.get(i).getCpf();
			linha[4] = LtpLib.obterDataFormatada(clientes.get(i).getDataCad());
			linha[5] = clientes.get(i).getTelefone();
			linha[6] = clientes.get(i).getLimiteCredito();

			model.addRow(linha);
		}

	}

	private void LimpaJtable(DefaultTableModel tabela) {
		// LIMPA TODAS AS LINHAS DA JTABLE
		tabela.setRowCount(0);

	}

	public DefaultTableModel mostraTabelaDeProdutosTotal(ArrayList<Produto> listaProdutos) {

		Object[] titleRow = { "Código", "Nome", "Preço Unitário" };
		Object[][] data = new Object[listaProdutos.size()][titleRow.length];

		for (int i = 0; i < listaProdutos.size(); i++) {

			data[i][0] = listaProdutos.get(i).getCodigo();
			data[i][1] = listaProdutos.get(i).getNome();
			data[i][2] = listaProdutos.get(i).getPrecoUnitario();
		}

		DefaultTableModel model = new DefaultTableModel(data, titleRow);

		return model;
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
