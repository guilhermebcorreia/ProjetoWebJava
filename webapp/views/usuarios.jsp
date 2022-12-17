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
                                                        <h5>Cadastro Usuario</h5>
                                                        <!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
                                                    </div>
                                                    <div class="card-block">
                                                        <form class="form-material" action="<%= request.getContextPath() %>/servletUsuarioController" method="post" id="formUser" >
												           	   
												           	   <input type="hidden" name="acao" id="acao" value="">
												           	   
												           	   <div class="form-group form-default form-static-label">
													           	   <label for="idUser">Cod Usuario</label>
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
												                <button type="button" class="btn waves-effect waves-light btn-grd-primary">Novo</button>
                                                                <button class="btn waves-effect waves-light btn-grd-info">Salvar</button>                                                               
                                                                <button type="button" class="btn waves-effect waves-light btn-grd-danger" onclick="ExcluirUserAjax();">Excluir</button>
													            <button type="button" class="btn waves-effect waves-light btn-grd-warning" data-toggle="modal" data-target="#ModalUsuario">Pesquisar</button>
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
    
     <!-- Modal -->
<div class="modal fade" id="ModalUsuario" tabindex="-1" role="dialog" aria-labelledby="ModalUsuario" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="ModalUsuario">Pesquisa de usuario</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
      	<div class="input-group mb-3">
		  <input type="text" class="form-control" placeholder="Nome" aria-label="nome" id="nomeBusca" aria-describedby="basic-addon2">
		  <div class="input-group-append">
		    <button class="btn btn-success" type="button" onclick="buscarUsuario();">Buscar</button>
		  </div>
		</div>
		<div style="height:300px; overflow: scroll;">
		   <table class="table" id="tabelaresultados">
		       <thead>
		          <tr>
		             <th scope="col">ID</th>
		             <th scope="col">Nome</th>
		             <th scope="col">Acao</th>
		           </tr>		           
		       </thead>
		       <tbody>
		       
		       </tbody>
		   </table>
		
		</div>

      </div>
      <span id="totalResultados"></span>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>    
    
    <jsp:include page="templates/importsJavascript.jsp"></jsp:include>
    
    <script type="text/javascript">

function verEditar(id){
	
	var urlAction = document.getElementById("formUser").action;

	window.location.href = urlAction + '?acao=editarUsuario&id='+id;
	
}


function buscarUsuario(){

	var nomeBusca = document.getElementById("nomeBusca").value;

	if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '' )  { //Validar que o camnpo possue um valor

        var urlAction = document.getElementById("formUser").action;

	    $.ajax({

    	    method:"get",
    	    url: urlAction,
    	    data : "nomeBusca=" + nomeBusca + "&acao=buscarUsuario",
    	    success: function(response){

        	    var json = JSON.parse(response);


               	    
        	    $('#tabelaresultados > tbody > tr').remove();

        	    for(var p = 0; p < json.length; p++){

                   $('#tabelaresultados > tbody').append('<tr> <td>'+json[p].idUser+'</td><td>'+json[p].nome+
                           '</td><td><button onClick="verEditar('+json[p].idUser+')" type="button" class=btn btn-info">Editar</button></td></tr>');
            	}

        	    document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length; 
    	    }

   	    }).fail(function(xhr, status, erroThrown){
       	    alert('Erro ao buscar usuario por nome: ' + xhr.responseText)
       	    });

    	}


	
	
}

    function ExcluirUserAjax(){

    	if(confirm('Confirma exclusao do usuario?')){

            var urlAction = document.getElementById("formUser").action;
    	    var idUser = document.getElementById("idUser").value; 

    	    $.ajax({

        	    method:"get",
        	    url: urlAction,
        	    data : "id=" + idUser + "&acao=excluirajax",
        	    success: function(response){

        	    	LimparCampos();
        	    	document.getElementById('msg').textContent = response;
        	    	
           	    }


       	    }).fail(function(xhr, status, erroThrown){
           	    alert('Erro ao excluir usuario por id: ' + xhr.responseText)
           	    });

        	}

    }


    function ExcluirUser(){
        if(confirm('Confirma exclusao?')){
    	    document.getElementById("formUser").method = 'get';
    	    document.getElementById("acao").value = 'excluir';
    	    document.getElementById("formUser").submit();
        }
    }
    


    function LimparCampos(){

        var elementos = document.getElementById("formUser").elements; 
        
        for (p = 0; p < elementos.length; p ++){
    	    elementos[p].value = '';
        }

    }
</script>    
    
</body>
</html>
