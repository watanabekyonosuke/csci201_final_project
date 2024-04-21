This this the java serlvet for getting discussion items and comment items from sql.
ForumDisplayServlet will send back a list for discussion items, and the requestion should have the following parameters:{fgid, sort}. When sort is 0, the list will be sorted by the number of likes, and when sort is 1, the list will be sorted by the creation time.
When fgid is 0, the list will have discussion items of every category. If you only want the discussion item in a specific group, set fgid to the id of that group.
Commentserlvet will send back list of all comments under s specific title id, so you need the parameter{titleid}. 
Both serlvet is of type "POST", and a sample script to use them is in test.html.
JDBCConnector requires GSON and mysql-connector, you can either add them to the path or directly import my forumjava.zip project to eclipse.
To use JDBCConnector, remember to change the username and password on the top of the class JDBCConnector.
Discussion items has the following attributes:{title, titleid, fgid, userid,post,creationtime(number of hours since created), likes, numofcomments}
Comment items has the following attributes:{titleid, title, content, likes, houts, userid, username}
