package br.facilitareabi.com.dto;

import br.facilitareabi.com.model.Usuario;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioRequestDTO {
    //Data Transfer obj-> separa o que você envia/recebe da sua camada de domínio
    //é o que você envia para o usuário do site ou recebe dele via API; o que você expoem pro front

    private int id;
    private String login;
    private String senha;
    private String feedback;

    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(int id, String login, String feedback, String
                             senha) {
        this.id = id;
        this.login = login;
        this.feedback = feedback;
        this.senha= senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    //Método de conversão : converter um Usuariodto para um Usuario
    public Usuario converterToUsuario(UsuarioRequestDTO dto){
        Usuario usuario = new Usuario(dto.getId(),dto.getLogin(),dto.getSenha(), dto.getFeedback());
        return usuario;
    }

    //Método de conversão : converte uma entidade que vem do banco para um obj DTO enviado pro front
    public static UsuarioRequestDTO converterUsuarioDto (UsuarioRequestDTO usuario){
        return new UsuarioRequestDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getFeedback()
        );
    }

}
