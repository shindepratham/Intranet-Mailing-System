package com.example.web;

import com.example.model.*;
import java.io.*;
import java.io.InputStream;
import java.util.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItemFactory;
import org.bson.types.ObjectId;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import org.apache.commons.fileupload.FileItem;


@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class MakeMail extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        
		
		 PrintWriter out = response.getWriter();
		 DB db=(DB)getServletContext().getAttribute("Database");
		 HttpSession session = request.getSession(false);
		 HashMap<String,String> attributeMap=new HashMap<>();
         List<ObjectId> fileIdList=new ArrayList<>();		

		 List<FileItem> formItems;
         if (ServletFileUpload.isMultipartContent(request)) {
				try{	
					formItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					for (FileItem item : formItems) {
						if (item.isFormField()) {
							  String fieldName = item.getFieldName();
							  String fieldValue = item.getString();
							  attributeMap.put(fieldName,fieldValue);
						 } else {
								String fileName = item.getName();
								String contentType =item.getContentType();
								long fileSize=item.getSize();
								InputStream inputStream = item.getInputStream();
								fileIdList.add(db.uploadFile(fileName,contentType,fileSize,inputStream ));
							}
                       }
					}catch(Exception e){
						out.println("line no 40");	
						out.println(e);
				    }
			}
		String subject = attributeMap.get("subject");
        String message = attributeMap.get("message");
		String receiverUserName=attributeMap.get("to");

		User receiver=null;
		
		if(session==null)
			out.println("can't initialise session");
		else{ 
			   out.println("line no 78");	
			    User user=(User)session.getAttribute("user");
				if(((Boolean)session.getAttribute("logged-in"))==true && user!=null )
				{
					String senderUserName = user.getUserName();
					  Mail m=new Mail();
					  m.setSenderUserName(senderUserName);
					  m.setReceiverUserName(receiverUserName);
					  //m.setCcList(ccList);
					  m.setSubject(subject);
					  m.setMessage(message);
					  m.setDate();
					  m.setTime();
					  m.setFileIdList(fileIdList);
					 try{
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