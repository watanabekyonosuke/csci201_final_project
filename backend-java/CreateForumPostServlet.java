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

@WebServlet("/CreateForumPostServlet")
public class CreateForumPostServlet extends HttpServlet {
	  private static final long serialVersionUID = 1L;
	  
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    	PrintWriter pw = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
	        System.out.println("CreateForumPostServlet called");
	        
	        Gson gson = new GsonBuilder().create();
	        
	        discussion disc = new Gson().fromJson(request.getReader(), discussion.class);
	        
//	        ForumRequest fr = new Gson().fromJson(request.getReader(), ForumRequest.class);
	        
	        String title = disc.getTitle();
	        int fgid = disc.getFgid();
	        int userid = disc.getUserid();
	        
	        String post = disc.getPost();
	        
	        if (title == null || title.isBlank()
	        		|| fgid <= 0 || userid <= 0
	        		|| post == null || post.isBlank()) {
		        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					String error = "Missing create post info";
					pw.write(gson.toJson(error));
					pw.flush();     	
	        } else {
		        boolean canPost = false;
		        
		        try {
					canPost = JDBCConnector.createPost(title, fgid, userid, post);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		        
		        if (canPost) {
		        	response.setStatus(HttpServletResponse.SC_OK);
					 Map<String, String> success = new HashMap<>();
				     success.put("success", "Post created successfully");
				     System.out.println(gson.toJson(success));
					 pw.write(gson.toJson(success));
					 pw.flush();	     		
		        } else {
		        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					Map<String, String> error = new HashMap<>();
				    error.put("error", "Unsuccessful Post Creation");
				    System.out.println(gson.toJson(error));
					pw.write(gson.toJson(error));
					pw.flush();		        	
		        }
	        }  
		  
	  }
	  
}
