package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/SigninServlet")
public class SigninServlet  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		System.out.println("SigninServlet doPost called");
		
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		User user = new Gson().fromJson(request.getReader(), User.class);
		Gson gson = new GsonBuilder().create();
		
		String username = user.getUsername();
		String password = user.getPassword();
		
		if (username == null || username.isBlank()
				|| password == null || password.isBlank()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			String error = "User info missing";
			pw.write(gson.toJson(error));
			pw.flush();
		}
		
		boolean isUserRegistered = false;
		try {
			isUserRegistered = JDBCConnector.login(username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (isUserRegistered) {
			
			// retrieve userId from session
			int userid = 0;
			try {
				userid = JDBCConnector.getUserId(username);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (userid > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("userId", userid); 
				response.setStatus(HttpServletResponse.SC_OK);
				Map<String, Object> success = new HashMap<>();
				success.put("success", "User is registered");
				success.put("userId", userid); // Include userId in the response
				System.out.println(gson.toJson(success));
				pw.write(gson.toJson(success));
				pw.flush();
			}else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				Map<String, String> error = new HashMap<>();
				error.put("error", "Could not rerieve Id");
				pw.write(gson.toJson(error));
				pw.flush();
			}
		}else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			Map<String, String> error = new HashMap<>();
			error.put("error", "User not found");
			pw.write(gson.toJson(error));
			pw.flush();			
		}

	}
	
}
