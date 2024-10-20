package dao;

import java.util.List;

import entities.DeTai;

public interface DeTaiDAO {
	public List<DeTai> getAllDeTai();
	public DeTai getDeTaiByMaDeTai(String maDeTai);
	public boolean addDeTai(DeTai deTai);
	public List<DeTai> getDeTaiByGiangVien(String maGiangVien);
}
