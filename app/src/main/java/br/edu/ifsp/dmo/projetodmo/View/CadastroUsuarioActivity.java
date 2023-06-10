package br.edu.ifsp.dmo.projetodmo.View;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.dmo.projetodmo.MVP.CadastroMVP;
import br.edu.ifsp.dmo.projetodmo.Presenter.CadastroPresenter;
import br.edu.ifsp.dmo.projetodmo.R;

public class CadastroUsuarioActivity extends AppCompatActivity implements CadastroMVP.View, View.OnClickListener{
    private CadastroMVP.Presenter presenter;
    private EditText usernameEditText;
    private EditText fullNameEditText;
    private EditText cityEditText;
    private EditText stateEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button confirmButton;
    private Button alreadyHasAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        findViews();
        setListener();
        presenter = new CadastroPresenter(this);
    }

    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void findViews(){
        usernameEditText = findViewById(R.id.edittext_userC);
        fullNameEditText = findViewById(R.id.edittext_nomeC);
        cityEditText = findViewById(R.id.edittext_cidadeC);
        stateEditText = findViewById(R.id.edittext_estadoC);
        phoneEditText = findViewById(R.id.edittext_telefoneC);
        passwordEditText = findViewById(R.id.edittext_senhaC);
        confirmPasswordEditText = findViewById(R.id.edittext_senhaConfirmaC);
        confirmButton = findViewById(R.id.button_confirmarC);
        alreadyHasAccountButton = findViewById(R.id.button_jaTemContaC);

    }

    private void setListener(){
        confirmButton.setOnClickListener(this);
        alreadyHasAccountButton.setOnClickListener(this);
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void close() {
        presenter.deatach();
        finish();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (view == confirmButton){
            if (presenter.checkPassword(passwordEditText.getText().toString(), confirmPasswordEditText.getText().toString())){
                presenter.saveUser(usernameEditText.getText().toString(), fullNameEditText.getText().toString(),
                        cityEditText.getText().toString(), stateEditText.getText().toString(),
                        phoneEditText.getText().toString(), passwordEditText.getText().toString());
            } else{
                showToast("Senhas não estão iguais");
            }
        }

        if (view == alreadyHasAccountButton){
            presenter.openLogin();
        }
    }
}
