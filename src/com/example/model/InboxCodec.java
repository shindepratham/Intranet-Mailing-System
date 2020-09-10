package com.example.model;

import org.bson.*;
import org.bson.codecs.*;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.*;
import java.time.*;

public class InboxCodec implements CollectibleCodec<Inbox> {

  private final Codec<Document> documentCodec;

  public InboxCodec() {
    super();
    this.documentCodec = new DocumentCodec();
  }

  public void encode(BsonWriter bsonWriter, Inbox inbox, EncoderContext encoderContext) {
         Document inboxDoc=new Document();
		 ObjectId userId=inbox.getId();
		 inboxDoc.append("_id",userId);
		 String userName=inbox.getUserName();
		 inboxDoc.append("userName",userName);
	     List<Mail> readMail=inbox.getReadMail();
	     List<Mail>favourites=inbox.getFavourites();
	     List<Mail>unreadMail=inbox.getUnreadMail();
	     List<Mail>spam=inbox.getSpam(); 
		
		if(readMail!=null)
		{	 List  readMailDocument=new ArrayList<>();
			 
			 for(Mail m:readMail)
			 {
				 Document mailDocument=new Document();
				 String senderUserName = m.getSenderUserName();
				 List ccList = m.getCCList();
				 String subject= m.getSubject();
				 String message= m.getMessage();
				 String date=m.getDate();
				 String time=m.getTime();
	             List<ObjectId> fileIdList=m.getFileIdList();
				 if (null != senderUserName) {
				  mailDocument.append("senderUserName",senderUserName);
				}
				if (null != ccList) {
				  mailDocument.append("cc",ccList);
				}
				if (null != subject) {
				  mailDocument.append("subject",subject);
				}
				if (null != message) {
				  mailDocument.append("message",message);
				}
				if(null!=date){
					 mailDocument.append("date",date);
				}
				if(null!=time){
					 mailDocument.append("time",time);
				}
				if(null!=fileIdList){
					mailDocument.append("fileIdList",fileIdList);
				}
                readMailDocument.add(mailDocument);				
                				
			}
			inboxDoc.append("readMail",readMailDocument);
		}	
		
		  if(favourites!=null)
			  {
				  List favouriteMailDocument=new ArrayList<>();
				 
				 for(Mail m:favourites)
				 {
					 Document mailDocument=new Document();
					 String senderUserName = m.getSenderUserName();
					 List ccList = m.getCCList();
					 String subject= m.getSubject();
					 String message= m.getMessage();
					 String date=m.getDate();
					 String time=m.getTime();
					 List<ObjectId> fileIdList=m.getFileIdList();
					 if (null != senderUserName) {
					  mailDocument.append("senderUserName",senderUserName);
					}
					if (null != ccList) {
					  mailDocument.append("cc",ccList);
					}
					if (null != subject) {
					  mailDocument.append("subject",subject);
					}
					if (null != message) {
					  mailDocument.append("message",message);
					}
					if(null!=date){
						 mailDocument.append("date",date);
					}
					if(null!=time){
						 mailDocument.append("time",time);
					}
					if(null!=fileIdList){
						mailDocument.append("fileIdList",fileIdList);
					}
					favouriteMailDocument.add(mailDocument);				
									
				  }
				  inboxDoc.append("favourites",favouriteMailDocument);
		  }		  
	     if(unreadMail!=null)
		 {	 
		   List unreadMailDocument=new ArrayList<>();
			 
			 for(Mail m:unreadMail)
			 {
				 Document mailDocument=new Document();
				 String senderUserName = m.getSenderUserName();
				 List ccList = m.getCCList();
				 String subject= m.getSubject();
				 String message= m.getMessage();
				 String date=m.getDate();
			     String time=m.getTime();
	             List<ObjectId> fileIdList=m.getFileIdList();
				 if (null != senderUserName) {
				  mailDocument.append("senderUserName",senderUserName);
				}
				if (null != ccList) {
				  mailDocument.append("cc",ccList);
				}
				if (null != subject) {
				  mailDocument.append("subject",subject);
				}
				if (null != message) {
				  mailDocument.append("message",message);
				}
				if(null!=date){
					 mailDocument.append("date",date);
				}
				if(null!=time){
					 mailDocument.append("time",time);
				}
				if(null!=fileIdList){
					mailDocument.append("fileIdList",fileIdList);
				}
                 unreadMailDocument.add(mailDocument);					
                				
			  }
			   inboxDoc.append("unreadMail",unreadMailDocument);	  
		 }  
	   if(spam!=null)
	   {  
		   List  spamMailDocument=new ArrayList<>();
			 
			 for(Mail m:spam)
			 {
				 Document mailDocument=new Document();
				 String senderUserName = m.getSenderUserName();
				 List  ccList = m.getCCList();
				 String subject= m.getSubject();
				 String message= m.getMessage();
				  String date=m.getDate();
			    String time=m.getTime();
	             List<ObjectId> fileIdList=m.getFileIdList();
				 if (null != senderUserName) {
				  mailDocument.append("senderUserName",senderUserName);
				}
				if (null != ccList) {
				  mailDocument.append("cc",ccList);
				}
				if (null != subject) {
				  mailDocument.append("subject",subject);
				}
				if (null != message) {
				  mailDocument.append("message",message);
				}
				if(null!=date){
					 mailDocument.append("date",date);
				}
				if(null!=time){
					 mailDocument.append("time",time);
				}
				if(null!=fileIdList){
					mailDocument.append("fileIdList",fileIdList);
				}
                spamMailDocument.add(mailDocument);				
                				
			  }
			  inboxDoc.append("spam",spamMailDocument);
	   }	  
	    
        documentCodec.encode(bsonWriter, inboxDoc, encoderContext);		   
    }
	public Inbox decode(BsonReader bsonReader, DecoderContext decoderContext) {
    Document inboxDoc = documentCodec.decode(bsonReader, decoderContext);
    String userName =inboxDoc.getString("userName");
	List<Document> readMailDocument=(List<Document>)inboxDoc.get("readMail");
	List<Document> spamMailDocument=(List<Document>)inboxDoc.get("spam");
	List<Document> unreadMailDocument=(List<Document>)inboxDoc.get("unreadMail");
	List<Document> favouriteMailDocument=(List<Document>)inboxDoc.get("favourites");
	
	
	List<Mail> readMail=new ArrayList<>();
	List<Mail> unreadMail=new ArrayList<>();
	List<Mail> favourites=new ArrayList<>();
	List<Mail> spam=new ArrayList<>();
	
	for(Document mailDocument:readMailDocument)
	{
		 String senderUserName=mailDocument.getString("senderUserName");
		 String receiverUserName=userName;
		 List<String> ccList=(List<String>)mailDocument.get("cc");
		 String subject=mailDocument.getString("subject");
		 String message=mailDocument.getString("message");
		String date=mailDocument.getString("date");
		 String time=mailDocument.getString("time");
		List<ObjectId> fileIdList=(List<ObjectId>)mailDocument.get("fileIdList");
		 Mail m=new Mail();
		 m.setSenderUserName(senderUserName);
		 m.setReceiverUserName(receiverUserName);
		 m.setCcList(ccList);
		 m.setSubject(subject);
		 m.setMessage(message);
		 m.setDate(date);
		 m.setTime(time);
		 m.setFileIdList(fileIdList);
		 readMail.add(m);
	}
	
      for(Document mailDocument:unreadMailDocument)
	{
		 String senderUserName=mailDocument.getString("senderUserName");
		 String receiverUserName=userName;
		 List<String> ccList=(List<String>)mailDocument.get("cc");
		 String subject=mailDocument.getString("subject");
		 String message=mailDocument.getString("message");
		 String date=mailDocument.getString("date");
		  String time=mailDocument.getString("time");
		 List<ObjectId> fileIdList=(List<ObjectId>)mailDocument.get("fileIdList");
		 Mail m=new Mail();
		 m.setSenderUserName(senderUserName);
		 m.setReceiverUserName(receiverUserName);
		 m.setCcList(ccList);
		 m.setSubject(subject);
		 m.setMessage(message);
		 m.setDate(date);
		 m.setTime(time);
		m.setFileIdList(fileIdList);
		 unreadMail.add(m);
	}	
   for(Document mailDocument:favouriteMailDocument)
	{
		 String senderUserName=mailDocument.getString("senderUserName");
		 String receiverUserName=userName;
		 List<String> ccList=(List<String>)mailDocument.get("cc");
		 String subject=mailDocument.getString("subject");
		 String message=mailDocument.getString("message");
		  String date=mailDocument.getString("date");
		 String time=mailDocument.getString("time");
		 List<ObjectId> fileIdList=(List<ObjectId>)mailDocument.get("fileIdList");
		 Mail m=new Mail();
		 m.setSenderUserName(senderUserName);
		 m.setReceiverUserName(receiverUserName);
		 m.setCcList(ccList);
		 m.setSubject(subject);
		 m.setMessage(message);
		 m.setDate(date);
		 m.setTime(time);
		 m.setFileIdList(fileIdList);
		 favourites.add(m);
	}
   for(Document mailDocument:spamMailDocument)
	{
		 String senderUserName=mailDocument.getString("senderUserName");
		 String receiverUserName=userName;
		 List<String> ccList=(List<String>)mailDocument.get("cc");
		 String subject=mailDocument.getString("subject");
		 String message=mailDocument.getString("message");
		 String date=mailDocument.getString("date");
		 String time=mailDocument.getString("time");
		 List<ObjectId> fileIdList=(List<ObjectId>)mailDocument.get("fileIdList");
		 Mail m=new Mail();
		 m.setSenderUserName(senderUserName);
		 m.setReceiverUserName(receiverUserName);
		 m.setCcList(ccList);
		 m.setSubject(subject);
		 m.setMessage(message);
		 m.setDate(date);
		 m.setTime(time);
		m.setFileIdList(fileIdList);
		 spam.add(m);
	}
   Inbox inbox =new Inbox();
   inbox.setUserName(userName);
   inbox.setReadMail(readMail);
   inbox.setFavourites(favourites);
   inbox.setUnreadMail(unreadMail);
   inbox.setSpam(spam);
   return inbox;
  }

  @Override
  public Class<Inbox> getEncoderClass() {
    return Inbox.class;
  }

  @Override
  public Inbox generateIdIfAbsentFromDocument(Inbox inbox) {
    return !documentHasId(inbox) ? inbox.withNewId() : inbox;
  }

  @Override
  public boolean documentHasId(Inbox inbox) {
    return null != inbox.getId();
  }

  @Override
  public BsonString getDocumentId(Inbox inbox) {
    if (!documentHasId(inbox)) {
      throw new IllegalStateException("This document does not have an " + "_id");
    }

    return new BsonString(inbox.getUserName());
 }
}  
