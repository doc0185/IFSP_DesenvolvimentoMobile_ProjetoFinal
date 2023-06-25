package br.edu.ifsp.dmo.projetodmo.Model.Entities;

public class Usuario {
    private String nomeCompleto;
    private String username;
    private String cidade;
    private String estado;
    private String telefone;
    private String senha;

    public Usuario (String nomeCompleto, String username, String cidade, String estado, String telefone, String senha){
        this.nomeCompleto = nomeCompleto;
        this.username = username;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.senha = senha;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
