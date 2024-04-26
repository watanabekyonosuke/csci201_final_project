package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/GetTotalPointsServlet")
public class GetTotalPointsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	PrintWriter pw = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        User user = new Gson().fromJson(request.getReader(), User.class);
        
    	Integer userid = user.getUserid();     
    	System.out.println(userid);
    	
    	Gson gson = new GsonBuilder().create();
                
        int viewTotalPoints = 0;
        
		try {
			viewTotalPoints = JDBCConnector.getPoints(userid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if (viewTotalPoints < 0) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }else {
            response.setStatus(HttpServletResponse.SC_OK);
			pw.write(gson.toJson(viewTotalPoints));
			pw.flush();		
        }
	}
}
