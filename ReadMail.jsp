<%@page language="java" contentType="text/html"%>
<%@ page import="com.example.model.*,javax.servlet.http.*" %>
<%@ page import="com.example.model.Mail" %>
<%@ page import="com.example.model.DB" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <!--  This file has been downloaded from bootdey.com    @bootdey on twitter -->
    <!--  All snippets are MIT license http://bootdey.com/license -->
    <title>colored inbox mail list and compose - Bootdey.com</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" >
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="utils/ReadMail.css">
</head>
<body>
<jsp:useBean id="user" class="com.example.model.User" scope="session" />
<%pageContext.setAttribute("inbox",((DB) getServletContext().getAttribute("Database")).getInbox(user.getUserName()),PageContext.PAGE_SCOPE);%>
<jsp:useBean id="inbox" class="com.example.model.Inbox" scope="page" />	
<% Mail m=inbox.getUnreadMail().get(Integer.parseInt(request.getParameter("mailId")));%>
	

<div class="container">
<div class="row">
    <div class="col-xs-12">
        <!-- start:inbox detail -->
        <div class="box">
            <div class="mail-box">
               <jsp:include page="SideBar.jsp" />

                <aside class="lg-side" >
                <div class="inbox-head">
                    <h3>View Mail</h3>
                    <form class="pull-right position" action="#">
                        <div class="input-append">
                            <input type="text" placeholder="Search Mail" class="sr-input">
                            <button type="button" class="btn sr-btn" data-original-title="" title=""><i class="fa fa-search"></i></button>
                        </div>
                    </form>
                </div>
                <div class="inbox-body">
                    <div class="heading-inbox row">
                        <div class="col-xs-8">
                            <div class="compose-btn">
                                <a class="btn btn-sm btn-primary" href="mail_compose.html" data-original-title="" title=""><i class="fa fa-reply"></i> Reply</a>
                                <button title="" data-placement="top" data-toggle="tooltip" type="button" data-original-title="Print" class="btn  btn-sm tooltips"><i class="fa fa-print"></i> </button>
                                <button title="" data-placement="top" data-toggle="tooltip" data-original-title="Trash" class="btn btn-sm tooltips"><i class="fa fa-trash-o"></i></button>
                            </div>
                        </div>
                        <div class="col-xs-4 text-right">
                            <p class="date"><%=m.getDate()%></p>
                        </div>
                        <div class="col-xs-12">
                            <h4> <%=m.getSubject()%></h4>
                        </div>
                    </div>
                    <div class="sender-info">
                        <div class="row">
                            <div class="col-xs-12">
                                <img alt="" src="https://bootdey.com/img/Content/avatar/avatar6.png">
                                <strong><%=m.getSenderUserName()%></strong>
                                <span>[johndoe@gmail.com]</span>
                                to
                                <strong>me</strong>
                                <a class="sender-dropdown " href="javascript:;">
                                    <i class="fa fa-chevron-down"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="view-mail">
					   <%=m.getMessage()%>
                        </div>
                    <div class="attachment-mail">
                        <p>
                            <span><i class="fa fa-paperclip"></i> 3 attachments — </span>
                            <a href="#">Download all attachments</a>
                            |
                            <a href="#">View all images</a>
                        </p>
                        <ul>
                            <li>
                                <a href="#" class="atch-thumb">
                                    <img src="https://via.placeholder.com/128x100">
                                </a>
                                <div class="file-name">
                                    image-name.jpg
                                </div>
                                <span>12KB</span>
                                <div class="links">
                                    <a href="#">View</a> -
                                    <a href="#">Download</a>
                                </div>
                            </li>
                            <li>
                                <a href="#" class="atch-thumb">
                                    <img src="https://via.placeholder.com/128x100">
                                </a>
                                <div class="file-name">
                                    img_name.jpg
                                </div>
                                <span>40KB</span>
                                <div class="links">
                                    <a href="#">View</a> -
                                    <a href="#">Download</a>
                                </div>
                            </li>
                            <li>
                                <a href="#" class="atch-thumb">
                                    <img src="https://via.placeholder.com/128x100">
                                </a>
                                <div class="file-name">
                                    img_name.jpg
                                </div>
                                <span>30KB</span>
                                <div class="links">
                                    <a href="#">View</a> -
                                    <a href="#">Download</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="compose-btn pull-left">
                        <a class="btn btn-sm btn-primary" href="mail_compose.html" data-original-title="" title=""><i class="fa fa-reply"></i> Reply</a>
                        <button class="btn btn-sm " data-original-title="" title=""><i class="fa fa-arrow-right"></i> Forward</button>
                        <button title="" data-placement="top" data-toggle="tooltip" type="button" data-original-title="Print" class="btn  btn-sm tooltips"><i class="fa fa-print"></i> </button>
                        <button title="" data-placement="top" data-toggle="tooltip" data-original-title="Trash" class="btn btn-sm tooltips"><i class="fa fa-trash-o"></i></button>
                    </div>
                </div>
            </aside>
            </div>
        </div>
        <!-- end:inbox detail -->
    </div>
</div>
</div>

<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/uxs/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script type="text/javascript">
	   var sidebar = $("#sidebar");
      var hamburger = $('#navTrigger');

       hamburger.click(function(e) {
      e.preventDefault();
       $(this).toggleClass('is-active');
     // This will add `sidebar-opened`
       $('box').toggleClass("sidebar-opened");
      // Remove magin left
      sidebar.toggleClass('ml-0');
      });
	</script>
</script>	
</body>
</html>