# csci201_final_project

## Deployment: 

1. Setup Tomcat Server and Servlets in Backend
- COPY all .java files in Backend-Java to a new Dynamic Web Project in Eclipse
- This project MUST be named “Project Backend” (for pathing)
- .java files should be placed in a package titled “forum”
- Add necessary jars to project build path (GSON, MYSQLConnector)
- In “servers” folder, find web.xml. Open with “generic text editor”.
- Add the following tag before the first '<servlet></servlet>' tag to bypass CORS. 

'<filter> <filter-name>CorsFilter</filter-name> <filter-class>org.apache.catalina.filters.CorsFilter</filter-class> <init-param> <param-name>cors.allowed.origins</param-name> <param-value>*</param-value> </init-param> <init-param> <param-name>cors.allowed.methods</param-name> <param-value>GET, POST, PUT, DELETE, OPTIONS</param-value> </init-param> <init-param> <param-name>cors.allowed.headers</param-name> <param-value>Content-Type, Authorization</param-value> </init-param> </filter> <filter-mapping> <filter-name>CorsFilter</filter-name> <url-pattern>/*</url-pattern> </filter-mapping>'

- Now in web perspective, click on the servers tab next to terminal, console, etc. Double click your Tomcat server.
- Edit the HTML/1.1 portname to be “3001”
- This can be set 8080 or any other available port, but you must change all the frontend url requests to reflect that. This just makes it match the pulled source code. 
- Now run “ProjectBackend” on your server. A 404 error page will pop up, but this is fine. No html is being served by the web project, but the servlets are up and ready to receive requests. 

2. Launch Frontend
- Navigate to the folder you initially pulled the repo into. 
- Cd into “app”
- Run “npm install” for base dependencies. 
- Additonally run “npm install react-router-dom” and “npm install react-icons” for added dependencies. 
- Try “npm start”
- If everything has been configured correctly, you should be in an instance of our web page with live access to the cloud database. 

