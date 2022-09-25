package com.crud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.lang.CharSequence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Blog> blogs = null;
		try {
			blogs = getBlogs();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Blog blog1 = new Blog(1, "How JSP Works", "Marc", LocalDate.now(), "It works.");
//		Blog blog2 = new Blog(5, "ThymeLeaf", "Steve", LocalDate.now(), "Engine for templating.");
		
		request.setAttribute("blogs", blogs);
		
		getServletContext()
		.getRequestDispatcher("/index.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected ArrayList<Blog> getBlogs() throws SQLException, ClassNotFoundException {
		
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/servlets";
			String user = "root";
			String pw = "bac";
			
			con = DriverManager.getConnection(url, user, pw);
			
			String query = "SELECT blogger.name, blog.* "
							+ "FROM blogs blog LEFT JOIN bloggers blogger ON blog.authorID = blogger.id;";
			
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
