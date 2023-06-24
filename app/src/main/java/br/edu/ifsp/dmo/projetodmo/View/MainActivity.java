package br.edu.ifsp.dmo.projetodmo.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.dmo.projetodmo.MVP.LoginMVP;
import br.edu.ifsp.dmo.projetodmo.Presenter.LoginPresenter;
import br.edu.ifsp.dmo.projetodmo.Presenter.ServicoNotificaoAPI;
import br.edu.ifsp.dmo.projetodmo.R;

public class MainActivity extends AppCompatActivity implements LoginMVP.View {
    private LoginMVP.Presenter presenter;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox checkBox;
    private Button confirmButton;
    private Button createUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListener();
        presenter = new LoginPresenter(this);
    }

    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    @Override
    protected void onResume(){
        presenter = new LoginPresenter(this);
        presenter.checkPreferences(usernameEditText, passwordEditText, checkBox);
        super.onResume();
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void findViews(){
        this.usernameEditText = findViewById(R.id.edittext_userM);
        this.passwordEditText = findViewById(R.id.edittext_senhaM);
        this.confirmButton = findViewById(R.id.button_loginM);
        this.checkBox = findViewById(R.id.check_remember);
        this.createUserButton = findViewById(R.id.button_novoUsuarioM);
    }

    private void setListener(){
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (presenter.autenticate(usernameEditText.getText().toString(), passwordEditText.getText().toString(), checkBox.isChecked())) {
                    Toast.makeText(getContext(), "Usuário logado com Sucesso!", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(getContext(), "Senha/Login incorreto!", Toast.LENGTH_LONG).show();
                }
            }
        });

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openSignUp();
            }
        });
    }
}