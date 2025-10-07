package br.facilitareabi.com.dto;

import br.facilitareabi.com.model.Usuario;

public class UsuarioDTO {
    //Data Transfer obj-> separa o que você envia/recebe da sua camada de domínio
    //é o que você envia para o usuário do site ou recebe dele via API; o que você expoem pro front

    private int id;
    private String login;
    private String feedback;

    public UsuarioDTO() {
    }

    public UsuarioDTO(int id, String login, String feedback) {
        this.id = id;
        this.login = login;
        this.feedback = feedback;
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


    //Método de conversão : converte uma entidade que vem do banco para um obj DTO enviado pro front

    public static UsuarioDTO converterUsuario (Usuario usuario){
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getFeedback()
        );
    }

}
