               <aside id="sidebar" class="side-panel d-flex flex-column h-100 bg-golden sm-side">
                    <!-- d-lg-none will hide the button on medium and large screens -->
						 <a id="navTrigger" class="d-lg-none nav-trigger" role="button" title="sweet hamburger" >
								<span class="hamburger">
								    <span class="hamburger-icon"></span>
								</span>
						  </a>
						<div class="user-head">
							<a href="javascript:;" class="inbox-avatar">
								<img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="" class="img-responsive">
							</a>
							<div class="user-name">
								<h5><a href="#"></a></h5>
								<span><a href="#">${user.userName}</a></span>
							</div>
							<a href="javascript:;" class="mail-dropdown pull-right">
								<i class="fa fa-chevron-down"></i>
							</a>
						</div>
						<div class="inbox-body">
							<a class="btn btn-compose" data-toggle="modal" href="#myModal" data-original-title="" title="">
								Compose
							</a>
							<!-- Modal -->
				  
						</div>

						<ul class="inbox-nav inbox-divider">
							<li class="active">
								<a href="#"><i class="fa fa-inbox"></i> Inbox <span class="label label-danger pull-right">2</span></a>
							</li>
							<li>
								<a href="#"><i class="fa fa-envelope-o"></i> Sent Mail</a>
							</li>
							<li>
								<a href="#"><i class="fa fa-bookmark-o"></i> Important</a>
							</li>
							<li>
								<a href="#"><i class=" fa fa-external-link"></i> Drafts <span class="label label-info pull-right">30</span></a>
							</li>
							<li>
								<a href="#"><i class=" fa fa-trash-o"></i> Trash</a>
							</li>
						</ul>
						<ul class="nav nav-pills nav-stacked labels-info inbox-divider">
							<li> <h4>Labels</h4> </li>
							<li> <a href="#"> <i class=" fa fa-sign-blank text-danger"></i> Work </a> </li>
							<li> <a href="#"> <i class=" fa fa-sign-blank text-success"></i> Design </a> </li>
							<li> <a href="#"> <i class=" fa fa-sign-blank text-info "></i> Family </a>
							</li><li> <a href="#"> <i class=" fa fa-sign-blank text-warning "></i> Friends </a>
							</li><li> <a href="#"> <i class=" fa fa-sign-blank text-primary "></i> Office </a>
							</li>
						</ul>
						<ul class="nav nav-pills nav-stacked labels-info ">
							<li> <h4>Buddy online</h4> </li>
							<li> <a href="#"> <i class=" fa fa-circle text-success"></i> Jhone Doe <p>I do not think</p></a>  </li>
							<li> <a href="#"> <i class=" fa fa-circle text-danger"></i> Sumon <p>Busy with coding</p></a> </li>
							<li> <a href="#"> <i class=" fa fa-circle text-muted "></i> Anjelina Joli <p>I out of control</p></a>
							</li><li> <a href="#"> <i class=" fa fa-circle text-muted "></i> Jonathan Smith <p>I am not here</p></a>
							</li><li> <a href="#"> <i class=" fa fa-circle text-muted "></i> Tawseef <p>I do not think</p></a>
							</li>
						</ul>
						<div class="inbox-body text-center">
							<div class="btn-group">
								<a href="javascript:;" class="btn mini btn-primary" data-original-title="" title="">
									<i class="fa fa-plus"></i>
								</a>
							</div>
							<div class="btn-group">
								<a href="javascript:;" class="btn mini btn-success" data-original-title="" title="">
									<i class="fa fa-phone"></i>
								</a>
							</div>
							<div class="btn-group">
								<a href="javascript:;" class="btn mini btn-info" data-original-title="" title="">
									<i class="fa fa-cog"></i>
								</a>
							</div>
						</div>
                </aside>
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                        <h4 class="modal-title">Compose</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" method="post" action="compose.do"  enctype='multipart/form-data'>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label">To</label>
                                                <div class="col-lg-10">
                                                    <input type="text" class="form-control" name="to" id="inputEmail1" placeholder="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label">Cc / Bcc</label>
                                                <div class="col-lg-10">
                                                    <input type="text" class="form-control" id="cc" name="cc" placeholder="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label">Subject</label>
                                                <div class="col-lg-10">
                                                    <input type="text" class="form-control" id="inputPassword1" name="subject"  placeholder="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label">Message</label>
                                                <div class="col-lg-10">
                                                    <textarea  id="" class="form-control" cols="30" rows="10" name="message"></textarea>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <div class="col-lg-offset-2 col-lg-10">
                                                    <span class="btn green fileinput-button" data-original-title="" title="">
                                                      <i class="fa fa-plus fa fa-white"></i>
                                                      <span>Attachment</span>
                                                      <input type="file" multiple name="attachment">
                                                    </span>
                                                    <button type="submit" class="btn btn-send" >Send</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal-dialog -->
                        </div><!-- /.modal -->