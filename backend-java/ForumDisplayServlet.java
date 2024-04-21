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

import com.google.gson.Gson;

@WebServlet("/ForumDisplayServlet")
public class ForumDisplayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	PrintWriter pw = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        List<Integer> disIDs = new ArrayList<>();
        try {
        ForumRequest req = new Gson().fromJson(request.getReader(), ForumRequest.class);
        int fgid=req.getFgid();
        int sort=req.getSort();
        if(sort==0) {
	        if(fgid==0) {
	        	disIDs=JDBCConnector.sortbyLikes();
	        }else {
	        	disIDs=JDBCConnector.sortbyLikes(fgid);
	        }
        }else if(sort==1) {
        	if(fgid==0) {
	        	disIDs=JDBCConnector.sortbyTimes();
	        }else {
	        	disIDs=JDBCConnector.sortbyTimes(fgid);
	        }
        }
        
        List<discussion> contents = new ArrayList<>();
        for (int number : disIDs) {
            discussion temp=new discussion();
            temp=JDBCConnector.getDiscussionById(number);
            contents.add(temp);
        }
        Gson gson=new Gson();
        pw.write(gson.toJson(contents));
        pw.flush();
        
        }catch(SQLException e) {
        	e.printStackTrace();
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        
    }


}