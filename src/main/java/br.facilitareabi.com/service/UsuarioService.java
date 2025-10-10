package br.facilitareabi.com.service;
import br.facilitareabi.com.dao.UsuarioDao;
import br.facilitareabi.com.dto.UsuarioRequestDTO;
import br.facilitareabi.com.dto.UsuarioResponseDTO;
import br.facilitareabi.com.model.Usuario;

import java.sql.SQLException;

public class UsuarioService {
    private UsuarioDao usuarioDao = new UsuarioDao();

//    public boolean verificarUsuario(Usuario usuario){
//        return usuario.getSenha() != null && usuario.getLogin() != null;
//    }
//    public void cadastrarUsuario(Usuario usuario){
//        if(verificarUsuario(usuario)){
//            usuarioDao.cadastrarUsuario(usuario);
//            System.out.printf("Usuário cadastrado com sucesso!");
//
//        }else {
//            System.out.printf("Usuário com dados inválidos");
//        }
//    }

    //Método com lógica de cadastrar usuário
    public void cadastrar(UsuarioRequestDTO usuarioRequestDTO) throws SQLException{
        usuarioDao.cadastrarUsuario(usuarioRequestDTO.converterToUsuario(usuarioRequestDTO));
        //Aqui vamos inserir no banco fazendo a conversão dos dados brutos para os dados da entidade
    }

    //Método com lógica de resposta dos dados do usuário

    public UsuarioResponseDTO buscarUsuarioPorLogin(String login) throws SQLException {
        //método com relação a requisição get usuario login en ccontroller
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        return dto.convertToUsuarioResponseDto(usuarioDao.buscarLogin(login));

    }
    }

