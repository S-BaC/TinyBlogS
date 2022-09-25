package com.crud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class formRequest
 */
@WebServlet("/formReq")
public class formReq extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public formReq() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reqStr = request.getQueryString().split("=")[1];
		String reqType = reqStr.split("&")[0];
		String reqParam = request.getQueryString().split("=")[2];
		
		String url = reqType.equals("deleteBlog") ? "/MePage" : "/forms.jsp";
		
		if(reqType.equals("deleteBlog")) {
			deleteBlog(reqParam);
		} else {
			request.setAttribute("id", reqParam);
			request.setAttribute("type", reqType);
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
//		
	}
	
	private void deleteBlog(String idStr) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost/servlets";
		String user = "root";
		String pw = "bac";
		
		try(Connection con = DriverManager.getConnection(url, user, pw)){
			
			String query = "DELETE FROM blogs WHERE id = "+ idStr +";";
			PreparedStatement s = con.prepareStatement(query);
			boolean res = s.execute();
			
			System.out.println(res);
		
			s.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
