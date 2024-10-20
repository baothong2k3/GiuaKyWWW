package dao;
import java.util.List;
import entities.GiangVien;
public interface GiangVienDAO {
	public List<GiangVien> getAllGiangVien();
	public GiangVien getGiangVienByMaGV(String maGV);
	public boolean addGiangVien(GiangVien giangVien);
}
