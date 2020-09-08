package com.example.web;
import com.example.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;  

public class login extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response)
 throws IOException, ServletException {
	 String userName=request.getParameter("username");
	 String password=request.getParameter("pass");
	 DB db = (DB) getServletContext().getAttribute("Database");
	 boolean status=false;
	 HttpSession session = request.getSession();
	 PrintWriter out = response.getWriter();
	 session.setAttribute("logged-in",false);
	 User user=null;
	  if(session.isNew())		  
	  {
		user=db.findUser(userName,password);
		if(user==null)
			out.println("<H1>Intranet Mailing System</H1><br>Invalid credentials");
		else{
			session.setAttribute("user",user);
			session.setAttribute("logged-in",true);
			session.setMaxInactiveInterval(10*60);
			request.getRequestDispatcher("/inbox.jsp").forward(request, response);
		}	
	  }
      else{
		  if(((Boolean)session.getAttribute("logged-in"))==true)
		  {
			  request.getRequestDispatcher("/inbox.jsp").forward(request, response);
	      }	
        else{
			user=db.findUser(userName,password);
			if(user==null)
				out.println("<H1>Intranet Mailing System</H1><br>Invalid credentials");
			else{
				session.setAttribute("user",user);
				session.setAttribute("logged-in",true);
				session.setMaxInactiveInterval(10*60);
				request.getRequestDispatcher("/inbox.jsp").forward(request, response);
			   }	
		    }			
	     }		  
    }
} 
