package br.com.frateu.blogcomentario.model;

public class Usuarios {
    public static final String NM_USUARIO_BD = "usuarios";
    public static final String NM_ID_USUARIO = "idUsuario";
    public static final String NM_NOME_USUARIO = "nomeUsuario";
    public static final String NM_EMAIL_USUARIO = "emailUsuario";
    public static final String NM_SENHA_USUARIO = "senhaUsuario";
    public static final String NM_SENHA_CONFIRMAR_USUARIO = "senhaConfirmarUsuario";

    private Long idUsuario;
    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;

    public Usuarios() {   
    }

    public Usuarios(String pNomeUsuario, String pEmailUsuario, String pSenhaUsuario) {
        nomeUsuario = pNomeUsuario;
        emailUsuario = pEmailUsuario;
        senhaUsuario = pSenhaUsuario;
    }

    /**
     * @return Long return the idUsuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return String return the nomeUsuario
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * @param nomeUsuario the nomeUsuario to set
     */
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * @return String return the emailUsuario
     */
    public String getEmailUsuario() {
        return emailUsuario;
    }

    /**
     * @param emailUsuario the emailUsuario to set
     */
    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    /**
     * @return String return the senhaUsuario
     */
    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    /**
     * @param senhaUsuario the senhaUsuario to set
     */
    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

}
