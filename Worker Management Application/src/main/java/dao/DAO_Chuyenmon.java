package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import connect.ConnectDB;

public class DAO_Chuyenmon {
	
	/**lấy danh sách các chuyên môn
	 * 
	 * @return set<String>
	 * @throws SQLException
	 */
	public Set<String> getChuyenmons() throws SQLException {
		Set<String> chuyenmons = new HashSet<String>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getCon();
		String sql = "select * from ChuyenMon";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String cm = rs.getString("ChuyenMon");
			chuyenmons.add(cm);
		}
		return chuyenmons;
	}
}
