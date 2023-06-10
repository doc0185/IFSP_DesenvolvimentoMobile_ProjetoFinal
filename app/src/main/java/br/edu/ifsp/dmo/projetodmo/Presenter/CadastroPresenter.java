package br.edu.ifsp.dmo.projetodmo.Presenter;

import android.content.Intent;

import br.edu.ifsp.dmo.projetodmo.MVP.CadastroMVP;
import br.edu.ifsp.dmo.projetodmo.Model.DAO.IUsuarioDAO;
import br.edu.ifsp.dmo.projetodmo.Model.DAO.UsuarioDAOSQLite;
import br.edu.ifsp.dmo.projetodmo.Model.Entities.Usuario;
import br.edu.ifsp.dmo.projetodmo.View.CadastroUsuarioActivity;
import br.edu.ifsp.dmo.projetodmo.View.MainActivity;

public class CadastroPresenter implements CadastroMVP.Presenter{
    private CadastroMVP.View view;
    private IUsuarioDAO uDAO;
    public Usuario usuario;

    public CadastroPresenter(CadastroMVP.View view){
        this.view = view;
        usuario = null;
        uDAO = new UsuarioDAOSQLite(view.getContext());
    }

    @Override
    public void deatach() {
        this.view = null;
    }

    @Override
    public void saveUser(String nomeCompleto, String username, String cidade, String estado, String telefone, String senha) {
        usuario = new Usuario(nomeCompleto, username, cidade, estado, telefone, senha);
        uDAO.create(usuario);
        view.showToast("Usuário Cadastrado com Sucesso!");
        view.close();
    }

    @Override
    public boolean checkPassword(String senha, String confirmaSenha) {
       if (senha.equals(confirmaSenha)){
           return true;
       } else{
           return false;
       }
    }

    @Override
    public void openLogin() {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        view.getContext().startActivity(intent);
    }
}
