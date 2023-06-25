package br.edu.ifsp.dmo.projetodmo.View;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Timer;
import java.util.TimerTask;

import br.edu.ifsp.dmo.projetodmo.MVP.WeatherMVP;
import br.edu.ifsp.dmo.projetodmo.Presenter.ServicoNotificacaoAPI;
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

    public static final int REQUEST_PERMISSION_CODE = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        findViews();
        setListener();
        presenter = new WeatherPresenter(this);
        usarGPS();



    }

    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //startService( new Intent( this, ServicoNotificacaoAPI.class ));
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
                presenter.openCityWeather();
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
    public void onProviderEnabled(@NonNull String provider) {
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("teste", "onStatusChanged");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, (float) 100, (LocationListener) this);
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Log.d("teste", "onLocationChanged");

        presenter.getWeatherDetails( tituloTextView, temperaturaTextView, sensacaoTermicaTextView, umidadeTextView, descricaoTextView, velocidadeVentoTextView,
                nuvensTextView, pressaoAtmosfericaTextView, latitude, longitude);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_PERMISSION_CODE){
            for(int index = 0; index != permissions.length; index++){
                if(permissions[index].equalsIgnoreCase(Manifest.permission.ACCESS_FINE_LOCATION) && grantResults[index] == PackageManager.PERMISSION_GRANTED){
                    if (permissions[index+1].equalsIgnoreCase(Manifest.permission.ACCESS_COARSE_LOCATION) && grantResults[index] == PackageManager.PERMISSION_GRANTED){
                        usarGPS();

                    }
                }
            }
        }

    }

    private void usarGPS(){
        final Activity activity = this;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, (float) 100, (LocationListener) this);
        } else if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                    new AlertDialog.Builder(this).setMessage("Para que o aplicativo consiga obter a previsão do tempo é necessário fornecer a permissão de acesso, caso contrário o recurso será desabilitado. Caso marque a opção 'Não perguntar novamente' essa funcionalidade só será habilitada nas configurações do aplicativo do Android").setPositiveButton("Fornecer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_CODE);
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_CODE);

                        }
                        }).setNegativeButton("Agora não", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).show();

        } else{
            ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_PERMISSION_CODE
                );


        }
    }
}