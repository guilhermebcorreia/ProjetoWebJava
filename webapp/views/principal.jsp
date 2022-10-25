<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<!-- header -->
<jsp:include page="templates/header.jsp"></jsp:include>

  <body>
  <!-- Pre-loader start -->
<jsp:include page="templates/preLoader.jsp"></jsp:include>
  <!-- Pre-loader end -->
  
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
        <!-- navbar -->
		<jsp:include page="templates/navbar.jsp"></jsp:include>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
			<!--  sidebar -->
			<jsp:include page="templates/sidebar.jsp"></jsp:include>
			
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                     <jsp:include page="templates/pageHeader.jsp"></jsp:include>
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <div class="row">
                                            <h1>Principal</h1>
                                        </div>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    <!-- Required Jquery -->
   <jsp:include page="templates/importsJavascript.jsp"></jsp:include>
</body>

</html>
