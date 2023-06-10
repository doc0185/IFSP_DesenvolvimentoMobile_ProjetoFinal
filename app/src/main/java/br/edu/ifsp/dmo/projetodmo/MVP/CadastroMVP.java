package br.edu.ifsp.dmo.projetodmo.MVP;

import android.content.Context;
import android.os.Bundle;

public interface CadastroMVP {
    interface View{
        Context getContext();
        Bundle getBundle();
        void close();
        void showToast(String message);
    }

    interface Presenter{
        void deatach();
        void saveUser(String nomeCompleto, String username, String cidade, String estado, String telefone, String senha);
        boolean checkPassword(String senha, String confirmaSenha);

        void openLogin();
    }
}
