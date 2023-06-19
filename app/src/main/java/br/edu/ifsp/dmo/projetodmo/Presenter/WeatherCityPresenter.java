package br.edu.ifsp.dmo.projetodmo.Presenter;

import android.util.Log;
import android.widget.EditText;
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

import br.edu.ifsp.dmo.projetodmo.MVP.WeatherCityMVP;

public class WeatherCityPresenter implements WeatherCityMVP.Presenter {
    private WeatherCityMVP.View view;

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


    public WeatherCityPresenter (WeatherCityMVP.View view){
        this.view = view;
    }


    @Override
    public void deatach() {
        this.view = null;
    }

    @Override
    public void getWeatherDetails(TextView tituloTextView, TextView temperaturaTextView, TextView sensacaoTermicaTextView,
                                  TextView umidadeTextView, TextView descricaoTextView, TextView velocidadeVentoTextoView,
                                  TextView nuvensTextView, TextView pressaoAtmosfericaTextview, EditText editTextCidade) {
        String cidade = editTextCidade.getText().toString().trim();
        if (cidade.equals("")){
            tituloTextView.setText("Cidade não encontrada");
        } else{
            String tempUrl = url + "?q=" + cidade + "&appid=" + appid;


            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

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




    }

}
