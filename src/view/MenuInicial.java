package view;

import javax.swing.JPanel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuInicial extends JPanel{
	public MenuInicial() {
		
		setSize(926,149);

		
		JButton btnNewButton = new JButton("CLIENTE");	
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				setVisible(false);
				TelaCliente cliente = new TelaCliente();
				cliente.setVisible(true);
			}
		});
		
		//SETA A IMAGEM DENTRO DO BOTÃO
		ImageIcon IconeCliente = new ImageIcon(new ImageIcon("C:\\Users\\PHD1\\eclipse-workspace\\A-I LTP3\\src\\images\\png\\cliente.png").getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
		btnNewButton.setIcon(IconeCliente);
		
		
		JButton btnFornecedor = new JButton("FORNECEDOR");
		//SETA A IMAGEM DENTRO DO BOTÃO
		ImageIcon IconeFornecedor = new ImageIcon(new ImageIcon("C:\\Users\\PHD1\\eclipse-workspace\\A-I LTP3\\src\\images\\png\\fornecedor.png").getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
		btnFornecedor.setIcon(IconeFornecedor);
		
		
		JButton btnVendedor = new JButton("VENDEDOR");
		//SETA A IMAGEM DENTRO DO BOTÃO
		ImageIcon IconeVendedor = new ImageIcon(new ImageIcon("C:\\Users\\PHD1\\eclipse-workspace\\A-I LTP3\\src\\images\\png\\vendedor.png").getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
		btnVendedor.setIcon(IconeVendedor);
		
		
		JButton btnProdutos = new JButton("PRODUTOS");
		//SETA A IMAGEM DENTRO DO BOTÃO
		ImageIcon IconeProdutos = new ImageIcon(new ImageIcon("C:\\Users\\PHD1\\eclipse-workspace\\A-I LTP3\\src\\images\\png\\produtos.png").getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
		btnProdutos.setIcon(IconeProdutos);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(btnFornecedor, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnVendedor, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnProdutos, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFornecedor, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVendedor, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnProdutos, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
}
