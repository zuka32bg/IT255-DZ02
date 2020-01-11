/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it225.dz02;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Uros Zukancic
 */
public class IT225DZ02 {

    public static void main(String[] args) {
        get("225");
        posta();
    }

    public static void get(String id) {
        try {
            URL url = new URL("http://89.216.56.107:8080/restfull/rest/users/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Prihvati", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Greška : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void posta() {
        
        
        User korisnik = new User();
        korisnik.setEmailConfirmed(true);
        korisnik.setEmailConfirmationCode("3496");
        korisnik.setEmail("zuka32bg@gmail.com");
        korisnik.setPassword("TokioBAri1991CZVbgd");
        korisnik.setFullName("Uros Zukancic");
        

        try {
            URL url = new URL("http://89.216.56.107:8080/restfull/rest/users");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Method", "POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            pw.print(new Gson().toJson(korisnik));

            pw.close();
            pw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
