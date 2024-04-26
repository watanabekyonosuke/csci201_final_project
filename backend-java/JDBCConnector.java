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
	private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "201finalproject";
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
	    
	    conn = DriverManager.getConnection("jdbc:mysql://csoasis.cb6ymk6iw65j.us-west-1.rds.amazonaws.com:3306/ForumData?user=" + DB_USER + "&password=" + DB_PASSWORD);
        
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
		    conn = DriverManager.getConnection("jdbc:mysql://csoasis.cb6ymk6iw65j.us-west-1.rds.amazonaws.com:3306/ForumData?user=" + DB_USER + "&password=" + DB_PASSWORD);

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
		    conn = DriverManager.getConnection("jdbc:mysql://csoasis.cb6ymk6iw65j.us-west-1.rds.amazonaws.com:3306/ForumData?user=" + DB_USER + "&password=" + DB_PASSWORD);

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
		    conn = DriverManager.getConnection("jdbc:mysql://csoasis.cb6ymk6iw65j.us-west-1.rds.amazonaws.com:3306/ForumData?user=" + DB_USER + "&password=" + DB_PASSWORD);

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
		    conn = DriverManager.getConnection("jdbc:mysql://csoasis.cb6ymk6iw65j.us-west-1.rds.amazonaws.com:3306/ForumData?user=" + DB_USER + "&password=" + DB_PASSWORD);

	        String query = "SELECT fd.titleid, fd.title, fd.fgid, fd.userid, u.username, fd.post, fd.creationtime, fd.likes, " +
                    "       (SELECT COUNT(*) FROM Comments c WHERE c.titleid = fd.titleid) AS commentCount " +
                    "FROM ForumDiscussions fd " +
                    "JOIN User u ON fd.userid = u.userid " +
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
		         discussion.setUsername(rs.getString("username")); // Set the username
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
		    conn = DriverManager.getConnection("jdbc:mysql://csoasis.cb6ymk6iw65j.us-west-1.rds.amazonaws.com:3306/ForumData?user=" + DB_USER + "&password=" + DB_PASSWORD);
	
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
	
	public static int registerUser(String username, String password, String email) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
	    Connection conn = null;
	    Statement st = null;
	    ResultSet rs = null;
	
		
		int userid = -1;
		
		try {
		    conn = DriverManager.getConnection("jdbc:mysql://csoasis.cb6ymk6iw65j.us-west-1.rds.amazonaws.com:3306/ForumData?user=" + DB_USER + "&password=" + DB_PASSWORD);
			
			st = conn.createStatement();
			System.out.println("Connected to database successfully.");
			
			rs = st.executeQuery("SELECT * FROM users WHERE username='" + username + "'");
			if (!rs.next()) {
				st = conn.createStatement();
				rs = st.executeQuery("SELECT * FROM users WHERE email='" + email + "'");
				if(!rs.next()) {  // if no user exists with the same username or email
					rs.close();
					st.execute("INSERT INTO users (username, password, email, points) VALUES ('" + username + "', '" + password + "', '" + email + "', 0)");					rs = st.executeQuery("SELECT LAST_INSERT_ID()");
					rs.next();
					userid = rs.getInt(1);
				}else {   //taken email
					userid = -2;
				}
			}
		}catch (SQLException sqle) {
	        System.out.println("SQLException in loginUser");
	        sqle.printStackTrace();
	    
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException sqle) {
	            System.out.println("sqle " + sqle.getMessage());
	        }
	    }
		
		return userid;
	}
	
	public static boolean createPost(String title, int fgid, int userid, String post) throws SQLException {
	   		
		Connection conn = null;
	    PreparedStatement ps = null;
		ResultSet rs = null;
	    
	    int points = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://csoasis.cb6ymk6iw65j.us-west-1.rds.amazonaws.com:3306/ForumData?user=" + DB_USER + "&password=" + DB_PASSWORD);
			String sql1 = "INSERT INTO ForumDiscussions (title, fgid, userid, post, creationtime, likes) VALUES (?, ?, ?, ?, ?, ?)";
			
	        ps = conn.prepareStatement(sql1);
	        
	        Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	        ps.setString(1, title);
	        ps.setInt(2, fgid);
	        ps.setInt(3, userid);
	        ps.setString(4, post);
	        ps.setTimestamp(5, creationTime);  
	        ps.setInt(6, 0);
	        int result = ps.executeUpdate();
	        
	        if (result > 0) {
				String sql2 = "SELECT points FROM user where userid = ?";
	        	
	        	ps = conn.prepareStatement(sql2);
	        	ps.setInt(1, userid);
	        	rs = ps.executeQuery();
	        	
	        	if (rs.next()) {
		        	points = rs.getInt("points");
		        	points += 2; // Add two points for new post
	        	}
	
	        	String updatePoints = "UPDATE user SET points = ? WHERE userid = ?";
	        	ps = conn.prepareStatement(updatePoints);
	        	
	        	ps.setInt(1, points);
	        	ps.setInt(2, userid);
	        	
	        	ps.executeUpdate();
	            return true;
	        } else {
	            return false;
	        }
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return false;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (ps != null) {
	                ps.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
				if (rs != null){
					rs.close();
				}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		
	}
	
	public static boolean createComment(String content, int userid, int titleid, int timeOfPost) {
	    Connection conn = null;
	    PreparedStatement ps = null;
		ResultSet rs = null;
	    
	    int points = 0;

	    
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://csoasis.cb6ymk6iw65j.us-west-1.rds.amazonaws.com:3306/ForumData?user=" + DB_USER + "&password=" + DB_PASSWORD);
			String sql1 = "INSERT INTO Comments (userid, comment, titleid, creationtime, likes) VALUES (?, ?, ?, ?, ?)";
			
	        ps = conn.prepareStatement(sql1);
	        
	        Timestamp creationTime = new Timestamp(System.currentTimeMillis());
	        
	        ps.setInt(1, userid);
	        ps.setString(2, content);
	        ps.setInt(3, titleid);
	        ps.setTimestamp(4, creationTime);
	        ps.setInt(5, 0);
	        
	        int result = ps.executeUpdate();
	        
	        if (result > 0) {
	        	String sql2 = "SELECT points FROM user where userid = ?";
	        	
	        	ps = conn.prepareStatement(sql2);
	        	ps.setInt(1, userid);
	        	rs = ps.executeQuery();
	        	
	        	if (rs.next()) {
	        		points = rs.getInt("points");
	        		points ++;  // Add one point for new comment
	        	}
	        	
	        	String updatePoints = "UPDATE user SET points = ? WHERE userid = ?";
	        	ps = conn.prepareStatement(updatePoints);
	        	
	        	ps.setInt(1, points);
	        	ps.setInt(2, userid);
	        	
	        	ps.executeUpdate();
	        	
	        	
	        	return true;
	        }else {
	        	return false;
	        }
	        
	    }catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return false;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        // Close resources to prevent memory leaks
	        try {
	            if (ps != null) {
	                ps.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
				if (rs != null){
					rs.close();
				}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public static boolean login(String username, String password) throws ClassNotFoundException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://csoasis.cb6ymk6iw65j.us-west-1.rds.amazonaws.com:3306/ForumData?user=" + DB_USER + "&password=" + DB_PASSWORD);
	        
	        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, username);
	        ps.setString(2, password);
	        
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	        	return true;
	        }else {
	        	return false;
	        }
	  
	    }catch (SQLException e) {
	    	e.printStackTrace();
	    	return false;
	    }finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (ps != null) {
	                ps.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException sqle) {
	            System.out.println("sqle " + sqle.getMessage());
	        }
	    }
	}
	
	public static int getUserId(String username) throws SQLException, ClassNotFoundException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    int userid = 0;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://csoasis.cb6ymk6iw65j.us-west-1.rds.amazonaws.com:3306/ForumData?user=" + DB_USER + "&password=" + DB_PASSWORD);
			
			String query = "SELECT userid FROM User WHERE username = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				userid = rs.getInt("userid");
			}
	    }catch (SQLException e) {
	    	e.printStackTrace();
	    	return -1;
	    }finally {
     
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
	        
	    }
	    
	    return userid;
	}
	
}