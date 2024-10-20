package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.DeTaiDAO;
import entities.DeTai;

public class DeTaiDAOImpl implements DeTaiDAO {
	private DataSource dataSource;

	public DeTaiDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<DeTai> getAllDeTai() {
		String sql = "SELECT * FROM DeTai";
		List<DeTai> listDeTai = new ArrayList<DeTai>();
		try (Connection conn = this.dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				DeTai deTai = new DeTai();
				deTai.setMaDeTai(rs.getString("MADT"));
				deTai.setTenDeTai(rs.getString("TENDT"));
				deTai.setNamDangky(rs.getInt("NAMDANGKY"));
				deTai.setMoTa(rs.getString("MOTADETAI"));
				deTai.setGiangVien(new GiangVienDAOImpl(dataSource).getGiangVienByMaGV(rs.getString("MAGV")));
				listDeTai.add(deTai);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDeTai;
	}

	@Override
	public DeTai getDeTaiByMaDeTai(String maDeTai) {
		String sql = "SELECT * FROM DeTai WHERE MADT = ?";
		DeTai deTai = null;
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, maDeTai);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					deTai = new DeTai();
					deTai.setMaDeTai(rs.getString("MADT"));
					deTai.setTenDeTai(rs.getString("TENDT"));
					deTai.setNamDangky(rs.getInt("NAMDANGKY"));
					deTai.setMoTa(rs.getString("MOTADETAI"));
					deTai.setGiangVien(new GiangVienDAOImpl(dataSource).getGiangVienByMaGV(rs.getString("MAGV")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deTai;
	}

	@Override
	public boolean addDeTai(DeTai deTai) {
		String sql = "INSERT INTO DeTai VALUES(?,?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, deTai.getMaDeTai());
			ps.setString(2, deTai.getTenDeTai());
			ps.setInt(3, deTai.getNamDangky());
			ps.setString(4, deTai.getMoTa());
			ps.setString(5, deTai.getGiangVien().getMaGV());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<DeTai> getDeTaiByGiangVien(String maGiangVien) {
		String sql = "SELECT * FROM DeTai WHERE MAGV = ?";
		List<DeTai> listDeTai = new ArrayList<DeTai>();
		try (Connection conn = this.dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, maGiangVien);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					DeTai deTai = new DeTai();
					deTai.setMaDeTai(rs.getString("MADT"));
					deTai.setTenDeTai(rs.getString("TENDT"));
					deTai.setNamDangky(rs.getInt("NAMDANGKY"));
					deTai.setMoTa(rs.getString("MOTADETAI"));
					deTai.setGiangVien(new GiangVienDAOImpl(dataSource).getGiangVienByMaGV(rs.getString("MAGV")));
					listDeTai.add(deTai);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDeTai;
	}

}
