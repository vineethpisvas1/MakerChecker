package com.nucleus.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nucleus.datalayer.CustomerInfo;
import com.nucleus.model.MakerCheckerModel;

@WebServlet("/CRUDController")
public class CRUDController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public CRUDController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String crudOperation = request.getParameter("CRUD");
		MakerCheckerModel model = new MakerCheckerModel();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		if(crudOperation.equals("Create")) {
			CustomerInfo customer = new CustomerInfo();
			customer.setCustomerCode(request.getParameter("customerCode"));
			customer.setCustomerName(request.getParameter("customerName"));
			customer.setAddress1(request.getParameter("address1"));
			customer.setAddress2(request.getParameter("address2"));
			customer.setEmail(request.getParameter("email"));
			customer.setPincode(Integer.parseInt(request.getParameter("pincode")));
			customer.setContactNumber(Long.parseLong(request.getParameter("contactNumber")));
			customer.setPrimaryContactPerson(request.getParameter("primaryContactPerson"));
			customer.setRecordStatus("N");
			customer.setFlag(request.getParameter("optradio2"));
			customer.setCreateDate((String)dateFormat.format(date));
			customer.setCreatedBy(request.getParameter("createdBy"));
			customer.setModifiedDate(request.getParameter("modifiedDate"));
			customer.setModifiedBy(request.getParameter("modifiedBy"));
			customer.setAuthorizedDate(request.getParameter("authorizedDate"));
			customer.setAuthorizedBy(request.getParameter("authorizedBy"));
			model.create(customer);
			try {
				request.setAttribute("check", "right");
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/Redirect.jsp");
				rs.forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		} else if(crudOperation.equals("Retrieve")) {
			List<CustomerInfo> records = new ArrayList<CustomerInfo> ();
			if(request.getParameter("optradio1").equals("Specific Record")) {
				String customerCode = request.getParameter("customerCode");
				records = model.retrieveSpecificRecord(customerCode);
			} else {
				records = model.retrieveAllMaker();
			}
			if(records==null) {
				request.setAttribute("check", "wrong");
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/WrongRedirect.jsp");
				rs.forward(request, response);
			} else {
				request.setAttribute("view", request.getParameter("optradio1"));
				request.setAttribute("list", records);
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/RecordsView.jsp");
				rs.forward(request, response);
			}
		} else if(crudOperation.equals("Update")) {
			int updateCount = 0;
			String customerCode = request.getParameter("customerCode");
			String[] updates = new String[11];
			String[] fields = {"customerName", "address1", "address2", "email", "pincode", "contactNumber", "primaryContactPerson", "flag", "recordStatus", "modifiedDate", "modifiedBy"};
			boolean[] updateFlag = new boolean[11];
			updateFlag[0] = request.getParameter("customerNameBox")!=null && request.getParameter("customerNameBox").equals("on");
			updateFlag[1] = request.getParameter("address1Box")!=null && request.getParameter("address1Box").equals("on");
			updateFlag[2] = request.getParameter("address2Box")!=null && request.getParameter("address2Box").equals("on");
			updateFlag[3] = request.getParameter("emailBox")!=null && request.getParameter("emailBox").equals("on");
			updateFlag[4] = request.getParameter("pincodeBox")!=null && request.getParameter("pincodeBox").equals("on");
			updateFlag[5] = request.getParameter("contactNumberBox")!=null && request.getParameter("contactNumberBox").equals("on");
			updateFlag[6] = request.getParameter("primaryContactPersonBox")!=null && request.getParameter("primaryContactPersonBox").equals("on");
			updateFlag[7] = request.getParameter("flagBox")!=null && request.getParameter("flagBox").equals("on");
			updateFlag[8] = true;
			updateFlag[9] = true;
			updateFlag[10] = true;
			for(int i=0; i<8; i++) {
				if(updateFlag[i]) {
					updates[i] = request.getParameter(fields[i]);
					updateCount++;
				} else {
					updates[i] = "Nil";
				}
			}
			if(updateCount==0) {
				System.out.println("Nothing to do -_-");
				try {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/MakerView.jsp");
					rs.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				updates[8] = "M";
				updates[9] = (String)dateFormat.format(date);
				updates[10] = username;
				updateCount = updateCount+3;
				if(model.update(customerCode, updates, updateCount)) {
					request.setAttribute("check", "right");
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/Redirect.jsp");
					rs.forward(request, response);
				} else {
					request.setAttribute("check", "wrong");
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/WrongRedirect.jsp");
					rs.forward(request, response);
				}
			}
		} else if(crudOperation.equals("Delete")) {
			String customerCode = request.getParameter("customerCode");
			if(model.delete(customerCode)) {
				request.setAttribute("check", "right");
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/Redirect.jsp");
				rs.forward(request, response);
			} else {
				request.setAttribute("check", "wrong");
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/WrongRedirect.jsp");
				rs.forward(request, response);
			}
		}
	}
}
