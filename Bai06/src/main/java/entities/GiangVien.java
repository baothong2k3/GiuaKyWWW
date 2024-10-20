package entities;

public class GiangVien {
	private String maGV;
    private String tenGV;
    private String linhVucNghienCuu;
    private String soDienThoai;
	public String getMaGV() {
		return maGV;
	}
	public void setMaGV(String maGV) {
		this.maGV = maGV;
	}
	public String getTenGV() {
		return tenGV;
	}
	public void setTenGV(String tenGV) {
		this.tenGV = tenGV;
	}
	public String getLinhVucNghienCuu() {
		return linhVucNghienCuu;
	}
	public void setLinhVucNghienCuu(String linhVucNghienCuu) {
		this.linhVucNghienCuu = linhVucNghienCuu;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public GiangVien(String maGV, String tenGV, String linhVucNghienCuu, String soDienThoai) {
		super();
		this.maGV = maGV;
		this.tenGV = tenGV;
		this.linhVucNghienCuu = linhVucNghienCuu;
		this.soDienThoai = soDienThoai;
	}
	public GiangVien() {
		super();
	}
	@Override
	public String toString() {
		return "GiangVien [maGV=" + maGV + ", tenGV=" + tenGV + ", linhVucNghienCuu=" + linhVucNghienCuu
				+ ", soDienThoai=" + soDienThoai + "]";
	}
}
