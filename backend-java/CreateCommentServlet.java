package forum;

import java.io.IOException;
import java.io.PrintWriter;


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

@WebServlet("/CreateCommentServlet")
public class CreateCommentServlet extends HttpServlet{
	  private static final long serialVersionUID = 1L;
	  
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    	PrintWriter pw = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
	        System.out.println("CreateCommentServlet called");
	        
	        Comment comment = new Gson().fromJson(request.getReader(), Comment.class);
	        
	        String content = comment.getContent();
	        int timeOfPost = comment.getHours();
	        int titleid = comment.getTitleid();
	        
	        Gson gson = new GsonBuilder().create();
	        
	    	Integer userId = (Integer) request.getSession().getAttribute("userid");
	        
	        if (content == null || content.isBlank() 
	        		|| timeOfPost <= 0 || titleid <= 0) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				String error = "Error: missing data";
				pw.write(gson.toJson(error));
				pw.flush();          	
	        	
	        }
	        
	        boolean canComment = JDBCConnector.createComment(content, userId, titleid, timeOfPost);
	        
	        
	        if (canComment) {
	        	response.setStatus(HttpServletResponse.SC_OK);
				 Map<String, String> success = new HashMap<>();
			     success.put("success", "Comment created successfully");
			     System.out.println(gson.toJson(success));
				pw.write(gson.toJson(success));
				pw.flush();	
	        } else {
	        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				Map<String, String> error = new HashMap<>();
			    error.put("error", "Unsuccessful Comment Creation");
			    System.out.println(gson.toJson(error));
				pw.write(gson.toJson(error));
				pw.flush();		
	        }
	        
	        
	        
	        	
	        
	        
	  
	}

}
