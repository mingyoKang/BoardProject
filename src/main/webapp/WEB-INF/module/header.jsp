<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

     <div id='header' class='row'>
            <div id=header-left class='col-2'> 
                <!-- 오프캔버스(Left 적용) -->
                <a type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasleft" aria-controls="offcanvasRight"><span style="font-weight: bold; font-family: cursive; font-size:20px">Menu</span></a>

                <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasleft" aria-labelledby="offcanvasRightLabel">
                  <div class="offcanvas-header">
                    <h5 id="offcanvasRightLabel">Menu</h5>
                    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                  </div>
                  <div class="offcanvas-body">
                    ...
                  </div>
                </div>

            </div>

            <div id=header-center class='col-8'></div>
            <div id=header-right  class='col-2'>
            	<ul style = "list-style: none; padding: 0px; text-align:right;">
            		<li style = "display: inline-block; margin-right: 10px;"><a href="/LogoutProc.do" style = "text-decoration:none; font-weight:bold; color: tomato;">Logout</a></li>
            		<li style = "display: inline-block; margin-right: 10px;">
            			<a href="#" style = "text-decoration:none; font-weight:bold; color: black;">
	            			<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-info-circle" viewBox="0 0 16 16">
	  						<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
	  						<path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
							</svg>
						</a>
            		</li>
            	</ul>
            </div>
     </div> 