package entity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import dao.DAO_CongViec;

public class CongViec {
	private String maCongViec;
	private String tenCongViec;
	private Date ngayBatDauCV;
	private Date ngayKetThucCV;
	private String chuyenmon;
	private String trangthai;
	private CongTrinh congtrinh;
	private NhanVien nhanvien;
	private String mota;

	

	public CongViec(String maCongViec, String tenCongViec, Date ngayBatDauCV, Date ngayKetThucCV, String chuyenmon,
			String trangthai, String mota, CongTrinh congtrinh, NhanVien nhanvien) {
		super();
		this.maCongViec = maCongViec;
		this.tenCongViec = tenCongViec;
		this.ngayBatDauCV = ngayBatDauCV;
		this.ngayKetThucCV = ngayKetThucCV;
		this.chuyenmon = chuyenmon;
		this.trangthai = trangthai;
		this.mota = mota;
		this.congtrinh = congtrinh;
		this.nhanvien = nhanvien;
	}

	public CongViec(String tenCongViec, Date ngayBatDauCV, String chuyenmon, String mota, CongTrinh congtrinh, NhanVien nhanvien) {
		super();
		try {
			setMaCongViec();
			this.tenCongViec = tenCongViec;
			this.ngayBatDauCV = ngayBatDauCV;
			this.ngayKetThucCV = null;
			this.chuyenmon = chuyenmon;
			this.trangthai = "Chưa hoàn thành";
			this.mota = mota;
			this.congtrinh = congtrinh;
			this.nhanvien = nhanvien;
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public CongViec() {
		super();
	}

	public String getMaCongViec() {
		return maCongViec;
	}

	public void setMaCongViec() throws SQLException {
		DAO_CongViec dao_CongViec = new DAO_CongViec();
		int count = dao_CongViec.getDsCongviec().size();
		AtomicInteger ID_GENERATOR = new AtomicInteger(count);
		int ma = ID_GENERATOR.incrementAndGet();
		String convert = String.format("%6d", ma).replaceAll(" ", "0");
		while(dao_CongViec.getCongviecByID("CV"+convert) != null) {
			ma++;
			convert = String.format("%6d", ma).replaceAll(" ", "0");
		}
		convert = String.format("%6d", ma).replaceAll(" ", "0");
		this.maCongViec = "CV" + convert;
	}

	public String getTenCongViec() {
		return tenCongViec;
	}

	public void setTenCongViec(String tenCongViec) {
		this.tenCongViec = tenCongViec;
	}

	public Date getNgayBatDauCV() {
		return ngayBatDauCV;
	}

	public void setNgayBatDauCV(Date ngayBatDauCV) {
		this.ngayBatDauCV = ngayBatDauCV;
	}

	public Date getNgayKetThucCV() {
		return ngayKetThucCV;
	}

	public void setNgayKetThucCV(Date ngayKetThucCV) {
		this.ngayKetThucCV = ngayKetThucCV;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public CongTrinh getCongtrinh() {
		return congtrinh;
	}

	public void setCongtrinh(CongTrinh congtrinh) {
		this.congtrinh = congtrinh;
	}

	public NhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}
	
	public String getChuyenmon() {
		return chuyenmon;
	}

	public void setChuyenmon(String chuyenmon) {
		this.chuyenmon = chuyenmon;
	}
	
	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	@Override
	public String toString() {
		return "CongViec [maCongViec=" + maCongViec + ", tenCongViec=" + tenCongViec + ", ngayBatDauCV=" + ngayBatDauCV
				+ ", ngayKetThucCV=" + ngayKetThucCV + ", trangthai=" + trangthai + ", congtrinh=" + congtrinh
				+ ", nhanvien=" + nhanvien + "]";
	}

}
