package entities;

public class DeTai {
	private String maDeTai;
    private String tenDeTai;
    private int namDangky;
    private String moTa;
    private GiangVien giangVien;
	public String getMaDeTai() {
		return maDeTai;
	}
	public void setMaDeTai(String maDeTai) {
		this.maDeTai = maDeTai;
	}
	public String getTenDeTai() {
		return tenDeTai;
	}
	public void setTenDeTai(String tenDeTai) {
		this.tenDeTai = tenDeTai;
	}
	public int getNamDangky() {
		return namDangky;
	}
	public void setNamDangky(int namDangky) {
		this.namDangky = namDangky;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public GiangVien getGiangVien() {
		return giangVien;
	}
	public void setGiangVien(GiangVien giangVien) {
		this.giangVien = giangVien;
	}
	public DeTai(String maDeTai, String tenDeTai, int namDangky, String moTa, GiangVien giangVien) {
		super();
		this.maDeTai = maDeTai;
		this.tenDeTai = tenDeTai;
		this.namDangky = namDangky;
		this.moTa = moTa;
		this.giangVien = giangVien;
	}
	public DeTai() {
		super();
	}
	@Override
	public String toString() {
		return "DeTai [maDeTai=" + maDeTai + ", tenDeTai=" + tenDeTai + ", namDangky=" + namDangky + ", moTa=" + moTa
				+ ", giangVien=" + giangVien + "]";
	}
    
}
