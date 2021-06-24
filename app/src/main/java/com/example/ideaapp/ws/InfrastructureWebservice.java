package com.example.ideaapp.ws;

import android.util.Log;

import com.example.ideaapp.model.Appuser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InfrastructureWebservice {



    private static final String URL = "http://10.0.2.2:18080/RestIdeenApp0806/rest/idea";


    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();

    private java.net.URL url;
    private URLConnection connection;
    private HttpURLConnection httpConnection;

    private OkHttpClient client = new OkHttpClient();

    private String urlString;

    public Appuser getUser(int id) throws NoSuchRowException {
        urlString = URL + "/appuser/member1";
        Log.e("test", urlString);
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        System.out.println(request.toString());
        try {
            System.out.println("Jetzt im Try");
            Response response = client.newCall(request).execute();
            System.out.println(response.toString());
            String output;
            Appuser user = null;
            if ((output = response.body().string()) != null) {
                System.out.println(output);
                user = gson.fromJson(output, Appuser.class);
                // zugegebene Vergewaltigung der JsonSyntaxException hinsichtlich
                // NoSuchRowException ...
            }
            return user;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        }
        return null;
    }

    public void createAppuser(Appuser appuser) throws IllegalCreateException {
        urlString = URL + "/appuser/save";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                gson.toJson(appuser));
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseString = response.body().string();
            if (responseString.compareTo("{\"status\":\"success\"}") != 0)
                throw new IllegalCreateException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
    public int getCountRooms() {
        urlString = URL + "/count";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return Integer.parseInt(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Collection<Room> getRooms() {
        urlString = URL + "/rooms";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Room[] rooms = null;
            if ((output = response.body().string()) != null)
                rooms = gson.fromJson(output, Room[].class);
            Collection<Room> allRooms = new ArrayList<Room>();
            for (int i = 0; i < rooms.length; i++)
                allRooms.add(rooms[i]);
            return allRooms;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Building> getBuildings() {
        urlString = URL + "/buildings";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Building[] buildings = null;
            if ((output = response.body().string()) != null)
                buildings = gson.fromJson(output, Building[].class);
            Collection<Building> allBuildings = new ArrayList<Building>();
            for (int i = 0; i < buildings.length; i++)
                allBuildings.add(buildings[i]);
            return allBuildings;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Room getRoom(long id) throws NoSuchRowException {
        urlString = URL + "/rooms/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Room room = null;
            if ((output = response.body().string()) != null) {
                room = gson.fromJson(output, Room.class);
                // zugegebene Vergewaltigung der JsonSyntaxException hinsichtlich
                // NoSuchRowException ...
            }
            return room;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        }
        return null;
    }

    public Building getBuilding(long id) throws NoSuchRowException {
        urlString = URL + "/buildings/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Building building = null;
            if ((output = response.body().string()) != null) {
                building = gson.fromJson(output, Building.class);
                // zugegebene Vergewaltigung der JsonException hinsichtlich
                // NoSuchRowException ...
            }
            return building;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        }
        return null;
    }

    public void removeBuilding(long id) throws NoSuchRowException {
        urlString = URL + "/buildings/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .delete()
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            if (responseString.compareTo("{\"status\":\"success\"}") != 0)
                throw new NoSuchRowException();
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        }
    }

    public void createBuilding(Building building) throws IllegalCreateException {
        urlString = URL + "/buildings/";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                gson.toJson(building));
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseString = response.body().string();
            if (responseString.compareTo("{\"status\":\"success\"}") != 0)
                throw new IllegalCreateException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}

