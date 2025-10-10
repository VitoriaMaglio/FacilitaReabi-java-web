package br.facilitareabi.com.controller;
import br.facilitareabi.com.dao.UsuarioDao;
import br.facilitareabi.com.dto.UsuarioRequestDTO;
import br.facilitareabi.com.dto.UsuarioResponseDTO;
import br.facilitareabi.com.model.Usuario;
import br.facilitareabi.com.service.UsuarioService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
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
    public Response cadastrarUsuario(UsuarioRequestDTO usuario){
        //Lógica> se os dados não existirem Dados inválidos (400 Bad request)
        //se não, chama o service que vai cadastrar usuario,
        // Vamos ter um dto cadastrado atribuido ao service para buscar seu login
        // se login==login post retorna resposta ok insere um novo recurso (201 Created)
        // se não, bad request
        //trata com catch e erro interno
        try{
            usuarioService.cadastrar(usuario);
            UsuarioResponseDTO cadastrado = usuarioService.buscarUsuarioPorLogin(usuario.getLogin());
            if(cadastrado.getLogin().equals(usuario.getLogin())){ //verificando se o login retornado do banco de dados (cadastrado) é o mesmo login
                // que foi enviado na requisição (usuario). “O login que eu acabei de buscar no banco é igual ao login que o front mandou?”
                return Response.status(Response.Status.CREATED).build();
            } else
                return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/usuario/{login}")//{} indica que isso é variável
    @Produces(MediaType.APPLICATION_JSON)
    //@PathParam("login") captura valores direto da URL e envia como parâmetro
    //Tipo do caminho para receber
    public Response buscarUsuario(@PathParam("login") String login) throws SQLException {
        UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorLogin(login);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuário não encontrado.")
                    .build();
        }
       // UsuarioResponseDTO usuarioDTO = UsuarioResponseDTO.convertToUsuario(usuario); //pega um objeto da entidade e converte ele para dto
        //Você retorna o usuarioDTO que tem apenas os atrbutos específicos para passar pro front.
        return Response.ok(usuario).build();
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



//    public void cadastrarUsuario(){
//        Usuario usuario = new Usuario();
//        System.out.println("Digite seu login:");
//        usuario.setLogin(scanner.nextLine());
//        System.out.println("Digite sua senha:");
//        usuario.setSenha(scanner.nextLine());
//        usuarioService.cadastrarUsuario(usuario);
//
//    }
    public void registrarFeedback(Usuario usuario) {
        System.out.println("Digite seu feedback: ");
        String feedback = scanner.nextLine();
        usuarioDao.atualizarFeedback(usuario.getId(), feedback);
        usuario.setFeedback(feedback); // também atualiza o objeto
    }

//    public void atualizarUsuario(){
//        System.out.println("Deseja atualizar seu usuário?");
//        String resp = scanner.nextLine();
//        if (resp.equalsIgnoreCase("Sim")){
//            cadastrarUsuario();
//            usuarioDao.alterarUsuario(usuario);
//        }else{
//            System.out.println("Ok!");
//        }
//    }
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
