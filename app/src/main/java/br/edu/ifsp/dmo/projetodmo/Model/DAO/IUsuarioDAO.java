package br.edu.ifsp.dmo.projetodmo.Model.DAO;

import br.edu.ifsp.dmo.projetodmo.Model.Entities.Usuario;

public interface IUsuarioDAO {
    void create(Usuario u);
    boolean loginUser(String username, String senha);
}
