package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public Registration() {
//        super();
//    }
//
//	/**
//	 * @see Servlet#init(ServletConfig)
//	 */
//	public void init(ServletConfig config) throws ServletException {
//	}
//
//	/**
//	 * @see Servlet#destroy()
//	 */
//	public void destroy() {
//	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String eMail = request.getParameter("eMail");
		
		if (firstName == null || eMail == null || firstName.isEmpty() || eMail.isEmpty()) {
			writer.append("<p class='error'>You are missing required fields</p>");
			writer.append("<meta http-equiv='refresh' content='5;URL=index.jsp'>");
			return;
		}
		
		if (lastName == null || lastName.isEmpty()) {
			lastName = "Lazybones";
		}
		
		writer.append("<h1>Super Fancy Welcome Page</h1>");
		writer.append("<p>Welcome, " + firstName + " " + lastName + ".  Great choice with " + eMail + "</p>");
		
		request.setAttribute("firstName", firstName);
		// I have no idea what the difference is between this and just getting the RequestDispatcher from request
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/SecondServlet");
		rd.include(request, response);
		writer.println("Aaand we're back");
	}
}
