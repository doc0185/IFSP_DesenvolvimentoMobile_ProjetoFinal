package br.edu.ifsp.dmo.projetodmo.Model.DAO;

import br.edu.ifsp.dmo.projetodmo.Model.Entities.Usuario;

public interface IUsuarioDAO {
    void create(Usuario u);
    void loginUser(String username, String senha);
}
