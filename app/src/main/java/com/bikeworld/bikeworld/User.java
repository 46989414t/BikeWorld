package com.bikeworld.bikeworld;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by enric on 2/12/15.
 */
public class User {
    public String resulName = "";
    public String resulPassword = "";

    public String userName;
    public String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
        /*this.userName = userName;
        this.password = password;*/
    }

    public String getResulName() {
        peticio();
        return resulName;
    }

    public void setResulName(String resulName) {
        this.resulName = resulName;
    }

    public String getResulPassword() {
        return resulPassword;
    }

    public void setResulPassword(String resulPassword) {
        this.resulPassword = resulPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHTML(String urlToRead) throws Exception{
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();//retorna un JSON
    }

    public void peticio(){
        System.out.println("Entra a PETICIO");
        resulName = "hola ke ase";

        //pruebas con openWeather
        String api_key="4c3c9fd618e5d67bb93faf64733a3470";
        String peticio2 = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Barcelona&mode=json&units=metric&cnt=7&appid="+api_key;


        String s;
        //String api_key="4c3c9fd618e5d67bb93faf64733a3470";
        //String peticio2 = "https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user/text";
        //String peticio2 = "dazzling-inferno-4414.firebaseio.com";

        try {
            s = getHTML(peticio2);
            SJS(s);
            //SJC(s);
        } catch (Exception e) {
            e.getMessage();
            System.out.println("no existeix");
        }

    }

    public void SJS(String cadena) throws JSONException {
        System.out.println("pasa por SJS");

        JSONObject object = new JSONObject(cadena);
        //JSONObject results = object.getJSONObject("text");
        //resulName = object.getString("userName");
        resulPassword = object.getString("password");

        resulName = "hola ke ase2";

        System.out.println("NAME: "+resulName);
        System.out.println("PASSWORD: "+resulPassword);


        /*
        //---------------------------
        Object obj02 = JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;

        String out = arra02.toJSONString();

        System.out.println("**********************************************");
        System.out.println("llega a SJS");
        //System.out.println(out);
        //Ciutats
        JSONObject respostaCity = (JSONObject)arra02.get("city");
        System.out.println("Ciudad: "+respostaCity.get("name"));

        //COD
        //System.out.println("llega a cod");
        //System.out.println("COD: "+arra02.get("cod"));

        //message
        //System.out.println("MESSAGE: "+arra02.get("message"));

        //cnt
        System.out.println("Previsión de días: "+arra02.get("cnt"));
        //List
        //System.out.println("llega al lista");
        JSONArray respostaList = (JSONArray)arra02.get("list");
        //Extraer atributos del primer tag de list
        for (int i =0; i < respostaList.size(); i++){

            //System.out.println("llega al primer for");
            JSONObject listaJsonObj = (JSONObject)respostaList.get(i);
            //Extraer atributos del tag temp
            JSONObject temp = (JSONObject)listaJsonObj.get("temp");
            //minima = (String) temp.get("min");
            //System.out.println("DIA: "+(diaInt+i)+"/"+mes+"/"+annio);
            //System.out.println("Mínimas: "+temp.get("min"));
            //System.out.println("Máximas: "+temp.get("max")+"\n");
            System.out.println("vuelta numero: "+i);

            //String min = (String) temp.get("min");

            minimaArrayList.add(temp.get("min")+"\n");
            maximaArrayList.add(temp.get("max")+"\n");
            fechaArrayList.add((diaInt+i)+"/"+mes+"/"+annio+"\n");*/

        }

    }

