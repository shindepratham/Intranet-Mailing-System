package com.example.web;

import com.example.model.*;
import java.io.*;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;


@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class MakeMail extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        // gets values of text fields
		HttpSession session = request.getSession(false);
		String subject = request.getParameter("subject");
        String message = request.getParameter("message");
		String receiverUserName=request.getParameter("to");
        Part filePart = request.getPart("attachment");
		PrintWriter out = response.getWriter();
		User receiver=null;
		if(session==null)
			out.println("can't initialise session");
		else{
				if(((Boolean)session.getAttribute("logged-in"))==true)
				{
					String senderUserName = ((User)session.getAttribute("user")).getUserName();
					  Mail m=new Mail();
					  m.setSenderUserName(senderUserName);
					  m.setReceiverUserName(receiverUserName);
					  //m.setCcList(ccList);
					  m.setSubject(subject);
					  m.setMessage(message);
					  m.setDate();
					  m.setTime();
					  m.setFile(filePart);
					 try{
							 DB db=(DB)getServletContext().getAttribute("Database");
							 if(m.send(db)){
								 out.println("Successfully sent");
							 }
							 else{
								  out.println("send failed user not found");
							 }
						} 
				    catch(Exception es){
							es.printStackTrace();
					   }		 
		     }
             else{
					request.getRequestDispatcher("/index.jsp").forward(request, response);
		        }			
      }
		   
   }
}	   