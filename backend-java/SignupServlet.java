package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet{
	  private static final long serialVersionUID = 1L;
	  
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		  
		  	response.setHeader("Access-Control-Allow-Origin", "*"); // Allow requests from any origin
	        response.setHeader("Access-Control-Allow-Methods", "POST"); // Allow POST requests
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Allow Content-Type header
	        response.setHeader("Access-Control-Max-Age", "3600"); // Cache preflight response for 1 hour
	    	
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
	        PrintWriter pw = response.getWriter();
	        
	        
	        System.out.println("SignupServlet called");	        
	        
	        User user = new Gson().fromJson(request.getReader(), User.class);
	        
	        String username = user.getUsername();
	        String email = user.getEmail();
	        String password = user.getPassword();
	        
	        Gson gson = new GsonBuilder().create();
	        
	        if (username == null || username.isBlank()
	        		|| email == null || email.isBlank()
	        		|| password == null || password.isBlank() ) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					String error = "User info missing";
					pw.write(gson.toJson(error));
					pw.flush();     	
		       }
	        
	        	
	        int userid = JDBCConnector.registerUser(username, password, email);
	        
	        if(userid == -1) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				String error = "Username is taken";
				pw.write(gson.toJson(error));
				pw.flush();
			}else if (userid == -2) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				String error = "Email is already registered";
				pw.write(gson.toJson(error));
				pw.flush();
			} else {
				if (userid > 0) {
					HttpSession session = request.getSession();
		            session.setAttribute("userId", userid);  // Store the user ID in the session
					response.setStatus(HttpServletResponse.SC_OK);
					pw.write(gson.toJson(userid));
					pw.flush();
				}
			}
	  }
}
