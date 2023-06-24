package br.edu.ifsp.dmo.projetodmo.MVP;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;

public interface LoginMVP {
    interface View{
        Context getContext();
    }

    interface Presenter{
        void deatach();
        boolean autenticate(String username, String senha, boolean savePreferences);
        void openSignUp();

        void openWeather();

        void remember(String username, String senha, boolean savePreference);

        void checkPreferences(EditText username, EditText password, CheckBox remember);
    }
}
