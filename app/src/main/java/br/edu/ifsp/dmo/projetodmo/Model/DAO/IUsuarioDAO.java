package br.edu.ifsp.dmo.projetodmo.Model.DAO;

import java.util.List;

import br.edu.ifsp.dmo.projetodmo.Model.Entities.Usuario;

public interface IUsuarioDAO {
    void create(Usuario u);

    boolean loginUser(String username, String senha);

    List<Usuario> listUsers();
}
