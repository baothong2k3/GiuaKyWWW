package dao;

import java.util.List;

import entities.TinTuc;

public interface DanhSachTinTucQuanLy {
	public List<TinTuc> getTinTucByMaDanhMuc(String maDanhMuc);

	public boolean addTinTuc(TinTuc tinTuc);

	public boolean deleteTinTuc(String maTT);
}
