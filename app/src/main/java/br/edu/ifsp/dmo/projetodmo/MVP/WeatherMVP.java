package br.edu.ifsp.dmo.projetodmo.MVP;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public interface WeatherMVP {

    interface View{
        Context getContext();
        Bundle getBundle();
    }


    interface Presenter{
        void deatach();
        void getWeatherDetails(TextView tituloTextView, TextView temperaturaTextView, TextView sensacaoTermicaTextView, TextView umidadeTextView, TextView descricaoTextView,
                               TextView velocidadeVentoTextoView, TextView nuvensTextView, TextView pressaoAtmosfericaTextview, Double latitude, Double longitude);
        void openCityWeather();

    }
}
