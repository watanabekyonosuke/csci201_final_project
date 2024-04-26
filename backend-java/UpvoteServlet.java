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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/UpvoteServlet")
public class UpvoteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	PrintWriter pw = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Gson gson = new GsonBuilder().create();
        
        
        int userid = Integer.parseInt(request.getParameter("userid"));
        int titleid = Integer.parseInt(request.getParameter("titleid"));
        String type = request.getParameter("type"); // post or comment
        
        
        boolean canUpvote = JDBCConnector.insertUpvote(userid, titleid, type);
        
        if (canUpvote) {
        	response.setStatus(HttpServletResponse.SC_OK);
        	Map<String, String> success = new HashMap<>();
        	success.put("success", "Successful upvote");
        	pw.write(gson.toJson(success));
        	pw.flush();
        } else {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			Map<String, String> error = new HashMap<>();
		    error.put("error", "Unsuccessful upvote");
		    System.out.println(gson.toJson(error));
			pw.write(gson.toJson(error));
			pw.flush();	
        }
    }
        
        
	
}
