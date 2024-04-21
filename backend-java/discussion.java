package forum;

public class discussion {
	private String username;
	private int titleid;
	
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTitleid() {
		return titleid;
	}

	public void setTitleid(int titleid) {
		this.titleid = titleid;
	}
	public int getFgid() {
		return fgid;
	}

	public void setFgid(int fgid) {
		this.fgid = fgid;
	}
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
	public int getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(int creationtime) {
		this.creationtime = creationtime;
	}
	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getNumofcomments() {
		return numofcomments;
	}

	public void setNumofcomments(int numofcomments) {
		this.numofcomments = numofcomments;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	private int fgid;
	private int userid;
    private String post;
    private int creationtime;
    private int likes;
    private int numofcomments;
    
}
