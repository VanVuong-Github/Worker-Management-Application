package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import connect.ConnectDB;
import dao.DAO_Chuyenmon;
import dao.DAO_CongTrinh;
import dao.DAO_CongViec;
import decorFrame.RoundedJButton;
import entity.CongTrinh;
import entity.CongViec;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FormCapNhatCongViec extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTenCV;
	private JPanel contentPane;
	private JComboBox<String> cbChuyenmon;
	private JButton btnDong;
	private JPanel panel_Title;
	private JDateChooser dcNgayBatdau;
	private JButton btnCapNhat;
	private ArrayList<CongTrinh> ct;
	private CongViec cvMoi;
	private CongTrinh congTrinh;
	private JComboBox<String> cbCongtrinh;
	private JTextArea taMota;
	private JTextField txtError;

	/**
	 * Create the frame.
	 */
	public FormCapNhatCongViec(String idCV) {
		setTitle("Cập nhật công việc");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 250, 705, 612);

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
		
		DAO_CongTrinh daoCT = new DAO_CongTrinh();
		DAO_Chuyenmon dao_cm = new DAO_Chuyenmon();
		DAO_CongViec dao_cv = new DAO_CongViec();
		CongViec cv = null;
		try {
			cv = dao_cv.getCongviecByID(idCV);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel_Title = new JPanel();
		panel_Title.setLayout(null);
		panel_Title.setBackground(new Color(255, 204, 102));
		panel_Title.setBounds(0, 0, 689, 70);
		contentPane.add(panel_Title);

		JLabel lblTitle = new JLabel("CẬP NHẬT CÔNG VIỆC\r\n");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(21, 25, 28));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitle.setBounds(0, 9, 689, 50);
		panel_Title.add(lblTitle);

		JPanel panel_Info = new JPanel();
		panel_Info.setBounds(0, 70, 689, 496);
		panel_Info.setLayout(null);
		panel_Info.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_Info);

		btnCapNhat = new RoundedJButton("Cập nhật");
		btnCapNhat.setBackground(new Color(255, 204, 102));
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCapNhat.setBounds(420, 436, 150, 50);
		panel_Info.add(btnCapNhat);

		JLabel lblTenCV = new JLabel("Tên công việc :");
		lblTenCV.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblTenCV.setBounds(20, 30, 178, 30);
		panel_Info.add(lblTenCV);

		txtTenCV = new JTextField();
		txtTenCV.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtTenCV.setColumns(10);
		txtTenCV.setBounds(198, 30, 460, 30);
		txtTenCV.setText(cv.getTenCongViec());
		panel_Info.add(txtTenCV);

		JLabel lblNgayBatdau = new JLabel("Ngày bắt đầu:");
		lblNgayBatdau.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNgayBatdau.setBounds(20, 70, 178, 30);
		panel_Info.add(lblNgayBatdau);

		dcNgayBatdau = new JDateChooser();
		dcNgayBatdau.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dcNgayBatdau.setBounds(198, 70, 460, 30);
		dcNgayBatdau.setDate(cv.getNgayBatDauCV());
		dcNgayBatdau.getJCalendar().setPreferredSize(new Dimension(400, 400));
		dcNgayBatdau.getJCalendar().getMonthChooser().setPreferredSize(new Dimension(150, 30));
		panel_Info.add(dcNgayBatdau);

		JLabel lblChuyenmon = new JLabel("Chuyên môn:");
		lblChuyenmon.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblChuyenmon.setBounds(20, 108, 178, 30);
		panel_Info.add(lblChuyenmon);

		Set<String> chuyenmons = new HashSet<String>();
		try {
			chuyenmons = dao_cm.getChuyenmons();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		cbChuyenmon = new JComboBox<String>();
		cbChuyenmon.setFont(new Font("Tahoma", Font.PLAIN, 24));
		for (String string : chuyenmons) {
			cbChuyenmon.addItem(string);
		}
		cbChuyenmon.setSelectedItem(cv.getChuyenmon());
		cbChuyenmon.setBounds(198, 108, 460, 30);
		panel_Info.add(cbChuyenmon);

		btnDong = new RoundedJButton("Đóng");
		btnDong.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDong.setBackground(new Color(255, 204, 102));
		btnDong.setBounds(579, 436, 100, 50);
		panel_Info.add(btnDong);

		JLabel lblCongtrinh = new JLabel("Công Trình:");
		lblCongtrinh.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblCongtrinh.setBounds(20, 149, 178, 30);
		panel_Info.add(lblCongtrinh);

		try {
			ct = daoCT.getDsCongTrinh();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		cbCongtrinh = new JComboBox<String>();
		cbCongtrinh.setFont(new Font("Tahoma", Font.PLAIN, 24));
		cbCongtrinh.setBounds(198, 149, 460, 30);
		for (CongTrinh congTrinh : ct) {
			cbCongtrinh.addItem(congTrinh.getMaCT());
		}
		cbCongtrinh.setSelectedItem(cv.getCongtrinh().getMaCT());
		panel_Info.add(cbCongtrinh);

		JLabel lblMota = new JLabel("Mô tả:");
		lblMota.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblMota.setBounds(20, 190, 178, 30);
		panel_Info.add(lblMota);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(198, 190, 460, 190);
		panel_Info.add(scrollPane);

		taMota = new JTextArea();
		taMota.setFont(new Font("Monospaced", Font.PLAIN, 24));
		scrollPane.setViewportView(taMota);

		contentPane.setLayout(null);

		cbChuyenmon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbCongtrinh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCapNhat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		txtError = new JTextField();
		txtError.setForeground(Color.RED);
		txtError.setBackground(Color.WHITE);
		txtError.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtError.setEditable(false);
		txtError.setBorder(BorderFactory.createEmptyBorder());
		txtError.setHorizontalAlignment(SwingConstants.CENTER);
		txtError.setBounds(20, 390, 638, 30);
		panel_Info.add(txtError);
		txtError.setColumns(10);
		
		// Công việc sau khi update
		cvMoi = new CongViec(idCV, null, null, null, null, null, null, null, null);
		// đăng ký sự kiện
		btnCapNhat.addActionListener(this);
		btnDong.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		DAO_CongTrinh daoCT = new DAO_CongTrinh();
		if (o.equals(btnDong)) {
			this.dispose();
		}
		if (o.equals(btnCapNhat)) {
			if (validData()) {
				int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn xác nhận muốn thay đổi thông tin công việc này?",
						"Chú ý", JOptionPane.YES_NO_OPTION);
				if (xacnhan == JOptionPane.YES_OPTION) {
					DAO_CongViec daoCV = new DAO_CongViec();
					String ten = txtTenCV.getText().trim();
					Date ngayBD = new Date(dcNgayBatdau.getDate().getTime());
					String cm = (String) cbChuyenmon.getSelectedItem();
					String idCT = (String) cbCongtrinh.getSelectedItem();
					String mota = taMota.getText();
					try {
						congTrinh = daoCT.getCongTrinhByID(idCT);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					cvMoi.setTenCongViec(ten);
					cvMoi.setNgayBatDauCV(ngayBD);
					cvMoi.setCongtrinh(congTrinh);
					cvMoi.setChuyenmon(cm);
					cvMoi.setMota(mota);
					if (daoCV.updateCV(cvMoi)) {
						JOptionPane.showMessageDialog(this, "Cập nhật thành công");
						this.dispose();
					} else {
						JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
					}
				}
			}
		}

	}

	public boolean validData() {
		String ten = txtTenCV.getText().trim();
		if (!(ten.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Tên công việc không được rỗng");
			txtTenCV.requestFocus();
			return false;
		}
		if (dcNgayBatdau.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được rỗng");
			dcNgayBatdau.requestFocus();
			return false;
		}

		return true;
	}
}
