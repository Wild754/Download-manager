package main.java.com.example.downloader;

import java.net.URL;

public class Utils {

    public static boolean isValidURL(String urlString) {
        try {
            new URL(urlString).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

