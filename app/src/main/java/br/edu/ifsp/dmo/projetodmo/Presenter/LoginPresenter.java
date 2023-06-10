package br.edu.ifsp.dmo.projetodmo.Presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import br.edu.ifsp.dmo.projetodmo.MVP.LoginMVP;
import br.edu.ifsp.dmo.projetodmo.Model.DAO.IUsuarioDAO;
import br.edu.ifsp.dmo.projetodmo.Model.DAO.UsuarioDAOSQLite;
import br.edu.ifsp.dmo.projetodmo.Model.Entities.Usuario;
import br.edu.ifsp.dmo.projetodmo.View.CadastroUsuarioActivity;
import br.edu.ifsp.dmo.projetodmo.View.WeatherActivity;

public class LoginPresenter implements LoginMVP.Presenter {
    private LoginMVP.View view;
    private IUsuarioDAO uDAO;
    public Usuario usuario;

    public LoginPresenter (LoginMVP.View view){
        this.view = view;
        uDAO = new UsuarioDAOSQLite(view.getContext());
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public void autenticate(String username, String senha) {
        if(uDAO.loginUser(username, senha)){
            openWeather();
        } else{
            Log.d("Erro", "Senha Incorreta");
        }
    }

    @Override
    public void openSignUp() {
        Intent intent = new Intent(view.getContext(), CadastroUsuarioActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void openWeather() {
        Intent intent = new Intent(view.getContext(), WeatherActivity.class);
        view.getContext().startActivity(intent);
    }
}
