package com.example.model;

import java.io.*;
import java.util.*;
import org.bson.Document;  
import javax.servlet.http.Part;
import java.text.*;
import org.bson.types.ObjectId; 
import com.mongodb.DBObject;
import java.time.LocalDate;
import java.time.LocalTime;



 
public class Mail{
	private String senderUserName;
	private String receiverUserName;
	private List<String> ccList;
	private String subject;
	private String message;
	boolean spam;
    boolean favourite;
    boolean read;
	String date;
	String time;
	List<ObjectId> fileIdList;
	
	
	
    public void setDate(String date)
	{
			this.date=date;	
	}
	public void setDate()
	{
			final String ISO_FORMAT = "yyyy-MM-dd";
            final SimpleDateFormat sdf = new SimpleDateFormat(ISO_FORMAT);
			this.date = sdf.format(new Date());
			
	}
	
	
	
	public void setTime(String time)
	{
			this.time=time;
			
	}
	public void setTime()
	{
			 final String ISO_FORMAT = "h:mm a";
             final SimpleDateFormat sdf = new SimpleDateFormat(ISO_FORMAT);
             this.time= sdf.format(new Date());; 			 	
			
	}
	public void setFileIdList(List<ObjectId> fileIdList)
	 {
		 this.fileIdList=fileIdList;
	 }
	 


	 
     public void setSenderUserName(String senderUserName)
	 {
		 this.senderUserName= senderUserName;
     }
	 
	 public void setReceiverUserName(String receiverUserName)
     {
       this.receiverUserName=receiverUserName;
     }
	 public void setSubject(String subject)
	 {
		 this.subject=subject;
	 }
	 public void setMessage(String message)
	 {
		 this.message=message;
	 }
	 public void setCcList(List<String> ccList)
	 {
		this.ccList=ccList;
	 }





	 
    public String getSenderUserName()
	 {
		 return senderUserName;
     }
     public String getReceiverUserName()
     {
       return receiverUserName;
     }
	 public String getSubject()
	 {
		 return subject;
	 }
	 public String getMessage()
	 {
		 return message;
	 }
	 public List<ObjectId> getFileIdList()
	 {
		 return fileIdList;
	 }
	 public String getDate()
	 {
		 return date;
	 }
	 public List<String> getCCList()
	 {
		 return ccList;
	 }
	 public String getTime()
	 {
		 return time;
	 }
	 
	 
	 
     public boolean isSpam()
	 {
		 return spam;
	 }
     public boolean isFavourite()
	 {
		 return favourite;	
     }
     public boolean isRead()
	 {
		 return favourite;	
     }
	 
	 
	 

     public boolean send(DB db)
     {
		Document mail=new Document();
		List<String> receiverUserNames=new ArrayList<>();
		if(ccList!=null)
		{
		  for(String cc:ccList)
		  {
			  receiverUserNames.add(cc);
		  }			  
        }			
		receiverUserNames.add(receiverUserName);	
		db.uploadMail(this.toDocument(),receiverUserNames); 
		return true;
         		
	 }
	 
	 public Document toDocument()
	 {
		 Document mailDocument=new Document();
		 mailDocument.append("senderUserName",senderUserName)
		    .append("cc",ccList)
		    .append("subject",subject)
		    .append("message",message)
		    .append("date",date)
		   .append("time",time);
        if(fileIdList.size()!=0)
		{
			mailDocument.append("fileIdList",fileIdList);
        }
        return mailDocument;		
	 } 
}