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

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	PrintWriter pw = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
        CommentRequest req = new Gson().fromJson(request.getReader(), CommentRequest.class);
        int titleid=req.getTitleid();
        List<Comment> comments =JDBCConnector.getCommentsByTitleId(titleid);
        
        response.setStatus(HttpServletResponse.SC_OK);
        Gson gson=new Gson();
        pw.write(gson.toJson(comments));
        pw.flush();
        
        }catch(SQLException e) {
        	e.printStackTrace();
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        
    }


}