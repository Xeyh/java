package com.example.demo;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    public static class weatherStats
    {
        String date;
        String temp;
        String status;

        public weatherStats(String date, String temp, String status)
        {
            this.date = date;
            this.temp = temp;
            this.status = status;
        }
    }

    public static weatherStats sendRequest() throws Exception {
        weatherStats stats = null;

        URL url = new URL("http://api.weatherapi.com/v1/current.json?key=ba3d489a11a74225bfd200902242705&q=Poznan&aqi=yes");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        String inline = "";
        Scanner scanner = new Scanner(url.openStream());
        inline += scanner.nextLine();
        scanner.close();
        JSONObject data_obj = new JSONObject(inline);
        stats = new weatherStats(data_obj.getJSONObject("current").get(
                "last_updated").toString(),
                data_obj.getJSONObject("current").get("temp_c").toString(),
                data_obj.getJSONObject("current").getJSONObject("condition").get(
                        "text").toString());

        return stats;
    }

}
