package br.edu.ifsp.dmo.projetodmo.View;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import br.edu.ifsp.dmo.projetodmo.MVP.WeatherMVP;
import br.edu.ifsp.dmo.projetodmo.Presenter.WeatherPresenter;
import br.edu.ifsp.dmo.projetodmo.R;

public class WeatherActivity extends AppCompatActivity implements WeatherMVP.View, LocationListener {
    private WeatherMVP.Presenter presenter;
    private TextView tituloTextView;
    private TextView temperaturaTextView;
    private TextView sensacaoTermicaTextView;
    private TextView umidadeTextView;
    private TextView descricaoTextView;
    private TextView velocidadeVentoTextView;
    private TextView nuvensTextView;
    private TextView pressaoAtmosfericaTextView;
    private Button buscarOutraCidadeButton;

    double latitude, longitude = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        findViews();
        setListener();
        presenter = new WeatherPresenter(this);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000L, (float) 0, (LocationListener) this);
        presenter.getWeatherDetails(temperaturaTextView, sensacaoTermicaTextView, umidadeTextView, descricaoTextView, velocidadeVentoTextView,
                nuvensTextView, pressaoAtmosfericaTextView, latitude, longitude);
    }

    private void findViews(){
        tituloTextView = findViewById(R.id.textViewTitulo);
        temperaturaTextView = findViewById(R.id.textViewTemperatura);
        sensacaoTermicaTextView = findViewById(R.id.textViewSensacaoTermica);
        umidadeTextView = findViewById(R.id.textViewUmidade);
        descricaoTextView = findViewById(R.id.textViewDescricao);
        velocidadeVentoTextView = findViewById(R.id.textViewVelocidadeVento);
        nuvensTextView = findViewById(R.id.textViewNuvens);
        pressaoAtmosfericaTextView = findViewById(R.id.textViewPressaoAtmosferica);
        buscarOutraCidadeButton = findViewById(R.id.buttonBuscaCidade);
    }

    private void setListener(){
        buscarOutraCidadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

    }
}
