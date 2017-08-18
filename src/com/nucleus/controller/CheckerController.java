package com.nucleus.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nucleus.model.MakerCheckerModel;

@WebServlet("/CheckerController")
public class CheckerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CheckerController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MakerCheckerModel model = new MakerCheckerModel();
		request.setAttribute("list", model.retrieveAllMaster());
		request.getRequestDispatcher("/MasterTable.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerCode = request.getParameter("recordRow");
		String username = (String) request.getSession(false).getAttribute("username");
		MakerCheckerModel model = new MakerCheckerModel();
		if(request.getParameter("operation")==null) {
			try {
				request.setAttribute("list", model.retrieveAllChecker());
				request.setAttribute("check", "nil");
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/CheckerView.jsp");
				rs.forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		} else {
			if(request.getParameter("operation").equals("Authorize")) {
				if(model.authorize(customerCode, username)) {
					try {
						request.setAttribute("list", model.retrieveAllChecker());
						request.setAttribute("check", "authcorrect");
						RequestDispatcher rs = getServletContext().getRequestDispatcher("/CheckerView.jsp");
						rs.forward(request, response);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ServletException e) {
						e.printStackTrace();
					}
				} else {
					try {
						request.setAttribute("list", model.retrieveAllChecker());
						request.setAttribute("check", "authfalse");
						RequestDispatcher rs = getServletContext().getRequestDispatcher("/CheckerView.jsp");
						rs.forward(request, response);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ServletException e) {
						e.printStackTrace();
					}
				}
			} else if(request.getParameter("operation").equals("Reject")) {
				if(model.reject(customerCode)) {
					try {
						request.setAttribute("list", model.retrieveAllChecker());
						request.setAttribute("check", "rejectcorrect");
						RequestDispatcher rs = getServletContext().getRequestDispatcher("/CheckerView.jsp");
						rs.forward(request, response);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ServletException e) {
						e.printStackTrace();
					}
				} else {
					try {
						request.setAttribute("list", model.retrieveAllChecker());
						request.setAttribute("check", "rejectfalse");
						RequestDispatcher rs = getServletContext().getRequestDispatcher("/CheckerView.jsp");
						rs.forward(request, response);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ServletException e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("Something went wrong!!");
			}
		}
	}
}
