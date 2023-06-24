package br.edu.ifsp.dmo.projetodmo.Presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmo.projetodmo.MVP.LoginMVP;
import br.edu.ifsp.dmo.projetodmo.Model.DAO.IUsuarioDAO;
import br.edu.ifsp.dmo.projetodmo.Model.DAO.UsuarioDAOSQLite;
import br.edu.ifsp.dmo.projetodmo.Model.Entities.Usuario;
import br.edu.ifsp.dmo.projetodmo.Util.Constant;
import br.edu.ifsp.dmo.projetodmo.View.CadastroUsuarioActivity;
import br.edu.ifsp.dmo.projetodmo.View.WeatherActivity;

public class LoginPresenter implements LoginMVP.Presenter {
    private LoginMVP.View view;
    private IUsuarioDAO uDAO;
    public Usuario usuario;
    private List<Usuario> dataset;


    public LoginPresenter (LoginMVP.View view){
        this.view = view;
        dataset = new ArrayList<>();
        uDAO = new UsuarioDAOSQLite(view.getContext());
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public boolean autenticate(String username, String senha, boolean savePreferences) {
        if(uDAO.loginUser(username, senha)){
            remember(username, senha, savePreferences);
            openWeather();
            return true;
        } else{
            Log.d("Erro", "Senha Incorreta");
            return false;
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


    @Override
    public void remember(String username, String senha, boolean savePreference) {
        SharedPreferences preferences = view.getContext()
                .getSharedPreferences(
                        Constant.FILE_LOGIN_PREFS,
                        Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        if(savePreference){
            editor.putString(Constant.KEY_USER, username);
            editor.putString(Constant.KEY_PASS, senha);
        }else{
            editor.putString(Constant.KEY_USER, "");
            editor.putString(Constant.KEY_PASS, "");
        }
        editor.putBoolean(Constant.KEY_PREFS, savePreference);
        editor.commit();
    }

    @Override
    public void checkPreferences(EditText user, EditText pass, CheckBox remember) {
        String username, password;
        boolean savePrefs;

        SharedPreferences preferences = view.getContext().getSharedPreferences(
                Constant.FILE_LOGIN_PREFS,
                Context.MODE_PRIVATE
        );

        savePrefs = preferences.getBoolean(Constant.KEY_PREFS, false);
        if(savePrefs){
            username = preferences.getString(Constant.KEY_USER, "");
            password = preferences.getString(Constant.KEY_PASS, "");
            user.setText(username);
            pass.setText(password);
        }
        remember.setChecked(savePrefs);

    }





}
