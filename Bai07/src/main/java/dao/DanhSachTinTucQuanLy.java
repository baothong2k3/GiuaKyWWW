package dao;

import java.util.List;

import entities.TinTuc;

public interface DanhSachTinTucQuanLy {
	public List<TinTuc> getTinTucByMaDanhMuc(String maDanhMuc);

	public TinTuc getTinTucByMa(String maTinTuc);

	public List<TinTuc> getAllTinTuc();

	public boolean addTinTuc(TinTuc tinTuc);

	public boolean deleteTinTuc(String maTT);
}
