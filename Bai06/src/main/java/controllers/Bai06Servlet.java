package controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import dao.DeTaiDAO;
import dao.GiangVienDAO;
import daoImpl.DeTaiDAOImpl;
import daoImpl.GiangVienDAOImpl;
import entities.DeTai;
import entities.GiangVien;

public class Bai06Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/QuanLyGiangVienDeTai")
	private DataSource dataSource;
	private DeTaiDAO deTaiDAO;
	private GiangVienDAO giangVienDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			deTaiDAO = new DeTaiDAOImpl(dataSource);
			giangVienDAO = new GiangVienDAOImpl(dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public Bai06Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		switch (action) {
		case "themGV":
			request.getRequestDispatcher("/views/ThemGiangVien.jsp").forward(request, response);
			break;
		case "addGV":
			themGV(request, response);
			break;
		case "themDeTai":
			request.getRequestDispatcher("/views/ThemDeTai.jsp").forward(request, response);
			break;
		case "addDT":
			themDeTai(request, response);
			break;
		default:
			show(request, response);
			break;
		}
	}

	private void themDeTai(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maDeTai = request.getParameter("maDeTai");
		String tenDeTai = request.getParameter("tenDeTai");
		int namDangKy = Integer.parseInt(request.getParameter("namDK"));
		String moTa = request.getParameter("moTa");
		String maGV = request.getParameter("maGV");
		GiangVien giangVien = giangVienDAO.getGiangVienByMaGV(maGV);
		DeTai deTai = new DeTai(maDeTai, tenDeTai, namDangKy, moTa, giangVien);
		DeTai dt = deTaiDAO.getDeTaiByMaDeTai(maDeTai);

		List<DeTai> dsDeTai = deTaiDAO.getDeTaiByGiangVien(maGV);
		for (DeTai deTai2 : dsDeTai) {
			if (deTai2.getMaDeTai().equals(maDeTai)) {
				request.setAttribute("error", "Giảng viên đã đăng ký đề tài này");
				request.getRequestDispatcher("/views/ThemDeTai.jsp").forward(request, response);
				return;
			}
		}
		if (dt == null && deTaiDAO.addDeTai(deTai)) {
			request.setAttribute("success", "Thêm đề tài thành công");
			System.out.println("Thêm đề tài thành công");
		} else {
			request.setAttribute("error", "Thêm đề tài thất bại");
			System.out.println("Thêm đề tài thất bại");
		}
		request.getRequestDispatcher("/views/ThemDeTai.jsp").forward(request, response);
	}

	private void themGV(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maGV = request.getParameter("maGV");
		String tenGV = request.getParameter("tenGV");
		String linhVuc = request.getParameter("linhVucNghienCuu");
		String soDienThoai = request.getParameter("soDienThoai");
		GiangVien giangVien = new GiangVien(maGV, tenGV, linhVuc, soDienThoai);
		GiangVien gv = giangVienDAO.getGiangVienByMaGV(maGV);

		if (gv == null && giangVienDAO.addGiangVien(giangVien)) {
			request.setAttribute("success", "Thêm giảng viên thành công");
			System.out.println("Thêm giảng viên thành công");
		} else {
			request.setAttribute("error", "Thêm giảng viên thất bại");
			System.out.println("Thêm giảng viên thất bại");
		}
		request.getRequestDispatcher("/views/ThemGiangVien.jsp").forward(request, response);
	}

	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GiangVien> giangViens = giangVienDAO.getAllGiangVien();
		Map<String, List<DeTai>> dsDeTai = new HashMap<String, List<DeTai>>();
		for (GiangVien giangVien : giangViens) {
			dsDeTai.put(giangVien.getMaGV(), deTaiDAO.getDeTaiByGiangVien(giangVien.getMaGV()));
		}
		request.setAttribute("giangViens", giangViens);
		request.setAttribute("dsDeTai", dsDeTai);
		request.getRequestDispatcher("/views/Home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
