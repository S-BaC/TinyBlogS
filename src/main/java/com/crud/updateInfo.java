package com.crud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateInfo
 */
@WebServlet("/updateInfo")
public class updateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateInfo() {
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
		
		switch(reqStr) {
			case "editProfile":
				editProfile(request);
				break;
			case "addBlog":
				addBlog(request);
				break;
			case "editBlog":
				editBlog(request);
				break;
		}
		
		getServletContext().getRequestDispatcher("/MePage").forward(request, response);
	}
	
	private void editProfile(HttpServletRequest request) {
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
			
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String title = request.getParameter("title");
			String img = request.getParameter("img");

			String query = "UPDATE bloggers SET name='"+ name +"', title='"+ title +"', img='"+ img +"' WHERE id = "+ id +";";
			PreparedStatement s = con.prepareStatement(query);
			boolean res = s.execute();
			
			System.out.println(res);
		
			s.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addBlog(HttpServletRequest request) {
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
			
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String text = request.getParameter("text");
			
			String query = "INSERT INTO blogs (title, text, date, authorID) "
							+ "VALUES ('"+ title +"', '"+ text +"', '"+ LocalDate.now() +"', "+id+");";
			PreparedStatement s = con.prepareStatement(query);
			boolean res = s.execute();
			
			System.out.println(res);
		
			s.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editBlog(HttpServletRequest request) {
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
			
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String text = request.getParameter("text");
			
			
			String query = "UPDATE blogs SET title='"+ title +"', text='"+ text +"' WHERE id = "+ id +";";
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
