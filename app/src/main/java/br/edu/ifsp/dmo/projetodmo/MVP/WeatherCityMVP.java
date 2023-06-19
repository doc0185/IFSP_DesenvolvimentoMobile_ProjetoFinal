package br.edu.ifsp.dmo.projetodmo.MVP;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public interface WeatherCityMVP {

    interface View{
        Context getContext();
        Bundle getBundle();
    }

    interface Presenter{
        void deatach();
        void getWeatherDetails(TextView tituloTextView, TextView temperaturaTextView, TextView sensacaoTermicaTextView, TextView umidadeTextView, TextView descricaoTextView,
                               TextView velocidadeVentoTextoView, TextView nuvensTextView, TextView pressaoAtmosfericaTextview, EditText editTextCidade);

    }
}
