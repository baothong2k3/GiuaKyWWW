package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.DanhMucDAO;
import entities.DanhMuc;

public class DanhMucDAOImpl implements DanhMucDAO {
	private DataSource dataSource;

	public DanhMucDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public DanhMuc getDanhMucByMaDanhMuc(String maDanhMuc) {
		String sql = "SELECT * FROM DANHMUC WHERE MADM = ?";
		DanhMuc danhMuc = null;
		try (Connection conn = this.dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, maDanhMuc);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				danhMuc = new DanhMuc();
				danhMuc.setMaDanhMuc(rs.getString("MADM"));
				danhMuc.setTenDanhMuc(rs.getString("TENDANHMUC"));
				danhMuc.setNguoiQuanLy(rs.getString("NGUOIQUANLY"));
				danhMuc.setGhiChu(rs.getString("GHICHU"));
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return danhMuc;
	}

	@Override
	public List<DanhMuc> getAllDanhMuc() {
		String sql = "Select * from DANHMUC";
		List<DanhMuc> danhMucs = new ArrayList<DanhMuc>();
		try (Connection conn = this.dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDanhMuc(rs.getString("MADM"));
				danhMuc.setTenDanhMuc(rs.getString("TENDANHMUC"));
				danhMuc.setNguoiQuanLy(rs.getString("NGUOIQUANLY"));
				danhMuc.setGhiChu(rs.getString("GHICHU"));
				danhMucs.add(danhMuc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return danhMucs;
	}

}
