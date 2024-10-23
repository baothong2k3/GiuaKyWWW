package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.DanhSachTinTucQuanLy;
import entities.DanhMuc;
import entities.TinTuc;

public class DanhSachTinTucQuanLyImpl implements DanhSachTinTucQuanLy {

	private DataSource dataSource;

	public DanhSachTinTucQuanLyImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<TinTuc> getTinTucByMaDanhMuc(String maDanhMuc) {
		String query = "SELECT * FROM TINTUC WHERE MADM = ?";
		List<TinTuc> listTinTuc = new ArrayList<TinTuc>();
		try (Connection conn = this.dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
			ps.setString(1, maDanhMuc);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TinTuc tinTuc = new TinTuc();
				tinTuc.setMaTinTuc(rs.getString("MATT"));
				tinTuc.setTieuDe(rs.getString("TIEUDE"));
				tinTuc.setNoiDung(rs.getString("NOIDUNGTT"));
				tinTuc.setLienKet(rs.getString("LIENKET"));
				DanhMuc danhMuc = new DanhMucDAOImpl(dataSource).getDanhMucByMaDanhMuc(rs.getString("MADM"));
				tinTuc.setDanhMuc(danhMuc);
				listTinTuc.add(tinTuc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listTinTuc;
	}

	@Override
	public boolean addTinTuc(TinTuc tinTuc) {
		String sql = "INSERT INTO TINTUC(MATT, TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES(?, ?, ?, ?, ?)";
		try (Connection conn = this.dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, tinTuc.getMaTinTuc());
			ps.setString(2, tinTuc.getTieuDe());
			ps.setString(3, tinTuc.getNoiDung());
			ps.setString(4, tinTuc.getLienKet());
			ps.setString(5, tinTuc.getDanhMuc().getMaDanhMuc());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteTinTuc(String maTT) {
		String sql = "DELETE FROM TINTUC WHERE MATT = ?";
		try (Connection conn = this.dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, maTT);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public TinTuc getTinTucByMa(String maTinTuc) {
		String query = "SELECT * FROM TINTUC WHERE MATT = ?";
		TinTuc tinTuc = null;
		try (Connection conn = this.dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
			ps.setString(1, maTinTuc);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tinTuc = new TinTuc();
				tinTuc.setMaTinTuc(rs.getString("MATT"));
				tinTuc.setTieuDe(rs.getString("TIEUDE"));
				tinTuc.setNoiDung(rs.getString("NOIDUNGTT"));
				tinTuc.setLienKet(rs.getString("LIENKET"));
				DanhMuc danhMuc = new DanhMucDAOImpl(dataSource).getDanhMucByMaDanhMuc(rs.getString("MADM"));
				tinTuc.setDanhMuc(danhMuc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tinTuc;
	}

	@Override
	public List<TinTuc> getAllTinTuc() {
		String query = "SELECT * FROM TINTUC";
		List<TinTuc> listTinTuc = new ArrayList<TinTuc>();
		try (Connection conn = this.dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TinTuc tinTuc = new TinTuc();
				tinTuc.setMaTinTuc(rs.getString("MATT"));
				tinTuc.setTieuDe(rs.getString("TIEUDE"));
				tinTuc.setNoiDung(rs.getString("NOIDUNGTT"));
				tinTuc.setLienKet(rs.getString("LIENKET"));
				DanhMuc danhMuc = new DanhMucDAOImpl(dataSource).getDanhMucByMaDanhMuc(rs.getString("MADM"));
				tinTuc.setDanhMuc(danhMuc);
				listTinTuc.add(tinTuc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listTinTuc;
	}

}
