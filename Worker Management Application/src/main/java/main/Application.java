package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import gui.FormLogin;
import javax.swing.SwingConstants;

public class Application extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel gif;
	private static JLabel lblPer;
	private static JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		int x;
		Application frame = new Application();
		frame.setVisible(true);
		try {
			for(x=0;x<=100;x++) {
				Application.progressBar.setValue(x);
				Thread.sleep(10);
				Application.lblPer.setText(Integer.toString(x)+"%");
				if(x==100) {
					frame.dispose();
					FormLogin Frm = new FormLogin();
					Frm.setVisible(true);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * màn hình chờ
	 */
	public Application() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(660, 200, 700, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		gif = new JLabel("");
		ImageIcon icon = new ImageIcon("images/giphy1.gif");
		gif.setIcon(icon);
		gif.setBounds(100, 60, 480, 480);
		contentPane.add(gif);

		progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 28));
		progressBar.setForeground(new Color(255, 204, 102));
		progressBar.setBounds(15, 600, 670, 40);
		contentPane.add(progressBar);
		
		lblPer = new JLabel("");
		lblPer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPer.setFont(new Font("Arial", Font.BOLD, 28));
		lblPer.setBounds(15, 560, 670, 40);
		contentPane.add(lblPer);
		
		JLabel lblNewLabel = new JLabel("loading...");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(15, 645, 670, 40);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 680, 678);
		contentPane.add(panel);
	}
}
