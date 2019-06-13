package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Menu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Menu() {

		setResizable(false);
		setTitle("SISCOM - AUTO INSTRUCIONAL 01/2018");

		int width = 1066;
		int height = 397;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, 1066, 430);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/icones/produtos.png")));
		setBackground(Color.GRAY);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		panel.setBounds(237, 6, 598, 69);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("SISTEMA COMERCIAL 2018/01");
		lblNewLabel.setBounds(145, 0, 308, 34);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/icones/online-store-2-32x32.png")));
		lblNewLabel.setForeground(new Color(0, 120, 215));
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		
				JLabel lblData = new JLabel("data");
				lblData.setBounds(369, 29, 84, 40);
				panel.add(lblData);
				lblData.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 15));
				lblData.setForeground(new Color(0, 120, 215));
				
						JLabel lblHora = new JLabel("hora");
						lblHora.setBounds(148, 29, 96, 40);
						panel.add(lblHora);
						lblHora.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 15));
						lblHora.setForeground(new Color(0, 120, 215));
		
		Thread horario = new Thread(new Runnable() {

            public void run() {
                while (true) {
                	lblData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new GregorianCalendar().getTime()));
                	lblHora.setText(new SimpleDateFormat("hh:mm:ss a").format(new GregorianCalendar().getTime()));
                    try {
                        Thread.sleep(1000); // Interromper por 1000 milisegundos
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
		
		horario.start();

		JButton btnCliente = new JButton("CLIENTE");
		btnCliente.setBackground(UIManager.getColor("Button.shadow"));
		btnCliente.setBounds(37, 87, 217, 290);
		btnCliente.setIcon(new ImageIcon(Menu.class.getResource("/icones/cliente.png")));
		btnCliente.setFont(new Font("Dialog", Font.PLAIN, 21));

		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// OCULTA A JFRAME DO MENU
				setVisible(false);
				// MOSTRA JFRAME CLIENTE
				TelaCliente cliente = new TelaCliente();
				cliente.setVisible(true);
			}
		});
		getContentPane().add(btnCliente);

		JButton btnProdutos = new JButton("PRODUTOS");
		btnProdutos.setBounds(804, 87, 217, 290);
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				// MOSTRA JFRAME VENDEDOR
				TelaProdutos telaprodutos = new TelaProdutos();
				telaprodutos.setVisible(true);

			}
		});

		JButton btnVendedor = new JButton("VENDEDOR");
		btnVendedor.setBounds(549, 87, 217, 290);
		btnVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				// MOSTRA JFRAME VENDEDOR
				TelaVendedor telavendedor = new TelaVendedor();
				telavendedor.setVisible(true);
			}
		});

		// ImageIcon IconeCliente = new ImageIcon(new
		// ImageIcon("//images/cliente.png").getImage().getScaledInstance(45, 45,
		// Image.SCALE_DEFAULT));

		JButton btnFornecedor = new JButton("FORNECEDOR");
		btnFornecedor.setBounds(291, 87, 217, 290);
		btnFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				setVisible(false);
				// MOSTRA JFRAME CLIENTE
				TelaFornecedor telafornecedor = new TelaFornecedor();
				telafornecedor.setVisible(true);

			}
		});
		btnFornecedor.setFont(new Font("Dialog", Font.PLAIN, 21));
		btnFornecedor.setIcon(new ImageIcon(Menu.class.getResource("/icones/fornecedor.png")));
		getContentPane().add(btnFornecedor);
		btnVendedor.setFont(new Font("Dialog", Font.PLAIN, 21));
		btnVendedor.setIcon(new ImageIcon(Menu.class.getResource("/icones/vendedor32x32.png")));
		getContentPane().add(btnVendedor);
		btnProdutos.setFont(new Font("Dialog", Font.PLAIN, 21));
		btnProdutos.setIcon(new ImageIcon(Menu.class.getResource("/icones/produtos32x32.png")));
		getContentPane().add(btnProdutos);

		JLabel label = new JLabel("New label");
		label.setIcon(new ImageIcon(Menu.class.getResource("/icones/bg_image.jpg")));
		label.setBounds(0, 0, 1048, 401);
		getContentPane().add(label);

		setVisible(true);
	}
}
