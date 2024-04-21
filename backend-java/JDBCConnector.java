package forum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


public class JDBCConnector {
	private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Completist99";
	public static List<Integer> sortbyLikes() throws SQLException{
	    List<Integer> disIDs = new ArrayList<>();
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection conn = null;
	    Statement st = null;
	    ResultSet rs = null;

	    conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/forumdata?user=" + DB_USER + "&password=" + DB_PASSWORD);
        
	    String query = "SELECT titleid " +
                "FROM ForumDiscussions " +
                "ORDER BY likes DESC";
	    
	    st = conn.createStatement();
	    rs = st.executeQuery(query);

	    while (rs.next()) {
	    	Integer discussionId = rs.getInt("titleid");
	    	disIDs.add(discussionId);
	    }
	 if (rs != null) {
	     rs.close();
	 }
	 if (st != null) {
	     st.close();
	 }
	 if (conn != null) {
	     conn.close();
	 }

	 return disIDs;

	}
	public static List<Integer> sortbyLikes(int fgid) throws SQLException {
	    List<Integer> disIDs = new ArrayList<>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/forumdata?user=" + DB_USER + "&password=" + DB_PASSWORD);

	        String query = "SELECT titleid " +
	                       "FROM ForumDiscussions " +
	                       "WHERE fgid = ? " +
	                       "ORDER BY likes DESC";

	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, fgid);

	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Integer discussionId = rs.getInt("titleid");
	            disIDs.add(discussionId);
	        }
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        if (stmt != null) {
	            stmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }

	    return disIDs;
	}
	public static List<Integer> sortbyTimes() throws SQLException {
	    List<Integer> disIDs = new ArrayList<>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/forumdata?user=" + DB_USER + "&password=" + DB_PASSWORD);

	        String query = "SELECT titleid " +
	                       "FROM ForumDiscussions " +
	                       "ORDER BY creationtime DESC";

	        stmt = conn.prepareStatement(query);

	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Integer discussionId = rs.getInt("titleid");
	            disIDs.add(discussionId);
	        }
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        if (stmt != null) {
	            stmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }

	    return disIDs;
	}
	public static List<Integer> sortbyTimes(int fgid) throws SQLException {
	    List<Integer> disIDs = new ArrayList<>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/forumdata?user=" + DB_USER + "&password=" + DB_PASSWORD);

	        String query = "SELECT titleid " +
	                       "FROM ForumDiscussions " +
	                       "WHERE fgid = ? " +
	                       "ORDER BY creationtime DESC";

	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, fgid);

	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Integer discussionId = rs.getInt("titleid");
	            disIDs.add(discussionId);
	        }
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        if (stmt != null) {
	            stmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }

	    return disIDs;
	}
	public static discussion getDiscussionById(int titleid) throws SQLException {
	    discussion discussion = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/forumdata?user=" + DB_USER + "&password=" + DB_PASSWORD);

	        String query = "SELECT fd.titleid, fd.title, fd.fgid, fd.userid, fd.post, fd.creationtime, fd.likes, " +
                    "       (SELECT COUNT(*) FROM Comments c WHERE c.titleid = fd.titleid) AS commentCount " +
                    "FROM ForumDiscussions fd " +
                    "WHERE fd.titleid = ?";

	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, titleid);

	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            discussion = new discussion();
	            discussion.setTitleid(rs.getInt("titleid"));
	            discussion.setTitle(rs.getString("title"));
	            discussion.setFgid(rs.getInt("fgid"));
	            discussion.setUserid(rs.getInt("userid"));
	            discussion.setPost(rs.getString("post"));
	            Timestamp creationTimestamp = rs.getTimestamp("creationtime");
	            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
	            long diffInMillis = currentTimestamp.getTime() - creationTimestamp.getTime();
	            int diffInHours = (int) (diffInMillis / (60 * 60 * 1000));
	            discussion.setCreationtime(diffInHours);
	            discussion.setLikes(rs.getInt("likes"));
	            discussion.setNumofcomments(rs.getInt("commentCount"));
	        }
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        if (stmt != null) {
	            stmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }

	    return discussion;
	}
	

	public static List<Comment> getCommentsByTitleId(int titleid) throws SQLException {
	    List<Comment> comments = new ArrayList<>();
	
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	
	    try {
	        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/forumdata?user=" + DB_USER + "&password=" + DB_PASSWORD);
	
	        String query = "SELECT c.titleid, fd.title, c.comment AS content, c.likes, " +
	                       "       TIMESTAMPDIFF(HOUR, c.creationtime, NOW()) AS hours, " +
	                       "       c.userid, u.username " +
	                       "FROM Comments c " +
	                       "JOIN ForumDiscussions fd ON c.titleid = fd.titleid " +
	                       "JOIN User u ON c.userid = u.userid " +
	                       "WHERE c.titleid = ?";
	
	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, titleid);
	
	        rs = stmt.executeQuery();
	
	        while (rs.next()) {
	            Comment comment = new Comment();
	            comment.setTitleid(rs.getInt("titleid"));
	            comment.setTitle(rs.getString("title"));
	            comment.setContent(rs.getString("content"));
	            comment.setLikes(rs.getInt("likes"));
	            comment.setHours(rs.getInt("hours"));
	            comment.setUserid(rs.getInt("userid"));
	            comment.setUsername(rs.getString("username"));
	
	            comments.add(comment);
	        }
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        if (stmt != null) {
	            stmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }
	
	    return comments;
	}
}