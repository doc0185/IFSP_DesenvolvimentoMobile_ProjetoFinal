package br.edu.ifsp.dmo.projetodmo.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.ifsp.dmo.projetodmo.MVP.WeatherCityMVP;
import br.edu.ifsp.dmo.projetodmo.Presenter.WeatherCityPresenter;
import br.edu.ifsp.dmo.projetodmo.R;

public class WeatherCityActivity extends AppCompatActivity implements WeatherCityMVP.View{
    private WeatherCityMVP.Presenter presenter;
    private EditText nomeCidadeEditText;
    private TextView tituloTextView;
    private TextView temperaturaTextView;
    private TextView temperaturaTTextView;
    private TextView sensacaoTermicaTextView;
    private TextView sensacaoTermicaTTextView;
    private TextView umidadeTextView;
    private TextView umidadeTTextView;
    private TextView descricaoTextView;
    private TextView descricaoTTextView;
    private TextView velocidadeVentoTextView;
    private TextView velocidadeVentoTTextView;
    private TextView nuvensTextView;
    private TextView nuvensTTextView;
    private TextView pressaoAtmosfericaTextView;
    private TextView pressaoAtmosfericaTTextView;
    private Button buscarCidadeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_city);
        findViews();
        setListener();
        hideTextView();
        setToolbar();
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
        temperaturaTTextView = findViewById(R.id.textViewTTemperaturaWC);
        sensacaoTermicaTextView = findViewById(R.id.textViewSensacaoTermicaWC);
        sensacaoTermicaTTextView = findViewById(R.id.textViewTSensacaoTermicaWC);
        umidadeTextView = findViewById(R.id.textViewUmidadeWC);
        umidadeTTextView = findViewById(R.id.textViewTUmidadeWC);
        descricaoTextView = findViewById(R.id.textViewDescricaoWC);
        descricaoTTextView = findViewById(R.id.textViewTDescricaoWC);
        velocidadeVentoTextView = findViewById(R.id.textViewVelocidadeVentoWC);
        velocidadeVentoTTextView = findViewById(R.id.textViewTVelocidadeVentoWC);
        nuvensTextView = findViewById(R.id.textViewNuvensWC);
        nuvensTTextView = findViewById(R.id.textViewTNuvensWC);
        pressaoAtmosfericaTextView = findViewById(R.id.textViewPressaoAtmosfericaWC);
        pressaoAtmosfericaTTextView = findViewById(R.id.textViewTPressaoAtmosfericaWC);
        buscarCidadeButton = findViewById(R.id.buttonBuscaCidadeWC);
    }

    private void hideTextView(){
        temperaturaTTextView.setVisibility(View.INVISIBLE);
        sensacaoTermicaTTextView.setVisibility(View.INVISIBLE);
        umidadeTTextView.setVisibility(View.INVISIBLE);
        descricaoTTextView.setVisibility(View.INVISIBLE);
        velocidadeVentoTTextView.setVisibility(View.INVISIBLE);
        nuvensTTextView.setVisibility(View.INVISIBLE);
        pressaoAtmosfericaTTextView.setVisibility(View.INVISIBLE);
    }

    private void showTextView(){
        temperaturaTTextView.setVisibility(View.VISIBLE);
        sensacaoTermicaTTextView.setVisibility(View.VISIBLE);
        umidadeTTextView.setVisibility(View.VISIBLE);
        descricaoTTextView.setVisibility(View.VISIBLE);
        velocidadeVentoTTextView.setVisibility(View.VISIBLE);
        nuvensTTextView.setVisibility(View.VISIBLE);
        pressaoAtmosfericaTTextView.setVisibility(View.VISIBLE);
    }

    private void setListener(){
        buscarCidadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTextView();
                presenter.getWeatherDetails( tituloTextView, temperaturaTextView, sensacaoTermicaTextView,
                        umidadeTextView, descricaoTextView, velocidadeVentoTextView,
                        nuvensTextView, pressaoAtmosfericaTextView, nomeCidadeEditText);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}