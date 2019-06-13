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
import javax.swing.Timer;
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
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class TelaProdutos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textNome;
	private JTextField textEstoqueMinimo;
	private JTextField textEstoque;
	private JTextField textPrecoUnitario;
	private JTextField textCodigoProduto;
	private JTable tableBusca;
	private static Comercial comercial = new Comercial();

	public TelaProdutos() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			// AO FECHAR ESSA JANELA ELE MOSTRA O MENU INICIAL NOVAMENTE
			public void windowClosing(WindowEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
			}
		});

		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaProdutos.class.getResource("/icones/produtos.png")));
		setTitle("SISCOM - PRODUTOS");

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

		JPanel panelClienteCadastro = new JPanel();
		panelClienteCadastro.setBackground(new Color(204, 255, 255));
		tabbedPane.addTab("CADASTRO", new ImageIcon(TelaProdutos.class.getResource("/icones/produtos32x32.png")),
				panelClienteCadastro, null);
		panelClienteCadastro.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(126, 46, 145, 49);
		lblNome.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		panelClienteCadastro.add(lblNome);

		textNome = new JTextField();
		textNome.setBounds(281, 48, 693, 49);
		panelClienteCadastro.add(textNome);
		textNome.setColumns(10);

		JLabel lblEmail = new JLabel("Pre\u00E7o Unit\u00E1rio");
		lblEmail.setBounds(109, 139, 162, 49);
		lblEmail.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		panelClienteCadastro.add(lblEmail);

		textPrecoUnitario = new JTextField();
		textPrecoUnitario.setBounds(281, 141, 693, 49);
		textPrecoUnitario.setColumns(10);
		panelClienteCadastro.add(textPrecoUnitario);

		JLabel lblTelefone = new JLabel("Estoque");
		lblTelefone.setBounds(126, 232, 145, 49);
		lblTelefone.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		panelClienteCadastro.add(lblTelefone);

		textEstoque = new JTextField();
		textEstoque.setBounds(281, 234, 693, 49);
		textEstoque.setColumns(10);
		panelClienteCadastro.add(textEstoque);

		JLabel lblCnpj = new JLabel("Estoque Minimo");
		lblCnpj.setBounds(109, 325, 162, 49);
		lblCnpj.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		panelClienteCadastro.add(lblCnpj);

		textEstoqueMinimo = new JTextField();
		textEstoqueMinimo.setBounds(281, 327, 693, 49);
		textEstoqueMinimo.setColumns(10);
		panelClienteCadastro.add(textEstoqueMinimo);

		JButton btnCadastroProd = new JButton("Cadastrar");
		btnCadastroProd.setIcon(new ImageIcon(TelaProdutos.class.getResource("/icones/check32x32.png")));
		btnCadastroProd.setBounds(281, 420, 693, 49);
		btnCadastroProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// SALVA NOVO PRODUTO
				try {
					int codigo = 0;

					if (Comercial.getListaProduto().isEmpty()) {
						codigo = 1;
					} else {
						codigo = Comercial.getListaProduto().size() + 1;
					}

					String nome = textNome.getText();

					double precoUnitario = Double.parseDouble(textPrecoUnitario.getText().replace(',', '.'));

					int estoque = Integer.parseInt(textEstoque.getText());
					int estoqueMinimo = Integer.parseInt(textEstoqueMinimo.getText());
					Date dataCadastrada = new Date();

					Produto produto = new Produto(codigo, nome, precoUnitario, estoque, estoqueMinimo, dataCadastrada);
					Comercial.getListaProduto().add(produto);

					JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!");
					// mostarTabelaCliente();
					try {
						LtpLib.gravarArquivoObjetosArray("Produtos.obj", Comercial.getListaProduto());
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

		btnCadastroProd.setBackground(new Color(144, 238, 144));
		btnCadastroProd.setForeground(new Color(0, 0, 0));
		btnCadastroProd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		panelClienteCadastro.add(btnCadastroProd);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 2000, 514);
		panelClienteCadastro.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(TelaProdutos.class.getResource("/icones/bg_image.jpg")));

		JPanel panelBuscaProdutos = new JPanel();
		tabbedPane.addTab("Busca", new ImageIcon(TelaCliente.class.getResource("/icones/busca.png")),
				panelBuscaProdutos, null);
		panelBuscaProdutos.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 23, 1169, 66);
		panelBuscaProdutos.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Digite o C\u00F3digo :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(10, 11, 134, 44);
		panel.add(lblNewLabel_1);

		textCodigoProduto = new JTextField();
		textCodigoProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) tableBusca.getModel();

				// EXECUTA AO SER PRESIONADA E TECLA ENTER SOMENTE
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					try {
						LimparTabela(model);
						Produto produtos = (Produto) Comercial
								.consultarCodigoProduto(Integer.parseInt(textCodigoProduto.getText()));
						Object[] row = new Object[6];

						row[0] = produtos.getCodigo();
						row[1] = produtos.getNome();
						row[2] = produtos.getPrecoUnitario();
						row[3] = produtos.getEstoque();
						row[4] = produtos.getEstoqueMinimo();
						row[5] = LtpLib.obterDataFormatada(produtos.getDataCadastrada());

						model.addRow(row);

					} catch (SisComException o) {
						System.err.println(o.getMessage());
					} catch (NumberFormatException b) {
						// JOptionPane.showMessageDialog(null, "Informe apenas números");
					}
				}

				// EXECUTA AO SER PRESIONADA QUALQUER TECLA
				try {

					LimparTabela(model);
					Produto produtos = (Produto) comercial
							.consultarCodigoProduto(Integer.parseInt(textCodigoProduto.getText()));
					Object[] row = new Object[6];

					row[0] = produtos.getCodigo();
					row[1] = produtos.getNome();
					row[2] = produtos.getPrecoUnitario();
					row[3] = produtos.getEstoque();
					row[4] = produtos.getEstoqueMinimo();
					row[5] = LtpLib.obterDataFormatada(produtos.getDataCadastrada());

					model.addRow(row);

				} catch (SisComException a) {
					System.err.println(a.getMessage());
				} catch (NumberFormatException b) {
					// JOptionPane.showMessageDialog(null, "Informe apenas números");
				}
			}

			private void LimparTabela(DefaultTableModel model) {
				model.setRowCount(0);

			}
		});
		textCodigoProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		textCodigoProduto.setBounds(149, 11, 656, 44);
		panel.add(textCodigoProduto);
		textCodigoProduto.setColumns(10);

		JButton btnPesquisar = new JButton("PESQUISAR");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) tableBusca.getModel();

				// EXECUTA AO SER PRESIONADA E TECLA ENTER SOMENTE
				String codigoProd = textCodigoProduto.getText();
				if (codigoProd.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite Um Código Para Realizar a Busca!", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					
				
				try {
					model.setRowCount(0);;
					Produto produtos = (Produto) Comercial.consultarCodigoProduto(Integer.parseInt(textCodigoProduto.getText()));
					Object[] row = new Object[6];

					row[0] = produtos.getCodigo();
					row[1] = produtos.getNome();
					row[2] = produtos.getPrecoUnitario();
					row[3] = produtos.getEstoque();
					row[4] = produtos.getEstoqueMinimo();
					row[5] = LtpLib.obterDataFormatada(produtos.getDataCadastrada());

					model.addRow(row);

				} catch (SisComException o) {
					System.err.println(o.getMessage());
				} catch (NumberFormatException b) {
					// JOptionPane.showMessageDialog(null, "Informe apenas números");
				}
			}

			}
		});
		btnPesquisar.setIcon(new ImageIcon(TelaProdutos.class.getResource("/icones/busca.png")));
		btnPesquisar.setBounds(817, 12, 154, 44);
		panel.add(btnPesquisar);

		JButton btnListarTodos = new JButton("LISTAR TODOS");
		btnListarTodos.setIcon(new ImageIcon(TelaProdutos.class.getResource("/icones/lista32x32.png")));
		btnListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) tableBusca.getModel();

				ArrayList<Produto> listaProdutos = Comercial.getListaProduto();

				Object[] data = new Object[6];
				model.setRowCount(0);
				for (int i = 0; i < listaProdutos.size(); i++) {

					data[0] = listaProdutos.get(i).getCodigo();
					data[1] = listaProdutos.get(i).getNome();
					data[2] = listaProdutos.get(i).getPrecoUnitario();
					data[3] = listaProdutos.get(i).getEstoque();
					data[4] = listaProdutos.get(i).getEstoqueMinimo();
					data[5] = LtpLib.obterDataFormatada(listaProdutos.get(i).getDataCadastrada());

					model.addRow(data);
				}

			}
		});
		btnListarTodos.setBounds(983, 11, 176, 44);
		panel.add(btnListarTodos);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Opções Avançadas ", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(102, 100, 153)));
		panel_1.setBounds(12, 93, 1167, 99);
		panelBuscaProdutos.add(panel_1);

		JRadioButton radioOrdernarPorNome = new JRadioButton("Ordenar por nome");

		JRadioButton radioAbaixoMinimo = new JRadioButton("Abaixo do m\u00EDnimo");

		radioOrdernarPorNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				radioAbaixoMinimo.setSelected(false);

				radioOrdernarPorNome.setSelected(true);
			}
		});

		radioAbaixoMinimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				radioOrdernarPorNome.setSelected(false);

				radioAbaixoMinimo.setSelected(true);
			}
		});

		JButton button = new JButton("Listar todos");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) tableBusca.getModel();
					LimparTabela(model);

					if (radioOrdernarPorNome.isSelected()) {

						ArrayList<Produto> produtos = Comercial.obterListaProdutosNomeOrdenada();
						Object[] colum = new Object[6];

						for (int i = 0; i < produtos.size(); i++) {

							colum[0] = produtos.get(i).getCodigo();
							colum[1] = produtos.get(i).getNome();
							colum[2] = produtos.get(i).getPrecoUnitario();
							colum[3] = produtos.get(i).getEstoque();
							colum[4] = produtos.get(i).getEstoqueMinimo();
							colum[5] = LtpLib.obterDataFormatada(produtos.get(i).getDataCadastrada());

							model.addRow(colum);
						}
					} else if (radioAbaixoMinimo.isSelected()) {

						ArrayList<Produto> produtos = null;
						produtos = Comercial.obterListaProdutosEstoqueMin();
						Object[] colum = new Object[6];

						for (int i = 0; i < produtos.size(); i++) {

							colum[0] = produtos.get(i).getCodigo();
							colum[1] = produtos.get(i).getNome();
							colum[2] = produtos.get(i).getPrecoUnitario();
							colum[3] = produtos.get(i).getEstoque();
							colum[4] = produtos.get(i).getEstoqueMinimo();
							colum[5] = LtpLib.obterDataFormatada(produtos.get(i).getDataCadastrada());

							model.addRow(colum);
						}
					}

				} catch (SisComException e1) {
					System.out.println(e1.getMessage());

				}
			}

			private void LimparTabela(DefaultTableModel model) {
				model.setRowCount(0);

			}
		});
		button.setIcon(new ImageIcon(TelaProdutos.class.getResource("/icones/lista32x32.png")));

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_1.createSequentialGroup().addGap(287)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(radioOrdernarPorNome, GroupLayout.DEFAULT_SIZE, 284,
												Short.MAX_VALUE)
										.addGap(30).addComponent(radioAbaixoMinimo, GroupLayout.PREFERRED_SIZE, 265,
												Short.MAX_VALUE)))
						.addGap(291)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(radioOrdernarPorNome)
								.addComponent(radioAbaixoMinimo))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE).addContainerGap()));
		panel_1.setLayout(gl_panel_1);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 196, 1169, 307);
		panelBuscaProdutos.add(scrollPane);

		tableBusca = new JTable();
		scrollPane.setViewportView(tableBusca);

		DefaultTableModel model = (DefaultTableModel) tableBusca.getModel();
		Object[] titleJTable = { "ID", "Nome", "Preço Unitário", "Estoque", "Estoque Mínimo", "Data Cadastrada" };

		for (int i = 0; i < titleJTable.length; i++) {
			model.addColumn(titleJTable[i]);
		}

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaProdutos.class.getResource("/icones/bg_image.jpg")));
		label.setBounds(0, 23, 1189, 514);
		panelBuscaProdutos.add(label);

		setVisible(true);

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
}
