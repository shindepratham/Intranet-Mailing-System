package com.example.model;
import com.mongodb.client.FindIterable; 
import com.mongodb.client.MongoCollection; 
import com.mongodb.client.MongoDatabase; 
import com.mongodb.client.model.Filters; 
import com.mongodb.client.model.Updates; 
import com.mongodb.ServerAddress;
import com.mongodb.MongoClientOptions;
import java.util.*; 
import java.text.*;
import org.bson.Document;  
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;  
import com.mongodb.client.*;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoDatabase;
import com.mongodb.session.ClientSession;
import com.mongodb.MongoClient;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.ClassModelBuilder;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.pojo.PropertyModelBuilder;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromCodecs;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import javax.servlet.http.Part;
import java.sql.Timestamp;
import java.io.*;
public class DB {

	 String user;
	 String password;
	 MongoDatabase database;
	 MongoCollection<User> usersCollection;
	  MongoCollection<Inbox> inboxCollection;
	 
	 // constructor
	 
	 public DB(String user,String password)
	 {
		 this.user=user;
		 this.password=password;
		 MongoCredential credential = MongoCredential.createCredential(user, "mail", password.toCharArray());
		 
		 InboxCodec inboxCodec=new InboxCodec();
		 UserCodec userCodec=new UserCodec();
		 
		 CodecRegistry codecRegistry =
         fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromCodecs(userCodec,inboxCodec));	
		 
		 
		 
		 MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential), MongoClientOptions.builder().codecRegistry(codecRegistry).build());		
         
		 database = mongoClient.getDatabase("mail");
		 com.mongodb.DB db = mongoClient.getDB("mail");
         if(!db.collectionExists("Users"))
		 {
		    database.createCollection("Users");
		 }
         if(!db.collectionExists("Inbox"))
		 {
		    database.createCollection("Inbox");
		 }
		 usersCollection=database.getCollection("Users", User.class).withCodecRegistry(codecRegistry);
		 inboxCollection=database.getCollection("Inbox", Inbox.class).withCodecRegistry(codecRegistry);
		 
         		 
			  
	 } 			
        
		
		public int createUser(User u) 
		    {
			  Bson queryFilter =eq("userName",u.getUserName());
			  long count=usersCollection.count(queryFilter);
			  if(count==0)
			  { 
			    usersCollection.insertOne(u);
				inboxCollection.insertOne(createFormatInbox(u.getUserName()));
				return 1;
			  }
              else
			  {
				  return 2;
 			  }
		    }
       private Inbox createFormatInbox(String userName)
       {
		        List<Mail>read=new ArrayList<Mail>();
				List<Mail>unread=new ArrayList<Mail>();
				List<Mail>favourites=new ArrayList<Mail>();
				List<Mail>spam=new ArrayList<Mail>();
				List <String> cc=new ArrayList<String>();
				cc.add("");
				Mail m=new Mail();
		        m.setSenderUserName("System");
		        m.setReceiverUserName(userName);
		        m.setCcList(cc);
		        m.setSubject("Sytem generated mail");
		        m.setMessage("This message is System generated");
		        m.setDate();
		        m.setTime();
		        m.setFileId(new ObjectId());
				read.add(m);
				unread.add(m);
				favourites.add(m);
				spam.add(m);
				 Inbox inbox =new Inbox();
			   inbox.setUserName(userName);
			   inbox.setReadMail(read);
			   inbox.setFavourites(favourites);
			   inbox.setUnreadMail(unread);
			   inbox.setSpam(spam);
			   return inbox;
       }		   
		
	  public ObjectId uploadFile(Part part)
	  {
		  final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		  InputStream inputStream=null;
		  
		  try{
			  inputStream =part.getInputStream();
		  }
		  catch(Exception e)
		  {}
		  
			  
		  
		  GridFSBucket gridFSBucket = GridFSBuckets.create(database, "uploadedFiles");
		  GridFSUploadOptions uploadOptions = new GridFSUploadOptions()
		  .chunkSizeBytes(1024)
		  .metadata(new Document("type", part.getHeader("type"))
		  .append("upload_date", sdf.format(timestamp))
		  .append("content_type", part.getContentType()));
		  ObjectId fileId = gridFSBucket.uploadFromStream(part.getName(),inputStream, uploadOptions);
		  return fileId;
	  }
	  
	   public User findUser(String userName,String password)
	   {
		   Bson queryFilter =and(eq("userName",userName),eq("password",password));
		   User user = usersCollection.find(queryFilter).iterator().tryNext();
		   return user;
	   }
       public Inbox getInbox(String userName)
       {
		   Bson queryFilter =eq("userName",userName);
		   Inbox inbox = inboxCollection.find(queryFilter).iterator().tryNext();
		   return inbox; 
	   }		   
	  
       public void uploadMail(Document mail,List<String>receivers)
       {
		  for(String receiver:receivers)
		  {
			  Bson queryFilter =eq("userName",receiver);
			  long count=inboxCollection.count(queryFilter);
			  if(count==1)
			    inboxCollection.updateOne(queryFilter,Updates.addToSet("unreadMail", mail));
		  }
       }
	   public static void main(String [] args)
	   {
		   DB db=new DB("admin","Nishantd@1410");
		   Inbox inbox=db.getInbox("nishantd.mishra");
		   
	   } 
 
}

				