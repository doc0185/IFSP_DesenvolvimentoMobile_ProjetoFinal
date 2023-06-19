package br.edu.ifsp.dmo.projetodmo.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.ifsp.dmo.projetodmo.MVP.WeatherCityMVP;
import br.edu.ifsp.dmo.projetodmo.Presenter.WeatherCityPresenter;
import br.edu.ifsp.dmo.projetodmo.Presenter.WeatherPresenter;
import br.edu.ifsp.dmo.projetodmo.R;

public class WeatherCityActivity extends AppCompatActivity implements WeatherCityMVP.View{
    private WeatherCityMVP.Presenter presenter;
    private EditText nomeCidadeEditText;
    private TextView tituloTextView;
    private TextView temperaturaTextView;
    private TextView sensacaoTermicaTextView;
    private TextView umidadeTextView;
    private TextView descricaoTextView;
    private TextView velocidadeVentoTextView;
    private TextView nuvensTextView;
    private TextView pressaoAtmosfericaTextView;
    private Button buscarCidadeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_city);
        findViews();
        setListener();
        presenter = new WeatherCityPresenter(this);
    }

    @Override
    public Context getContext() {
        return this;
    }
    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    private void findViews(){
        nomeCidadeEditText = findViewById(R.id.editTextCidadeWC);
        tituloTextView = findViewById(R.id.textViewTituloWC);
        temperaturaTextView = findViewById(R.id.textViewTemperaturaWC);
        sensacaoTermicaTextView = findViewById(R.id.textViewSensacaoTermicaWC);
        umidadeTextView = findViewById(R.id.textViewUmidadeWC);
        descricaoTextView = findViewById(R.id.textViewDescricaoWC);
        velocidadeVentoTextView = findViewById(R.id.textViewVelocidadeVentoWC);
        nuvensTextView = findViewById(R.id.textViewNuvensWC);
        pressaoAtmosfericaTextView = findViewById(R.id.textViewPressaoAtmosfericaWC);
        buscarCidadeButton = findViewById(R.id.buttonBuscaCidadeWC);
    }

    private void setListener(){
        buscarCidadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getWeatherDetails( tituloTextView, temperaturaTextView, sensacaoTermicaTextView,
                        umidadeTextView, descricaoTextView, velocidadeVentoTextView,
                        nuvensTextView, pressaoAtmosfericaTextView, nomeCidadeEditText);
            }
        });
    }
}