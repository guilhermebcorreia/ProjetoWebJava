<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">

  <jsp:include page="templates/header.jsp"></jsp:include>

  <body>
  <!-- Pre-loader start -->
  
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
                                           <div class="col-sm-10">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Material Form Inputs</h5>
                                                        <!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
                                                    </div>
                                                    <div class="card-block">
                                                        <form class="form-material" action="<%=request.getContextPath()%>/servletUsuarioController" method="post" id="formUser">
												           	   <div class="form-group form-default">
													           	   <label for="idUser">Cod Usuário</label>
													           	   <span class="form-bar"></span>
													           	   <input type="text" name="idUser" id="idUser" readonly="readonly" class="form-group" value="${modelLogin.idUser}">
												           	   </div>
												               <div class="form-group form-default">
												                   <input type="text" name="nome" class="form-control" id="nomeUsuario" value="${modelLogin.nome}">
												                   <span class="form-bar"></span>
												                   <label class="float-label">Nome Completo</label>
												               </div>
												               <div class="form-group form-default">
												                   <input type="text" name="email" class="form-control" id="emailUsuario" value="${modelLogin.email}">
												                   <span class="form-bar"></span>
												                   <label class="float-label">Email (example@gmail.com)</label>
												               </div>
												               <div class="form-group form-default">
												                   <input type="password" name="senha" class="form-control" id="senhaUsuario" value="${modelLogin.senha}">
												                   <span class="form-bar"></span>
												                   <label class="float-label">Senha</label>
												               </div>
												              
												               <div class="form-group form-default">
												                   <input type="text" name="login" class="form-control" id="loginUsuario" value="${modelLogin.login}">
												                   <span class="form-bar"></span>
												                   <label class="float-label">Login</label>
												               </div>
												              
												               
												               <button class="btn btn-success">Incluir</button>
												               <button class="btn btn-primary">Alterar</button>
												               <button class="btn btn-warning">Consultar</button>
												               <button class="btn btn-danger">Excluir</button>
											           </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
