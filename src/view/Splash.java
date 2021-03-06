package view;

import java.awt.*;
import javax.swing.*;

// O JWindow � uma Janela sem barra de titulo, sem bot�es de minimizar, maximizar e fechar ... 
// O JFrame tamb�m � uma Janela mas ele possui borda, barra de t�tulo, bot�es de minimizar, fechar maximizar ...

public class Splash extends JWindow {

	private static final long serialVersionUID = 1L;

	private int duration;

	public Splash(int d) {
		duration = d;
	}

	// Este � um m�todo simples para mostrar uma tela de apresent��o
	// no centro da tela durante a quantidade de tempo passada no construtor
	public void showSplash() {
		JPanel content = (JPanel) getContentPane();
		content.setBackground(Color.white);

		// Configura a posi��o e o tamanho da janela
		int width = 520;
		int height = 450;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2; 
		setBounds(x, y, width, height);

		// Constr�i o splash screen
		
		JLabel imgsplash1 = new JLabel(new ImageIcon("images/carrinho.png"));
		JLabel imgsplash2 = new JLabel(new ImageIcon("images/carregando.gif"));
		
		JLabel Sicom = new JLabel("SISCOM 1.0", JLabel.CENTER);
		Sicom.setFont(new Font("Sans-Serif", Font.BOLD, 35));
		Sicom.setForeground(new Color(0, 120, 215));
		content.add(Sicom, BorderLayout.NORTH);
		
		JLabel copyrt = new JLabel("Auto Instrucional 2018 LTP3 - Caio Graciano", JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 15));
		copyrt.setForeground(new Color(0, 120, 215));
		content.add(imgsplash1, BorderLayout.LINE_START);
		content.add(imgsplash2, BorderLayout.CENTER);
	
		
		content.add(copyrt, BorderLayout.SOUTH);
		Color cor = new Color(0, 0, 125, 200);
		content.setBorder(BorderFactory.createLineBorder(cor, 2));

		// Torna vis�vel
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