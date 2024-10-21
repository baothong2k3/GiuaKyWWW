package dao;

import java.util.List;

import entities.DanhMuc;

public interface DanhMucDAO {
	public DanhMuc getDanhMucByMaDanhMuc(String maDanhMuc);

	public List<DanhMuc> getAllDanhMuc();
}
