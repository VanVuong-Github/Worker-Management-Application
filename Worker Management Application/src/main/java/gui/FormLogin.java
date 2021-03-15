package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

import connect.ConnectDB;
import dao.DAO_Taikhoan;
import decorFrame.RoundedJButton;
import decorFrame.RoundedJPassword;
import decorFrame.RoundedJTextField;
import decorFrame.RoundedPanel;
import entity.TaiKhoan;

public class FormLogin extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnMain;
	private JPasswordField txtPassword;
	int xx, xy;
	private RoundedJTextField txtUserName;
	private RoundedJButton btnLogin;

	private DAO_Taikhoan daoTK = new DAO_Taikhoan();
	private TaiKhoan tk;
	private RoundedPanel pnLogin;
	private JCheckBox chkAdmin;

	/**
	 * Create the frame.
	 */
	public FormLogin() {

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		try {
			setIconImage(ImageIO.read(new File("icons/iconFrameW.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setTitle("CÔNG TY TNHH XÂY DỰNG HLAV");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setResizable(true);
		setBounds(150, 30, 1615, 1000);

		pnMain = new JPanel();
		pnMain.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		pnMain.setBackground(new Color(255, 204, 102));
		pnMain.setBounds(0, 0, 1615, 1000);
		getContentPane().add(pnMain);
		pnMain.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1100, 1000);
		panel.setBackground(Color.BLACK);
		pnMain.add(panel);
		panel.setLayout(null);

		JLabel bgImage = new JLabel("");
		bgImage.setBackground(new Color(255, 255, 255));

		bgImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();
				xy = e.getY();
			}
		});
		bgImage.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				FormLogin.this.setLocation(x - xx, y - xy);
			}
		});
		bgImage.setBounds(0, 129, 1100, 800);
		bgImage.setVerticalAlignment(SwingConstants.TOP);
		bgImage.setIcon(new ImageIcon("images/bg1.png"));
		panel.add(bgImage);

		JLabel lblTitle = new JLabel("<html>CÔNG TY TNHH<br>XÂY DỰNG HLAV");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Serif", Font.BOLD, 55));
		lblTitle.setBounds(1110, 120, 477, 158);
		pnMain.add(lblTitle);

		pnLogin = new RoundedPanel(20);
		pnLogin.setBounds(1110, 357, 477, 470);
		pnLogin.setOpaque(false);
		pnLogin.setForeground(Color.BLACK);
		pnLogin.setBackground(new Color(255, 204, 102));
		pnMain.add(pnLogin);
		pnLogin.setLayout(null);

		JLabel lblLogin = new JLabel("Đăng nhập");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(0, 67, 477, 65);
		pnLogin.add(lblLogin);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 48));

		txtUserName = new RoundedJTextField(10, null);
		txtUserName.setBounds(55, 171, 412, 60);
		pnLogin.add(txtUserName);
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 28));
		txtUserName.setText("admin");
		txtUserName.setColumns(10);

		txtPassword = new RoundedJPassword(10, null);
		txtPassword.setBounds(55, 236, 412, 60);
		pnLogin.add(txtPassword);
		txtPassword.setText("admin");
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 28));

		btnLogin = new RoundedJButton("Đăng nhập");
		btnLogin.setBounds(10, 354, 457, 70);
		pnLogin.add(btnLogin);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 32));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setOpaque(false);
		btnLogin.setBackground(new Color(241, 57, 83));
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		chkAdmin = new JCheckBox("Quản lý");
		chkAdmin.putClientProperty("JComponent.sizeVariant", "large");
		chkAdmin.setFont(new Font("Tahoma", Font.PLAIN, 26));
		chkAdmin.setBackground(new Color(255, 204, 102));
		chkAdmin.setBounds(333, 302, 134, 50);
		pnLogin.add(chkAdmin);
		chkAdmin.setSelected(true);

		JLabel lblIconUser = new JLabel("");
		lblIconUser.setIcon(new ImageIcon("icons/user.png"));
		lblIconUser.setBounds(10, 180, 40, 40);
		pnLogin.add(lblIconUser);

		JLabel lblIconPW = new JLabel("");
		lblIconPW.setIcon(new ImageIcon("icons/unlock.png"));
		lblIconPW.setBounds(10, 243, 40, 40);
		pnLogin.add(lblIconPW);

		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnLogin.addActionListener(this);
		txtPassword.addActionListener(this);
		txtUserName.addActionListener(this);
	}

	private boolean validInput() {
		if (txtUserName.getText().equals("")) {
			JOptionPane.showMessageDialog(getContentPane(), "Vui lòng nhập tên tài khoản!", "Lỗi",
					JOptionPane.WARNING_MESSAGE);
			txtUserName.requestFocus();
			return false;
		}
		if (new String(txtPassword.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(getContentPane(), "Vui lòng nhập mật khẩu", "Lỗi",
					JOptionPane.WARNING_MESSAGE);
			txtPassword.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLogin)) {
			if (validInput()) {
				String user = txtUserName.getText().trim().toLowerCase();
				String pass = new String(txtPassword.getPassword());
				if (!(chkAdmin.isSelected())) {
					if (daoTK.checkAccount(user, pass)) {
						tk = daoTK.getTaikhoanByName(user);
						new TaskLogin_NV(tk, this).execute();
					} else {
						JOptionPane.showMessageDialog(this,
								"Đăng nhập không thành công! \n Mật Khẩu hoặc tên người dùng không chính xác", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					if (daoTK.checkAccount(user, pass)) {
						tk = daoTK.getTaikhoanByName(user);
						new TaskLogin(tk, this).execute();
					} else {
						JOptionPane.showMessageDialog(this,
								"Đăng nhập không thành công! \n Mật Khẩu hoặc tên người dùng không chính xác", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else if (o.equals(txtUserName)) {
			txtPassword.requestFocus();
		} else if (o.equals(txtPassword)) {
			btnLogin.doClick();
		}
	}
}

class TaskLogin extends SwingWorker<Void, Void> {

	private TaiKhoan tk;
	private JFrame jframe;

	public TaskLogin(TaiKhoan tk, JFrame jframe) {
		this.tk = tk;
		this.jframe = jframe;
	}

	@Override
	protected Void doInBackground() throws Exception {
		FormTrangChu frame = new FormTrangChu(tk);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		frame.setSize(r.width, r.height);
		frame.setVisible(true);
		return null;
	}

	@Override
	protected void done() {
		this.jframe.dispose();
	}
}

class TaskLogin_NV extends SwingWorker<Void, Void> {

	private TaiKhoan tk;
	private JFrame jframe;

	public TaskLogin_NV(TaiKhoan tk, JFrame jframe) {
		this.tk = tk;
		this.jframe = jframe;
	}

	@Override
	protected Void doInBackground() throws Exception {
		FormTrangChu_NV frame = new FormTrangChu_NV(tk);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		frame.setSize(r.width, r.height);
		frame.setVisible(true);
		return null;
	}

	@Override
	protected void done() {
		this.jframe.dispose();
	}
}