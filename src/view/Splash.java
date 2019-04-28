package view;

import java.awt.*;
import javax.swing.*;

// O JWindow é uma Janela sem barra de titulo, sem botões de minimizar, maximizar e fechar ... 
// O JFrame também é uma Janela mas ele possui borda, barra de título, botões de minimizar, fechar maximizar ...

public class Splash extends JWindow {

	private static final long serialVersionUID = 1L;

	private int duration;

	public Splash(int d) {
		duration = d;
	}

	// Este é um método simples para mostrar uma tela de apresentção
	// no centro da tela durante a quantidade de tempo passada no construtor
	public void showSplash() {
		JPanel content = (JPanel) getContentPane();
		content.setBackground(Color.white);

		// Configura a posição e o tamanho da janela
		int width = 520;
		int height = 450;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2; 
		setBounds(x, y, width, height);

		// Constrói o splash screen
		JLabel imgsplash1 = new JLabel(new ImageIcon("images/carrinho.png"));
		JLabel imgsplash2 = new JLabel(new ImageIcon("images/carregando.gif"));
		
		JLabel copyrt = new JLabel("Auto Instrucional 2018 LTP3 - Caio Graciano", JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 15));
		content.add(imgsplash1, BorderLayout.NORTH);
		content.add(imgsplash2, BorderLayout.CENTER);
	
		
		content.add(copyrt, BorderLayout.SOUTH);
		Color cor = new Color(0, 0, 125, 200);
		content.setBorder(BorderFactory.createLineBorder(cor, 2));

		// Torna visível
		setVisible(true);

		// Espera ate que os recursos estejam carregados
		try {
			Thread.sleep(duration);
		} catch (Exception e) {
		}
		
		setVisible(false);
	}

	public void showSplashAndExit() {
		showSplash();
	}

}