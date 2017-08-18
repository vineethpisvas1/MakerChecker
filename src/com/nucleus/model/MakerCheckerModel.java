package com.nucleus.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nucleus.connection.ConnectionControl;
import com.nucleus.datalayer.CustomerInfo;

public class MakerCheckerModel {
	public String login(String username, String password) {
		ConnectionControl conObject = new ConnectionControl();
		Connection link;
		try {
			link = conObject.connectToDatabase();
			PreparedStatement ps = link.prepareStatement("SELECT * FROM MAKERCHECKERUSERS WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void create(CustomerInfo customer) {
		ConnectionControl conObject = new ConnectionControl();
		Connection link;
		try {
			link = conObject.connectToDatabase();
			PreparedStatement ps = link.prepareStatement("INSERT INTO CUSTOMERTEMPORARY values(customertemporary_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, customer.getCustomerCode());
			ps.setString(2, customer.getCustomerName());
			ps.setString(3, customer.getAddress1());
			ps.setString(4, customer.getAddress2());
			ps.setInt(5, customer.getPincode());
			ps.setString(6, customer.getEmail());
			ps.setLong(7, customer.getContactNumber());
			ps.setString(8, customer.getPrimaryContactPerson());
			ps.setString(9, customer.getRecordStatus());
			ps.setString(10, customer.getFlag());
			ps.setString(11, customer.getCreateDate());
			ps.setString(12, customer.getCreatedBy());
			ps.setString(13, customer.getModifiedDate());
			ps.setString(14, customer.getModifiedBy());
			ps.setString(15, customer.getAuthorizedDate());
			ps.setString(16, customer.getAuthorizedBy());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean update(String customerCode, String[] updates, int updateCount) {
		ConnectionControl conObject = new ConnectionControl();
		Connection link;
		String[] fields = {"name", "address1", "address2", "email", "pincode", "contactNumber", "primaryContactPerson", "flag", "recordStatus", "modifiedDate", "modifiedBy"};
		try {
			link = conObject.connectToDatabase();
			PreparedStatement ps1 = link.prepareStatement("SELECT * FROM CUSTOMERTEMPORARY WHERE code=?");
			ps1.setString(1, customerCode);
			ResultSet rs = ps1.executeQuery();
			if(rs.next()) {
				String query = " SET ";
				int c = 0;
				for(int i=0; i<updates.length; i++) {
					if(updates[i].equals("Nil")) {
						
					} else if(c!=(updateCount-1)){
						query = query + fields[i]+"= ?, ";
						c++;
					} else {
						query = query + fields[i]+"= ? ";
					}
				}
				String temp = "UPDATE CUSTOMERTEMPORARY"+query+"WHERE code=?";
				PreparedStatement ps2 = link.prepareStatement(temp);
				int k=1;
				for(int i=0; i<updates.length; i++) {
					if(updates[i].equals("Nil")) {
						
					} else if(fields[i].equals("pincode")){
						ps2.setInt(k, Integer.parseInt(updates[i]));
						k++;
					} else if(fields[i].equals("contactNumber")){
						ps2.setLong(k, Long.parseLong(updates[i]));
						k++;
					} else if(fields[i].equals("recordStatus")){
						if(rs.getString(10).equals("N") || rs.getString(10).equals("NR")) {
							ps2.setString(k, "N");
							k++;
						} else {
							ps2.setString(k, "M");
							k++;
						}
					} else {
						ps2.setString(k, updates[i]);
						k++;
					}
				}
				ps2.setString(k, customerCode);
				ps2.executeUpdate();
				return true;
			} else {
				PreparedStatement ps2 = link.prepareStatement("SELECT * FROM CUSTOMERMASTER WHERE code=?");
				ps2.setString(1, customerCode);
				ResultSet rs2 = ps2.executeQuery();
				if(rs2.next()) {
					for(int i=0; i<updates.length; i++) {
						if(updates[i].equals("Nil")) {
							if(fields[i].equals("contactNumber")) {
								updates[i]=rs2.getString("contact");
							} else {
								updates[i]=rs2.getString(fields[i]);
							}
						}
					}
					PreparedStatement ps = link.prepareStatement("INSERT INTO CUSTOMERTEMPORARY values(customertemporary_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setString(1, rs2.getString(2));
					ps.setString(2, updates[0]);
					ps.setString(3, updates[1]);
					ps.setString(4, updates[2]);
					ps.setInt(5, Integer.parseInt(updates[4]));
					ps.setString(6, updates[3]);
					ps.setLong(7, Long.parseLong(updates[5]));
					ps.setString(8, updates[6]);
					ps.setString(9, updates[8]);
					ps.setString(10, updates[7]);
					ps.setString(11, rs2.getString(12));
					ps.setString(12, rs2.getString(13));
					ps.setString(13, updates[9]);
					ps.setString(14, updates[10]);
					ps.setString(15, rs2.getString(16));
					ps.setString(16, rs2.getString(17));
					ps.executeUpdate();
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean delete(String customerCode) {
		ConnectionControl conObject = new ConnectionControl();
		Connection link;
		try {
			link = conObject.connectToDatabase();
			PreparedStatement ps1 = link.prepareStatement("SELECT * FROM CUSTOMERTEMPORARY WHERE code=?");
			ps1.setString(1, customerCode);
			ResultSet rs = ps1.executeQuery();
			if(rs.next()) {
				PreparedStatement ps2 = link.prepareStatement("DELETE FROM CUSTOMERTEMPORARY WHERE code=?");
				ps2.setString(1, customerCode);
				ps2.executeUpdate();
				return true;
			} else {
				PreparedStatement ps2 = link.prepareStatement("SELECT * FROM CUSTOMERMASTER WHERE code=?");
				ps2.setString(1, customerCode);
				ResultSet rs2 = ps2.executeQuery();
				if(rs2.next()) {
					PreparedStatement ps = link.prepareStatement("INSERT INTO CUSTOMERTEMPORARY values(customertemporary_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setString(1, rs2.getString(2));
					ps.setString(2, rs2.getString(3));
					ps.setString(3, rs2.getString(4));
					ps.setString(4, rs2.getString(5));
					ps.setInt(5, rs2.getInt(6));
					ps.setString(6, rs2.getString(7));
					ps.setLong(7, rs2.getLong(8));
					ps.setString(8, rs2.getString(9));
					ps.setString(9, "D");
					ps.setString(10, rs2.getString(11));
					ps.setString(11, rs2.getString(12));
					ps.setString(12, rs2.getString(13));
					ps.setString(13, rs2.getString(14));
					ps.setString(14, rs2.getString(15));
					ps.setString(15, rs2.getString(16));
					ps.setString(16, rs2.getString(17));
					ps.executeUpdate();
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<CustomerInfo> retrieveSpecificRecord(String customerCode) {
		ConnectionControl conObject = new ConnectionControl();
		Connection link;
		List<CustomerInfo> records = new ArrayList<CustomerInfo>();
		try {
			link = conObject.connectToDatabase();
			PreparedStatement ps1 = link.prepareStatement("SELECT * FROM CUSTOMERTEMPORARY WHERE code=?");
			ps1.setString(1, customerCode);
			ResultSet rs = ps1.executeQuery();
			if(rs.next()) {
				CustomerInfo customer = new CustomerInfo();
				customer.setCustomerCode(rs.getString("code"));
				customer.setCustomerName(rs.getString("name"));
				customer.setAddress1(rs.getString("address1"));
				customer.setAddress2(rs.getString("address2"));
				customer.setPincode(rs.getInt("pincode"));
				customer.setEmail(rs.getString("email"));
				customer.setContactNumber(rs.getLong("contact"));
				customer.setPrimaryContactPerson(rs.getString("primaryContactPerson"));
				customer.setRecordStatus(rs.getString("recordStatus"));
				customer.setFlag(rs.getString("flag"));
				customer.setCreatedBy(rs.getString("createdBy"));
				customer.setCreateDate(rs.getString("createDate"));
				customer.setModifiedBy(rs.getString("modifiedBy"));
				customer.setModifiedDate(rs.getString("modifiedDate"));
				records.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(records.isEmpty()) {
			return null;
		} else {
			return records;
		}
	}
	public List<CustomerInfo> retrieveAllMaker() {
		ConnectionControl conObject = new ConnectionControl();
		Connection link;
		List<CustomerInfo> records = new ArrayList<CustomerInfo>();
		try {
			link = conObject.connectToDatabase();
			PreparedStatement ps = link.prepareStatement("SELECT * FROM CUSTOMERTEMPORARY");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CustomerInfo customer = new CustomerInfo();
				customer.setCustomerCode(rs.getString("code"));
				customer.setCustomerName(rs.getString("name"));
				customer.setAddress1(rs.getString("address1"));
				customer.setAddress2(rs.getString("address2"));
				customer.setPincode(rs.getInt("pincode"));
				customer.setEmail(rs.getString("email"));
				customer.setContactNumber(rs.getLong("contact"));
				customer.setPrimaryContactPerson(rs.getString("primaryContactPerson"));
				customer.setRecordStatus(rs.getString("recordStatus"));
				customer.setFlag(rs.getString("flag"));
				customer.setCreatedBy(rs.getString("createdBy"));
				customer.setCreateDate(rs.getString("createDate"));
				customer.setModifiedBy(rs.getString("modifiedBy"));
				customer.setModifiedDate(rs.getString("modifiedDate"));
				records.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (records.isEmpty()) {
			return null;
		} else {
			return records;
		}
	}
	
	public List<CustomerInfo> retrieveAllChecker() {
		ConnectionControl conObject = new ConnectionControl();
		Connection link;
		List<CustomerInfo> records = new ArrayList<CustomerInfo>();
		try {
			link = conObject.connectToDatabase();
			PreparedStatement ps = link.prepareStatement("SELECT * FROM CUSTOMERTEMPORARY");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CustomerInfo customer = new CustomerInfo();
				customer.setCustomerCode(rs.getString("code"));
				customer.setCustomerName(rs.getString("name"));
				customer.setAddress1(rs.getString("address1"));
				customer.setAddress2(rs.getString("address2"));
				customer.setPincode(rs.getInt("pincode"));
				customer.setEmail(rs.getString("email"));
				customer.setContactNumber(rs.getLong("contact"));
				customer.setPrimaryContactPerson(rs.getString("primaryContactPerson"));
				customer.setRecordStatus(rs.getString("recordStatus"));
				customer.setFlag(rs.getString("flag"));
				customer.setCreatedBy(rs.getString("createdBy"));
				customer.setCreateDate(rs.getString("createDate"));
				customer.setModifiedBy(rs.getString("modifiedBy"));
				customer.setModifiedDate(rs.getString("modifiedDate"));
				if((customer.getRecordStatus().equals("N") || customer.getRecordStatus().equals("M") || customer.getRecordStatus().equals("D")))
					records.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (records.isEmpty()) {
			return null;
		} else {
			return records;
		}
	}
	
	public List<CustomerInfo> retrieveAllMaster() {
		ConnectionControl conObject = new ConnectionControl();
		Connection link;
		List<CustomerInfo> records = new ArrayList<CustomerInfo>();
		try {
			link = conObject.connectToDatabase();
			PreparedStatement ps = link.prepareStatement("SELECT * FROM CUSTOMERMASTER");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CustomerInfo customer = new CustomerInfo();
				customer.setCustomerCode(rs.getString("code"));
				customer.setCustomerName(rs.getString("name"));
				customer.setAddress1(rs.getString("address1"));
				customer.setAddress2(rs.getString("address2"));
				customer.setPincode(rs.getInt("pincode"));
				customer.setEmail(rs.getString("email"));
				customer.setContactNumber(rs.getLong("contact"));
				customer.setPrimaryContactPerson(rs.getString("primaryContactPerson"));
				customer.setRecordStatus(rs.getString("recordStatus"));
				customer.setFlag(rs.getString("flag"));
				customer.setCreatedBy(rs.getString("createdBy"));
				customer.setCreateDate(rs.getString("createDate"));
				customer.setModifiedBy(rs.getString("modifiedBy"));
				customer.setModifiedDate(rs.getString("modifiedDate"));
				records.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (records.isEmpty()) {
			return null;
		} else {
			return records;
		}
	}
	public boolean authorize(String customerCode, String username) {
		ConnectionControl conObject = new ConnectionControl();
		Connection link;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		try {
			link = conObject.connectToDatabase();
			PreparedStatement ps = link.prepareStatement("SELECT * FROM CUSTOMERTEMPORARY WHERE code=?");
			ps.setString(1, customerCode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString(10).equals("N")) {
					PreparedStatement ps1 = link.prepareStatement("INSERT INTO CUSTOMERMASTER values(customermaster_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps1.setString(1, rs.getString(2));
					ps1.setString(2, rs.getString(3));
					ps1.setString(3, rs.getString(4));
					ps1.setString(4, rs.getString(5));
					ps1.setInt(5, rs.getInt(6));
					ps1.setString(6, rs.getString(7));
					ps1.setLong(7, rs.getLong(8));
					ps1.setString(8, rs.getString(9));
					ps1.setString(9, "A");
					ps1.setString(10, rs.getString(11));
					ps1.setString(11, rs.getString(12));
					ps1.setString(12, rs.getString(13));
					ps1.setString(13, rs.getString(14));
					ps1.setString(14, rs.getString(15));
					ps1.setString(15, (String)dateFormat.format(date));
					ps1.setString(16, username);
					ps1.executeUpdate();
					PreparedStatement ps2 = link.prepareStatement("DELETE FROM CUSTOMERTEMPORARY WHERE code=?");
					ps2.setString(1, customerCode);
					ps2.executeUpdate();
					return true;
				} else if(rs.getString(10).equals("M")) {
					PreparedStatement ps2 = link.prepareStatement("DELETE FROM CUSTOMERMASTER WHERE code=?");
					ps2.setString(1, customerCode);
					ps2.executeUpdate();
					PreparedStatement ps1 = link.prepareStatement("INSERT INTO CUSTOMERMASTER values(customermaster_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps1.setString(1, rs.getString(2));
					ps1.setString(2, rs.getString(3));
					ps1.setString(3, rs.getString(4));
					ps1.setString(4, rs.getString(5));
					ps1.setInt(5, rs.getInt(6));
					ps1.setString(6, rs.getString(7));
					ps1.setLong(7, rs.getLong(8));
					ps1.setString(8, rs.getString(9));
					ps1.setString(9, "A");
					ps1.setString(10, rs.getString(11));
					ps1.setString(11, rs.getString(12));
					ps1.setString(12, rs.getString(13));
					ps1.setString(13, rs.getString(14));
					ps1.setString(14, rs.getString(15));
					ps1.setString(15, (String)dateFormat.format(date));
					ps1.setString(16, username);
					ps1.executeUpdate();
					PreparedStatement ps3 = link.prepareStatement("DELETE FROM CUSTOMERTEMPORARY WHERE code=?");
					ps3.setString(1, customerCode);
					ps3.executeUpdate();
					return true;
				} else if(rs.getString(10).equals("D")) {
					PreparedStatement ps2 = link.prepareStatement("DELETE FROM CUSTOMERMASTER WHERE code=?");
					ps2.setString(1, customerCode);
					ps2.executeUpdate();
					PreparedStatement ps3 = link.prepareStatement("DELETE FROM CUSTOMERTEMPORARY WHERE code=?");
					ps3.setString(1, customerCode);
					ps3.executeUpdate();
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean reject(String customerCode) {
		ConnectionControl conObject = new ConnectionControl();
		Connection link;
		try {
			link = conObject.connectToDatabase();
			PreparedStatement ps = link.prepareStatement("SELECT * FROM CUSTOMERTEMPORARY WHERE code=?");
			ps.setString(1, customerCode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString(10).equals("N")) {
					PreparedStatement ps2 = link.prepareStatement("UPDATE CUSTOMERTEMPORARY SET recordStatus=? WHERE code=?");
					ps2.setString(1, "NR");
					ps2.setString(2, customerCode);
					ps2.executeUpdate();
					return true;
				} else if(rs.getString(10).equals("M")) {
					PreparedStatement ps2 = link.prepareStatement("UPDATE CUSTOMERTEMPORARY SET recordStatus=? WHERE code=?");
					ps2.setString(1, "MR");
					ps2.setString(2, customerCode);
					ps2.executeUpdate();
					return true;
				} else if(rs.getString(10).equals("D")) {
					PreparedStatement ps2 = link.prepareStatement("UPDATE CUSTOMERTEMPORARY SET recordStatus=? WHERE code=?");
					ps2.setString(1, "DR");
					ps2.setString(2, customerCode);
					ps2.executeUpdate();
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}