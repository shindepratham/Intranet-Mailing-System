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
	private String message;
	private List<String> ccList;
	private String subject;
	private Part file;
	boolean spam;
    boolean favourite;
    boolean read;
	String date;
	String time;
	ObjectId fileId;
	
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
	public void setFileId(ObjectId fileId)
	 {
		 this.fileId=fileId;
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
	 public void setFile(Part file)
	 {
		this.file=file;
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
	 public ObjectId getFileId()
	 {
		 return fileId;
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
	  public Part getFile()
	 {
		return file;
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
		 fileId=null;
		 if(file!=null)
			fileId=db.uploadFile(file);
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
        if(fileId!=null)
		{
			mailDocument.append("fileId",fileId);
        }
     return mailDocument;		
	 } 
}