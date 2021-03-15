package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import connect.ConnectDB;
import dao.DAO_Nhanvien;
import dao.DAO_Taikhoan;
import decorFrame.PlaceholderTextField;
import decorFrame.RoundedJButton;
import entity.NhanVien;
import entity.TaiKhoan;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JComboBox;

public class FormThemNhanvien extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PlaceholderTextField txtTenNV;
	private PlaceholderTextField txtCMND;
	private PlaceholderTextField txtEmail;
	private PlaceholderTextField txtSDT;
	private PlaceholderTextField txtDiachi;
	private JDateChooser ngaysinh;
	private JButton btnThemNhanvien;
	private JPanel panel_Info;
	private JComboBox<String> cbGioitinh;
	private RoundedJButton btnDong;
	private PlaceholderTextField txtTaiKhoan;

	/**
	 * Create the frame.
	 */
	public FormThemNhanvien() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 250, 750, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		panel_Info = new JPanel();
		panel_Info.setBounds(0, 0, 734, 521);
		panel_Info.setBackground(SystemColor.WHITE);
		contentPane.add(panel_Info);
		panel_Info.setLayout(null);

		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblTenNV.setBounds(10, 100, 190, 30);
		panel_Info.add(lblTenNV);

		txtTenNV = new PlaceholderTextField();
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenNV.setPlaceholder("Nhập tên");
		txtTenNV.setBounds(200, 100, 524, 30);
		panel_Info.add(txtTenNV);
		txtTenNV.setColumns(10);

		JLabel lblGioitinh = new JLabel("Giới tính:");
		lblGioitinh.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblGioitinh.setBounds(10, 145, 190, 30);
		panel_Info.add(lblGioitinh);

		cbGioitinh = new JComboBox<String>();
		cbGioitinh.setFont(new Font("Tahoma", Font.PLAIN, 24));
		cbGioitinh.addItem("Nam");
		cbGioitinh.addItem("Nữ");
		cbGioitinh.setBounds(200, 145, 262, 30);
		panel_Info.add(cbGioitinh);

		JLabel lblNgaysinh = new JLabel("Ngày sinh:");
		lblNgaysinh.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNgaysinh.setBounds(10, 190, 190, 30);
		panel_Info.add(lblNgaysinh);

		ngaysinh = new JDateChooser();
		ngaysinh.setDateFormatString("dd/MM/yyyy");
		ngaysinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ngaysinh.setPreferredSize(new Dimension(400, 400));
		ngaysinh.getJCalendar().getMonthChooser().setPreferredSize(new Dimension(150, 30));
		ngaysinh.setBounds(200, 190, 262, 30);
		panel_Info.add(ngaysinh);

		JLabel lblCMND = new JLabel("CMND:");
		lblCMND.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblCMND.setBounds(10, 231, 190, 30);
		panel_Info.add(lblCMND);

		txtCMND = new PlaceholderTextField();
		txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCMND.setPlaceholder("Nhập CMND");
		txtCMND.setColumns(10);
		txtCMND.setBounds(200, 231, 524, 30);
		panel_Info.add(txtCMND);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEmail.setBounds(10, 276, 190, 30);
		panel_Info.add(lblEmail);

		txtEmail = new PlaceholderTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEmail.setPlaceholder("Nhập email");
		txtEmail.setColumns(10);
		txtEmail.setBounds(200, 276, 524, 30);
		panel_Info.add(txtEmail);

		JLabel lblSDT = new JLabel("SDT:");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblSDT.setBounds(10, 321, 190, 30);
		panel_Info.add(lblSDT);

		txtSDT = new PlaceholderTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSDT.setPlaceholder("Nhập SDT");
		txtSDT.setColumns(10);
		txtSDT.setBounds(200, 321, 524, 30);
		panel_Info.add(txtSDT);

		JLabel lblDiachi = new JLabel("Địa chỉ:");
		lblDiachi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiachi.setBounds(10, 366, 190, 30);
		panel_Info.add(lblDiachi);

		txtDiachi = new PlaceholderTextField();
		txtDiachi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDiachi.setPlaceholder("Nhập địa chỉ");
		txtDiachi.setColumns(10);
		txtDiachi.setBounds(200, 366, 524, 30);
		panel_Info.add(txtDiachi);

		btnThemNhanvien = new RoundedJButton("Thêm nhân viên");
		btnThemNhanvien.setBackground(new Color(255, 204, 102));
		btnThemNhanvien.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnThemNhanvien.setBounds(458, 460, 266, 50);
		panel_Info.add(btnThemNhanvien);

		btnDong = new RoundedJButton("Đóng");
		btnDong.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnDong.setBackground(new Color(255, 204, 102));
		btnDong.setBounds(10, 460, 130, 50);
		panel_Info.add(btnDong);

		JPanel panel_Title = new JPanel();
		panel_Title.setBounds(0, 0, 734, 70);
		panel_Info.add(panel_Title);
		panel_Title.setBackground(new Color(255, 204, 102));
		panel_Title.setLayout(null);

		JLabel lblTitle = new JLabel("THÊM NHÂN VIÊN MỚI");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(21, 25, 28));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitle.setBounds(0, 9, 704, 50);
		panel_Title.add(lblTitle);

		btnDong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThemNhanvien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbGioitinh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel lblTaiKhoan = new JLabel("Tài khoản:");
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblTaiKhoan.setBounds(10, 407, 190, 30);
		panel_Info.add(lblTaiKhoan);

		txtTaiKhoan = new PlaceholderTextField();
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTaiKhoan.setPlaceholder("Nhập tài khoản");
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.setBounds(200, 407, 524, 30);
		panel_Info.add(txtTaiKhoan);

		btnDong.addActionListener(this);
		btnThemNhanvien.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		DAO_Nhanvien dao_Nhanvien = new DAO_Nhanvien();
		DAO_Taikhoan dao_Taikhoan = new DAO_Taikhoan();
		if (o.equals(btnThemNhanvien)) {
			if (validData()) {
				String ten = txtTenNV.getText();
				String gt = (String) cbGioitinh.getSelectedItem();
				Date ns = new Date(ngaysinh.getDate().getTime());
				String phone = txtSDT.getText();
				String cmnd = txtCMND.getText();
				String email = txtEmail.getText();
				String diachi = txtDiachi.getText();
				String username = txtTaiKhoan.getText();
				String pass = "123456"; // mật khẩu mặc định
				TaiKhoan tk = new TaiKhoan(username, pass);
				NhanVien nv = new NhanVien(ten, gt, ns, phone, cmnd, email, diachi, "Đi Làm", tk);
				try {
					if (dao_Taikhoan.themTaikhoan(tk) && dao_Nhanvien.themNhanvien(nv)) {
						JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
						this.dispose();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra khi thêm nhân viên.\nVui lòng thử lại sau");
					e1.printStackTrace();
				}
			}
		}
		if (o.equals(btnDong)) {
			this.dispose();
		}
	}

	// thay đổi màn hình xử lý của giao diện
	public void changeScreen(JPanel newScreen) {
		panel_Info.removeAll();
		panel_Info.add(newScreen);
		panel_Info.repaint();
		panel_Info.revalidate();

	}

	/**
	 * Kiểm tra dữ liệu đầu vào Rỗng return false
	 * 
	 * @return true
	 */
	public boolean validData() {
		String ten = txtTenNV.getText().trim();
		String cmnd = txtCMND.getText().trim();
		String email = txtEmail.getText().trim();
		String sdt = txtSDT.getText().trim();
		DAO_Taikhoan dao_Taikhoan = new DAO_Taikhoan();
		DAO_Nhanvien dao_Nhanvien = new DAO_Nhanvien();
		String taikhoan = txtTaiKhoan.getText().trim();
		List<NhanVien> nhanviens = null;
		try {
			nhanviens = dao_Nhanvien.getDsNhanvien();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (!(ten.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Tên rỗng");
			txtTenNV.requestFocus();
			return false;
		}
		if (ngaysinh.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Ngày sinh rỗng");
			ngaysinh.requestFocus();
			return false;
		} else {
			int tuoi = LocalDate.now().getYear()
					- ngaysinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
			if (tuoi < 18 || tuoi > 64) {
				JOptionPane.showMessageDialog(this, "Tuổi nhân viên phải lớn hơn 18 và nhỏ hơn 64");
				return false;
			}
		}
		for (NhanVien nhanVien : nhanviens) {

			if (nhanVien.getCmnd().equals(cmnd) && !(nhanVien.getCmnd().equals(""))) {
				JOptionPane.showMessageDialog(this, "CMND đã tồn tại");
				return false;
			}

		}
		if (!(email.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Email rỗng");
			txtEmail.requestFocus();
			return false;
		}
		for (NhanVien nhanVien : nhanviens) {
			if (nhanVien.getEmail().equals(email)) {
				JOptionPane.showMessageDialog(this, "Email đã tồn tại");
				return false;
			}
		}
		if (!((sdt.length() > 0) && sdt.matches("[0-9]{10}"))) {
			JOptionPane.showMessageDialog(this, "SDT không hợp lệ.\nSDT chỉ chứa số và phải có đủ 10 số");
			txtSDT.requestFocus();
			return false;
		}
		for (NhanVien nhanVien : nhanviens) {
			if (nhanVien.getPhone().equals(sdt)) {
				JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
				return false;
			}
		}
		if (!((taikhoan.length() > 0)
				&& taikhoan.matches("^[a-zA-Z0-9](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){6,18}[a-zA-Z0-9]$"))) {
			JOptionPane.showMessageDialog(this,
					"Tài khoản không hợp lệ.\nTài khoản từ 6-18 kí tự, phải chứa cả kí tự và số, không chứa kí tự đặc biệt");
			txtTaiKhoan.requestFocus();
			return false;
		} else {
			try {
				TaiKhoan tk = null;
				tk = dao_Taikhoan.checkTenTaikhoanAvailable(txtTaiKhoan.getText());
				if (tk != null) {
					JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại");
					txtTaiKhoan.requestFocus();
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
