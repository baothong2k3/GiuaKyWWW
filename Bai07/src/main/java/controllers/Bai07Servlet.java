package controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import dao.DanhMucDAO;
import dao.DanhSachTinTucQuanLy;
import daoImpl.DanhMucDAOImpl;
import daoImpl.DanhSachTinTucQuanLyImpl;
import entities.DanhMuc;
import entities.TinTuc;

public class Bai07Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/QUANLYDANHMUC")
	private DataSource dataSource;
	private DanhMucDAO danhMucDAO;
	private DanhSachTinTucQuanLy tinTucQuanLy;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		danhMucDAO = new DanhMucDAOImpl(dataSource);
		tinTucQuanLy = new DanhSachTinTucQuanLyImpl(dataSource);
	}

	public Bai07Servlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		switch (action) {
		case "list":
			request.getRequestDispatcher("/views/DanhSach.jsp").forward(request, response);
			break;
		case "add":
			request.getRequestDispatcher("/views/ThemMoi.jsp").forward(request, response);
			break;
		case "addTT":
			themTinTuc(request, response);
			break;
		case "manage":
			manage(request, response);
			break;
		case "deleteTT":
			xoaTinTuc(request, response);
			break;
		case "search":
			List<TinTuc> tinTucs = tinTucQuanLy.getAllTinTuc();
			request.setAttribute("tinTucs", tinTucs);
			request.setAttribute("found", false);
			request.getRequestDispatcher("/views/TimKiem.jsp").forward(request, response);
			break;
		case "searchTT":
			timTinTuc(request, response);
			break;
		default:
			showList(request, response);
			break;
		}
	}

	private void timTinTuc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<TinTuc> tinTucs = tinTucQuanLy.getAllTinTuc();
		String maTT = request.getParameter("maTT");
		TinTuc tinTuc = null;
		boolean found = false;

		if (maTT != null && !maTT.trim().isEmpty()) {
			tinTuc = tinTucQuanLy.getTinTucByMa(maTT);
			if (tinTuc != null) {
				found = true; // Có tìm thấy tin tức
				request.setAttribute("tinTuc", tinTuc);
				request.setAttribute("found", found);
			}
		} else {
			request.setAttribute("tinTucs", tinTucs);
			request.setAttribute("found", found);
		}

		request.getRequestDispatcher("/views/TimKiem.jsp").forward(request, response);
	}

	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DanhMuc> danhMucs = danhMucDAO.getAllDanhMuc();
		Map<String, List<TinTuc>> tinTucs = new HashMap<String, List<TinTuc>>();
		for (DanhMuc danhMuc : danhMucs) {
			List<TinTuc> tinTuc = tinTucQuanLy.getTinTucByMaDanhMuc(danhMuc.getMaDanhMuc());
			tinTucs.put(danhMuc.getMaDanhMuc(), tinTuc);
		}
		request.setAttribute("danhMucs", danhMucs);
		request.setAttribute("tinTucs", tinTucs);
		request.getRequestDispatcher("/views/QuanLy.jsp").forward(request, response);

	}

	private void xoaTinTuc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maTT = request.getParameter("maTT");
		if (tinTucQuanLy.deleteTinTuc(maTT)) {
			request.setAttribute("success", "Xóa thành công");
		} else {
			request.setAttribute("error", "Xóa thất bại");
		}
		manage(request, response);
	}

	private void themTinTuc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maTT = request.getParameter("maTT");
		String tieuDe = request.getParameter("tieuDe");
		String noiDung = request.getParameter("noiDung");
		String lienKet = request.getParameter("lienKet");
		String maDanhMuc = request.getParameter("maDM");
		DanhMuc danhMuc = danhMucDAO.getDanhMucByMaDanhMuc(maDanhMuc);
		if (danhMuc == null) {
			request.setAttribute("error", "Danh mục không tồn tại");
			request.getRequestDispatcher("/views/ThemMoi.jsp").forward(request, response);
			return;
		}
		TinTuc tinTuc = new TinTuc(maTT, tieuDe, noiDung, lienKet, danhMuc);
		tinTucQuanLy.addTinTuc(tinTuc);
		request.setAttribute("success", "Thêm mới thành công");
		request.getRequestDispatcher("/views/ThemMoi.jsp").forward(request, response);
	}

	private void showList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<DanhMuc> danhMucs = danhMucDAO.getAllDanhMuc();
		Map<String, List<TinTuc>> tinTucs = new HashMap<String, List<TinTuc>>();
		for (DanhMuc danhMuc : danhMucs) {
			List<TinTuc> tinTuc = tinTucQuanLy.getTinTucByMaDanhMuc(danhMuc.getMaDanhMuc());
			tinTucs.put(danhMuc.getMaDanhMuc(), tinTuc);
		}
		request.setAttribute("danhMucs", danhMucs);
		request.setAttribute("tinTucs", tinTucs);
		request.getRequestDispatcher("/views/DanhSach.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
