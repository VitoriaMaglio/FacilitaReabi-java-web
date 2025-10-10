package br.facilitareabi.com.dto;

import br.facilitareabi.com.model.Usuario;

public class UsuarioResponseDTO {

    private String login;
    private String feedback;

    public UsuarioResponseDTO() {
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

    //Método conversão Usuariodto-usuario
    //Precisa ter um construtor na entidade que corresponde aos atributos que vc tá passando
    public Usuario convertToUsuario(UsuarioResponseDTO dto){
        return new Usuario(dto.login, dto.feedback);

    }

    public UsuarioResponseDTO convertToUsuarioResponseDto(Usuario usuario){
        return new UsuarioResponseDTO(usuario.getLogin(), usuario.getFeedback());
    }

    public UsuarioResponseDTO(String login, String feedback){
        this.login = login;
        this.feedback = feedback;

    }
}


