package com.example.demo;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class NBP {
    public static class exRate{
        private final String symbol;
        private final double mid;
        private final String date;

        public exRate(String symbol, double mid, String date) {this.symbol = symbol;this.mid = mid;this.date = date;}

        public String getSymbol() {return symbol;}
        public Double getMid() {return mid;}
        public String getDate() {return date;}
    }

    public static ArrayList<exRate> getExRates(String symbol) {
        ArrayList<exRate> list = new ArrayList<>();
        File f = new File(symbol + ".txt");
        if (f.exists() && !f.isDirectory()) {
            try {
                Scanner scanner = new Scanner(f);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] split = line.split(",");
                    list.add(new exRate(split[0], Double.parseDouble(split[1]), split[2]));
                }
                scanner.close();
            } catch (Exception e) {
                return null;
            }
        }
        return list;
    }

    public static IOException addExRate(exRate exRate) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(exRate.getSymbol() + ".txt"));
            writer.write(exRate.getSymbol() + "," + exRate.getMid() + "," + exRate.getDate() + "," + "\n");
            writer.close();
            return null;
        } catch (IOException e) {
            return e;
        }
    }

    public static exRate sendRequest(String symbol, String date)
    {
        double mid = 0.0;
        try {
            URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/a/" + symbol + "/" + date);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            String inline = "";
            Scanner scanner = new Scanner(url.openStream());
            inline += scanner.nextLine();
            scanner.close();
            JSONObject data_obj = new JSONObject(inline);
            mid = Double.parseDouble(data_obj.getJSONArray("rates").getJSONObject(0).get("mid").toString());
        } catch (Exception e) {
            return null;
        }
        return new exRate(symbol, mid, date);
    }
}
