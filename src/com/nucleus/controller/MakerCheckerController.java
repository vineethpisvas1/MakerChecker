package com.nucleus.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.nucleus.model.MakerCheckerModel;

@WebServlet("/MakerCheckerController")
public class MakerCheckerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MakerCheckerController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		MakerCheckerModel model = new MakerCheckerModel();
		if(model.login(username, password)!=null) {
			if(model.login(username, password).equals("maker")) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				try {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/MakerView.jsp");
					rs.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				request.setAttribute("list", model.retrieveAllChecker());
				RequestDispatcher rs = request.getRequestDispatcher("/CheckerView.jsp");
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("list", model.retrieveAllChecker());
				try {
					rs.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			request.setAttribute("login", "failed");
			RequestDispatcher rs = request.getRequestDispatcher("/index.jsp");
			rs.forward(request, response);
		}
	}
}
