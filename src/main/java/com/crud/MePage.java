package com.crud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MePage
 */
@WebServlet("/MePage")
public class MePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		session.setAttribute("userId", 1);
		
		ArrayList<Blog> blogs = null;
		User user = null;
		int loggedInUser = (int) session.getAttribute("userId");
		try {
			blogs = getBlogs(loggedInUser);
			user = getUserInfo(loggedInUser);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		request.setAttribute("blogs", blogs);
		request.setAttribute("user", user);
		
		getServletContext()
			.getRequestDispatcher("/mePage.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
		
	protected User getUserInfo(int userID) throws SQLException, ClassNotFoundException {
		
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost/servlets";
			String user = "root";
			String pw = "bac";
			
			con = DriverManager.getConnection(url, user, pw);
			
			String query = "SELECT * FROM bloggers WHERE id = '"+ userID +"';";

			PreparedStatement s = con.prepareStatement(query);
			
			ResultSet res = s.executeQuery();
			
			User blogger = null;
			
			while(res.next())
			{
				
				blogger = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4));
			}
			
			res.close();
			s.close();
			con.close();
			
			
			
			return blogger;
			
		} finally {

	    }
		
	}
	
	
	protected ArrayList<Blog> getBlogs(int userID) throws SQLException, ClassNotFoundException {
		
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/servlets";
			String user = "root";
			String pw = "bac";
			
			con = DriverManager.getConnection(url, user, pw);
			
			String query = "SELECT blogger.name, blog.* FROM blogs blog LEFT JOIN bloggers blogger ON blog.authorID = blogger.id WHERE authorID = '"+ userID +"';";
//			SELECT blogger.*, blog.* FROM blogs blog LEFT JOIN bloggers blogger ON blog.authorID WHERE blogger.id = userID
					
			PreparedStatement s = con.prepareStatement(query);
			
			ResultSet res = s.executeQuery();
			
			ArrayList<Blog> blogs = new ArrayList<Blog>();
			
			while(res.next())
			{
				
				System.out.println("Information___________");
				System.out.println(res.getString(1));
				System.out.println(res.getInt(2));
				System.out.println(res.getString(3));
				System.out.println(LocalDate.parse(res.getDate(4).toString().substring(0,10)));
				System.out.println(res.getString(5));
				System.out.println("______________________");
				
				blogs.add(new Blog(
						res.getInt(2),
						res.getString(3),
						res.getString(1),
						LocalDate.parse(res.getDate(4).toString().substring(0,10)),
						res.getString(5),
						res.getInt(6)));
			}
			
			res.close();
			s.close();
			con.close();
			
			return blogs;
			
		} finally {

	    }
		
	}

}
