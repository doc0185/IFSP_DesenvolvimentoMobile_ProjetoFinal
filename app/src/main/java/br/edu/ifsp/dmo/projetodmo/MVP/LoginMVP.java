package br.edu.ifsp.dmo.projetodmo.MVP;

import android.content.Context;

public interface LoginMVP {
    interface View{
        Context getContext();
    }

    interface Presenter{
        void deatach();
        boolean autenticate(String username, String senha);
        void openSignUp();

        void openWeather();
    }
}
