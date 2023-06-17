package br.edu.ifsp.dmo.projetodmo.Presenter;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.text.DecimalFormat;

import br.edu.ifsp.dmo.projetodmo.MVP.CadastroMVP;
import br.edu.ifsp.dmo.projetodmo.MVP.WeatherMVP;
import br.edu.ifsp.dmo.projetodmo.View.WeatherCityActivity;

public class WeatherPresenter implements WeatherMVP.Presenter{
    private WeatherMVP.View view;
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "5ee46de429dda4d97d4f95695e9eaef0";
    DecimalFormat df = new DecimalFormat("#.##");

    private String titulo;
    private String temperatura;
    private String sensacaoTermica;
    private int umidade;
    private String descricao;
    private String velocidadeVento;
    private String nuvens;
    private float pressaoAtmosferica;

    public WeatherPresenter (WeatherMVP.View view){
        this.view = view;
    }

    @Override
    public void deatach() {
        this.view = null;
    }

    @Override
    public void getWeatherDetails(TextView tituloTextView, TextView temperaturaTextView, TextView sensacaoTermicaTextView, TextView umidadeTextView, TextView descricaoTextView, TextView velocidadeVentoTextoView,
            TextView nuvensTextView, TextView pressaoAtmosfericaTextview, Double latitude, Double longitude) {
        String tempUrl = url + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + appid;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", latitude + " " + longitude);
                Log.d("response", response);

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    descricao = jsonObjectWeather.getString("description");
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");

                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                    pressaoAtmosferica = jsonObjectMain.getInt("pressure");
                    umidade = jsonObjectMain.getInt("humidity");

                    JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                    velocidadeVento = jsonObjectWind.getString("speed");
                    JSONObject jsonObjectsClouds = jsonResponse.getJSONObject("clouds");
                    nuvens = jsonObjectsClouds.getString("all");
                    JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                    titulo = jsonResponse.getString("name");

                    temperatura = df.format(temp) + " ºC";
                    sensacaoTermica = df.format(feelsLike) + " ºC";

                    String umidadeString = String.valueOf(umidade) + "%";
                    velocidadeVento += "m/s";
                    nuvens += "%";
                    String pressaoAtmosfericaString = String.valueOf(pressaoAtmosferica) + "hPa";

                    tituloTextView.setText(titulo);
                    temperaturaTextView.setText(temperatura);
                    sensacaoTermicaTextView.setText(sensacaoTermica);
                    umidadeTextView.setText(umidadeString);
                    descricaoTextView.setText(descricao);
                    velocidadeVentoTextoView.setText(velocidadeVento);
                    nuvensTextView.setText(nuvens);
                    pressaoAtmosfericaTextview.setText(String.valueOf(pressaoAtmosfericaString));



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(view.getContext(), error.toString().trim(), Toast.LENGTH_SHORT);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);



    }

    @Override
    public void openCityWeather() {
        Intent intent = new Intent(view.getContext(), WeatherCityActivity.class);
        view.getContext().startActivity(intent);

    }

}
