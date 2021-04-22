package com.example.gamestats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    public class DownloadTaskStats extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String json = "";
            URL url;

            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int data = reader.read();
                while (data != -1) {
                    char letter = (char) data;
                    json += letter;
                    data = reader.read();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);

            try {


                //data
                JSONObject jsonObject = new JSONObject(json);
                String data = jsonObject.getString("data");

                //player id
                JSONObject jsonObject1 = new JSONObject(data);
                String playerid = jsonObject1.getString(data.substring(2,11));

                //statystyki
                JSONObject jsonObject2 = new JSONObject(playerid);
                String statistics = jsonObject2.getString("statistics");

                //nickname
                JSONObject jsonObject3 = new JSONObject(playerid);
                String nickname = jsonObject3.getString("nickname");

                //pvp
                JSONObject jsonObject5 = new JSONObject(statistics);
                String pvp = jsonObject5.getString("pvp");

                //ilość bitwe
                JSONObject jsonObject4 = new JSONObject(pvp);
                String battles = jsonObject4.getString("battles");

                //% wygranych
                int battlesnumber=Integer.parseInt(battles.replaceAll("[\\D]",""));
                JSONObject jsonObject6 = new JSONObject(pvp);
                String wins = jsonObject6.getString("wins");
                int winsnumber=Integer.parseInt(wins.replaceAll("[\\D]",""));
                int winratio = (winsnumber * 100)/battlesnumber;

                //Fragi
                JSONObject jsonObject7 = new JSONObject(pvp);
                String frags = jsonObject7.getString("frags");

                //Średni xp
                JSONObject jsonObject8 = new JSONObject(pvp);
                String xp = jsonObject8.getString("xp");
                int xpNumber = Integer.parseInt(xp.replaceAll("[\\D]",""));
                int avgxp = (xpNumber/battlesnumber);

                //Śrenie fragi na bitwę
                //int fragsNumber = Integer.parseInt(frags.replaceAll("[\\D]",""));
                //double destructionRatio = fragsNumber / battlesnumber;

                //przegrane
                JSONObject jsonObject9 = new JSONObject(pvp);
                String losses = jsonObject9.getString("losses");

                //remisy
                JSONObject jsonObject10 = new JSONObject(pvp);
                String draws = jsonObject10.getString("draws");

                //główna bateria
                JSONObject jsonObject11 = new JSONObject(pvp);
                String mainBattery = jsonObject11.getString("main_battery");

                //zniszczenia główną baterią
                JSONObject jsonObject12 = new JSONObject(mainBattery);
                String mainBatteryFrags = jsonObject12.getString("frags");

                //trafienia główną baterią
                JSONObject jsonObject13 = new JSONObject(mainBattery);
                String mainBatteryHits = jsonObject13.getString("hits");

                //dodatkowa bateria
                JSONObject jsonObject14 = new JSONObject(pvp);
                String secondBattery = jsonObject14.getString("second_battery");

                //zniszczenia dodatkową baterią
                JSONObject jsonObject15 = new JSONObject(secondBattery);
                String secondBatteryFrags = jsonObject15.getString("frags");

                //trafienia dodatkową baterią
                JSONObject jsonObject16 = new JSONObject(secondBattery);
                String secondBatteryHits = jsonObject16.getString("hits");

                //torpedy
                JSONObject jsonObject17 = new JSONObject(pvp);
                String torpedoes = jsonObject17.getString("torpedoes");

                //zniszczenia torpedami
                JSONObject jsonObject18 = new JSONObject(torpedoes);
                String torpedoesFrags = jsonObject18.getString("frags");

                //trafienia torpedami
                JSONObject jsonObject19 = new JSONObject(torpedoes);
                String torpedoesHits = jsonObject19.getString("hits");

                //samoloty
                JSONObject jsonObject20 = new JSONObject(pvp);
                String aircraft = jsonObject20.getString("aircraft");

                //zniszczenia samolotami
                JSONObject jsonObject21 = new JSONObject(aircraft);
                String aircraftFrags = jsonObject21.getString("frags");


                //rekordy
                // rekord fragów
                JSONObject jsonObject22 = new JSONObject(pvp);
                String maxFrags = jsonObject22.getString("max_frags_battle");

                //rekord zniszczonych samolotów
                JSONObject jsonObject23 = new JSONObject(pvp);
                String maxPlanesKilled = jsonObject23.getString("max_planes_killed");

                //rekord uszkodzeń
                JSONObject jsonObject24 = new JSONObject(pvp);
                String maxDamage = jsonObject24.getString("max_damage_dealt");

                //rekord xp w bitwie
                JSONObject jsonObject25 = new JSONObject(pvp);
                String maxXP = jsonObject25.getString("max_xp");



                //Log.i("Staty", data);
                //Log.i("Playerid",playerid);
                //Log.i("Statystyki", statistics);
                //Log.i("pvp",pvp);
                Log.i("nickname",nickname);
                Log.i("battles",battles);
                Log.i("winratio", String.valueOf(winratio));
                Log.i("frags",frags);
                Log.i("avgxp", String.valueOf(avgxp));
                //Log.i("Fragi na bitwę", String.valueOf(destructionRatio));
                Log.i("losses",losses);
                Log.i("draws",draws);
                Log.i("mainBatteryFrags",mainBatteryFrags);
                Log.i("mainBatteryHits",mainBatteryHits);
                Log.i("secondBatteryFrags",secondBatteryFrags);
                Log.i("secondaryBatteryHits",secondBatteryHits);
                Log.i("torpedoesFrags",torpedoesFrags);
                Log.i("torpedoesHits",torpedoesHits);
                Log.i("aircraftFrags",aircraftFrags);
                Log.i("maxFrags",maxFrags);
                Log.i("maxDamage",maxDamage);
                Log.i("maxPlanesKilled",maxPlanesKilled);
                Log.i("maxXp",maxXP);

                TextView nicknameTextView = findViewById(R.id.nicknameTextView);
                nicknameTextView.setText("Nick: " + nickname);

                TextView battlesTextView = findViewById(R.id.battlesTextView);
                battlesTextView.setText("Wszystkie bitwy: " + battles);

                TextView lossesTextView = findViewById(R.id.lossesTextView);
                lossesTextView.setText("Przegrane: " + losses);

                TextView drawssTextView = findViewById(R.id.drawssTextView);
                drawssTextView.setText("Remisy: " + draws);

                TextView winratioTextView = findViewById(R.id.winratioTextView);
                winratioTextView.setText("Procent zwycięstw: "+String.valueOf(winratio)+"%");

                TextView fragsTextView = findViewById(R.id.fragsTextView);
                fragsTextView.setText("Fragi: "+frags);

                TextView avgxpTextView = findViewById(R.id.avgxpTextView);
                avgxpTextView.setText("Średnie doświadczenie na bitwę: "+String.valueOf(avgxp));

                TextView weaponTypeTextView = findViewById(R.id.weaponTypeTextView);
                weaponTypeTextView.setText("Zniszczenia wg uzbrojenia:");

                TextView mainBatteryFragsTextView = findViewById(R.id.mainBatteryFragsTextView);
                mainBatteryFragsTextView.setText("Bateria główna - fragi: "+mainBatteryFrags);

                TextView mainBatteryHitsTextView = findViewById(R.id.mainBatteryHitsTextView);
                mainBatteryHitsTextView.setText("Bateria główna - trafienia: "+mainBatteryHits);

                TextView secondBatteryFragsTextView = findViewById(R.id.secondBatteryFragsTextView);
                secondBatteryFragsTextView.setText("Działa dodatkowe - fragi: "+secondBatteryFrags);

                TextView secondaryBatteryhitsTextView = findViewById(R.id.secondBatteryHitsTextView);
                secondaryBatteryhitsTextView.setText("Działa dodatkowe = trafienia: "+ secondBatteryFrags);

                TextView torpedoesFragsTextView = findViewById(R.id.torpedoesFragsTextView);
                torpedoesFragsTextView.setText("Torpedy - fragi: "+torpedoesFrags);

                TextView torpedoesHitsTextView = findViewById(R.id.torpedoesHitsTextView);
                torpedoesHitsTextView.setText("Torpedy - trafienia: "+torpedoesHits);

                TextView aircraftFragsTextView = findViewById(R.id.aircraftFragsTextView);
                aircraftFragsTextView.setText("Samoloty - fragi: "+aircraftFrags);

                TextView RecordsTextView = findViewById(R.id.RecordsTextView);
                RecordsTextView.setText("Rekordy:");

                TextView maxFragsTextView = findViewById(R.id.maxFragsTextView);
                maxFragsTextView.setText("Rekord fragów:"+maxFrags);

                TextView maxDamageTextView = findViewById(R.id.maxDamageTextView);
                maxDamageTextView.setText("Rekord zadanych uszkodzeń: "+maxDamage);

                TextView maxPlanesKilledTextView = findViewById(R.id.maxPlanesKilledTextView);
                maxPlanesKilledTextView.setText("Rekord zniszczonych samolotów: "+maxPlanesKilled);

                TextView maxXpTextView = findViewById(R.id.maxXpTextView);
                maxXpTextView.setText("rekord zdobytego doświadczenia: "+maxXP);






            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);



    }

    public void sprawdz(View view) {
        EditText playerIdEditText = findViewById(R.id.idEditText);
        String playerId = playerIdEditText.getText().toString();

        DownloadTaskStats task = new DownloadTaskStats();
        task.execute("https://api.worldofwarships.eu/wows/account/info/?application_id=b0f4d669a9745f5dd7eb16395de839be&account_id="+playerId);

        ScrollView scrollView = findViewById(R.id.ScrollView);
       scrollView.setVisibility(view.VISIBLE);
    }
}