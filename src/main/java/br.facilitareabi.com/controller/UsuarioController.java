package br.facilitareabi.com.controller;
import br.facilitareabi.com.dao.UsuarioDao;
import br.facilitareabi.com.dto.UsuarioDTO;
import br.facilitareabi.com.model.Usuario;
import br.facilitareabi.com.service.UsuarioService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Scanner;

@Path("/usuario")
public class UsuarioController {
    private UsuarioService usuarioService = new UsuarioService();
    Usuario usuario = new Usuario();
    UsuarioDao usuarioDao = new UsuarioDao();
    Scanner scanner = new Scanner(System.in);

    //Primeiro método http -> cadastrar usuário -> POST
    //Inserir recurso usuário
    @POST
    @Path("/usuario")
    @Consumes(MediaType.APPLICATION_JSON)//é uma anotação do JAX-RS (Jersey) que indica qual tipo de dado o método espera receber no corpo da requisição HTTP.
    //RECEBE JSON DO FRONT
    @Produces(MediaType.APPLICATION_JSON)//produz uma resposta em json
    public Response cadastrarUsuario(Usuario usuario){
        //Lógica> se os dados não existirem Dados inválidos (400 Bad request)
        //se não, chama o service e retorna resposta ok insere um novo recurso (201 Created)
        //UsuarioDTO
        if (usuario.getLogin() == null && usuario.getSenha() == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro nos dados inseridos. Login ou senha inválidos.")
                    .build();
        }
        usuarioService.cadastrarUsuario(usuario);
        return Response.ok(usuario).build();
    }

    @GET
    @Path("/usuario/{login}")//{} indica que isso é variável
    @Produces(MediaType.APPLICATION_JSON)
    //@PathParam("login") captura valores direto da URL e envia como parâmetro
    //Tipo do caminho para receber
    public Response buscarUsuario(@PathParam("login") String login){
        Usuario usuario = usuarioService.buscarUsuarioPorLogin(login);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuário não encontrado.")
                    .build();
        }
        UsuarioDTO usuarioDTO = UsuarioDTO.converterUsuario(usuario); //pega um objeto da entidade e converte ele para dto
        //Você retorna o usuarioDTO que tem apenas os atrbutos específicos para passar pro front.
        return Response.ok(usuarioDTO).build();
    }


    public void buscarUsuario(Usuario usuario){
        System.out.println("Digite o login do usuário que você deseja buscar dados: ");
        String usuarioLogin = scanner.nextLine();
        Usuario usuarioEncontrado = usuarioDao.buscarLogin(usuarioLogin);
        if (usuarioLogin != null) {
            System.out.println("Usuário encontrado!");
            System.out.println("Login: " + usuarioEncontrado.getLogin());
            System.out.println("Senha: " + usuarioEncontrado.getSenha());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }



    public void cadastrarUsuario(){
        Usuario usuario = new Usuario();
        System.out.println("Digite seu login:");
        usuario.setLogin(scanner.nextLine());
        System.out.println("Digite sua senha:");
        usuario.setSenha(scanner.nextLine());
        usuarioService.cadastrarUsuario(usuario);

    }
    public void registrarFeedback(Usuario usuario) {
        System.out.println("Digite seu feedback: ");
        String feedback = scanner.nextLine();
        usuarioDao.atualizarFeedback(usuario.getId(), feedback);
        usuario.setFeedback(feedback); // também atualiza o objeto
    }

    public void atualizarUsuario(){
        System.out.println("Deseja atualizar seu usuário?");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("Sim")){
            cadastrarUsuario();
            usuarioDao.alterarUsuario(usuario);
        }else{
            System.out.println("Ok!");
        }
    }
    public void excluirUsuario(){
        UsuarioDao usuarioDao = new UsuarioDao();
        System.out.println("Deseja excluir um usuário?");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("Sim")){
                System.out.println("Digite o login do usuário que deseja excluir:");
                String login = scanner.nextLine();
                if (usuarioDao.existeUsuarioPorLogin(login)) {
                    usuarioDao.excluirUsua(login);
                } else {
                    System.out.println("Usuário com login '" + login + "' não encontrado no banco.");
                }
            } else {
                System.out.println("Operação cancelada.");
            }
        }
    }
