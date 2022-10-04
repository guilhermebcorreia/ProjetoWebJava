<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-md-6">
   <div class="card">
       <div class="card-header">
           <h5>Material Form Inputs</h5>
           <!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
       </div>
       <div class="card-block">
           <form class="form-material">
           <input type="hidden" name="idUser">
               <div class="form-group form-default">
                   <input type="text" name="nome" class="form-control" id="nomeUsuario">
                   <span class="form-bar"></span>
                   <label class="float-label">Nome Completo</label>
               </div>
               <div class="form-group form-default">
                   <input type="text" name="email" class="form-control" id="emailUsuario">
                   <span class="form-bar"></span>
                   <label class="float-label">Email (exa@gmail.com)</label>
               </div>
               <div class="form-group form-default">
                   <input type="password" name="senha" class="form-control" id="senhaUsuario">
                   <span class="form-bar"></span>
                   <label class="float-label">Senha</label>
               </div>
              
               <div class="form-group form-default">
                   <input type="text" name="login" class="form-control" id="loginUsuario">
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
