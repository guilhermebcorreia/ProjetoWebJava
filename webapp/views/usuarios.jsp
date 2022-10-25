<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">

  <jsp:include page="templates/header.jsp"></jsp:include>

  <body>
  <!-- Pre-loader start -->
  <jsp:include page="templates/preLoader.jsp"></jsp:include>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
      
          <jsp:include page="templates/navbar.jsp"></jsp:include>
          
          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
              
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
                                        
                                            <!-- task, page, download counter  start -->
                                           <div class="col-sm-12">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Cadastro Usuário</h5>
                                                        <!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
                                                    </div>
                                                    <div class="card-block">
                                                        <form class="form-material" action="<%= request.getContextPath() %>/servletUsuarioController" method="post" id="formUser" >
												           	   
												           	   <input type="hidden" name="acao" id="acao" value="">
												           	   
												           	   <div class="form-group form-default form-static-label">
													           	   <label for="idUser">Cod Usuário</label>
													           	   <span class="form-bar"></span>
													           	   <input type="text" id="idUser" name="idUser" class="form-control" readonly="readonly" value="${modelLogin.idUser}">
												           	   </div>
												               <div class="form-group form-default">
												                   <input type="text" name="nomeUsuario" class="form-control" id="nomeUsuario" value="${modelLogin.nome}">
												                   <span class="form-bar"></span>
												                   <label class="float-label">Nome Completo</label>
												               </div>
												               <div class="form-group form-default">
												                   <input type="text" name="emailUsuario" class="form-control" id="emailUsuario" value="${modelLogin.email}">
												                   <span class="form-bar"></span>
												                   <label class="float-label">Email (example@gmail.com)</label>
												               </div>
												               <div class="form-group form-default">
												                   <input type="password" name="senhaUsuario" class="form-control" id="senhaUsuario" value="${modelLogin.senha}">
												                   <span class="form-bar"></span>
												                   <label class="float-label">Senha</label>
												               </div>
												              
												               <div class="form-group form-default">
												                   <input type="text" name="loginUsuario" class="form-control" id="loginUsuario" value="${modelLogin.login}">
												                   <span class="form-bar"></span>
												                   <label class="float-label">Login</label>
												               </div>
												              
												              <div class="card-block"> 
												               <button class="btn btn-success">Incluir</button>
												               <button class="btn btn-primary">Alterar</button>
												               <button class="btn btn-danger">Excluir</button>
												               </div>
											           </form>
                                                    </div>
                                                </div>
                                            </div>
                                            <span id="msg">${msg}</span>
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
    <jsp:include page="templates/importsJavascript.jsp"></jsp:include>
</body>
</html>
