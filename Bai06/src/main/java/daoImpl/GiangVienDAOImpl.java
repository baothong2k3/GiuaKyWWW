package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.GiangVienDAO;
import entities.GiangVien;

public class GiangVienDAOImpl implements GiangVienDAO {

	private DataSource dataSource;

	public GiangVienDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<GiangVien> getAllGiangVien() {
		String sql = "SELECT * FROM GiangVien";
		List<GiangVien> listGiangVien = new ArrayList<GiangVien>();
		try (Connection conn = this.dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				GiangVien giangVien = new GiangVien();
				giangVien.setMaGV(rs.getString("maGV"));
				giangVien.setTenGV(rs.getString("tenGV"));
				giangVien.setLinhVucNghienCuu(rs.getString("linhVucNghienCuu"));
				giangVien.setSoDienThoai(rs.getString("soDienThoai"));
				listGiangVien.add(giangVien);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listGiangVien;
	}

	@Override
	public GiangVien getGiangVienByMaGV(String maGV1) {
		String sql = "SELECT * FROM GiangVien WHERE MAGV = ?";
		GiangVien giangVien = null;
		try (Connection conn = this.dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, maGV1);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					giangVien = new GiangVien();
					giangVien.setMaGV(rs.getString("maGV"));
					giangVien.setTenGV(rs.getString("tenGV"));
					giangVien.setLinhVucNghienCuu(rs.getString("linhVucNghienCuu"));
					giangVien.setSoDienThoai(rs.getString("soDienThoai"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return giangVien;
	}

	@Override
	public boolean addGiangVien(GiangVien giangVien) {
		String sql = "INSERT INTO GiangVien VALUES(?, ?, ?, ?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, giangVien.getMaGV());
			ps.setString(2, giangVien.getTenGV());
			ps.setString(3, giangVien.getLinhVucNghienCuu());
			ps.setString(4, giangVien.getSoDienThoai());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
