package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class PrimeiraTela {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrimeiraTela window = new PrimeiraTela();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrimeiraTela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JButton btnTesteBotao = new JButton("TEste Botao");
		springLayout.putConstraint(SpringLayout.WEST, btnTesteBotao, 139, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnTesteBotao, -25, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnTesteBotao);
		
		JLabel lblTeste = new JLabel("Teste");
		lblTeste.setFont(new Font("Tahoma", Font.PLAIN, 21));
		springLayout.putConstraint(SpringLayout.WEST, lblTeste, 153, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblTeste, -60, SpringLayout.NORTH, btnTesteBotao);
		frame.getContentPane().add(lblTeste);
	}
}
