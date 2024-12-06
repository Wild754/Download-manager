package main.java.com.example.downloader;

import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadDownloader {

    private static final int THREAD_COUNT = 4;

    public boolean downloadFile(String urlString, String outputFile) {
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            int fileSize = connection.getContentLength();

            if (fileSize <= 0) {
                System.err.println("Unable to retrieve file size.");
                return false;
            }

            System.out.println("File size: " + fileSize + " bytes");

            // Підготовка файлу для запису
            try (RandomAccessFile file = new RandomAccessFile(outputFile, "rw")) {
                file.setLength(fileSize);
            }

            // Розбиваємо завдання на потоки
            int partSize = fileSize / THREAD_COUNT;
            List<Thread> threads = new ArrayList<>();

            for (int i = 0; i < THREAD_COUNT; i++) {
                int start = i * partSize;
                int end = (i == THREAD_COUNT - 1) ? fileSize : start + partSize - 1;

                Thread thread = new Thread(new main.java.com.example.downloader.DownloadWorker(url, outputFile, start, end));
                threads.add(thread);
                thread.start();
            }

            // Очікуємо завершення всіх потоків
            for (Thread thread : threads) {
                thread.join();
            }

            System.out.println("File downloaded successfully: " + outputFile);
            return true;

        } catch (Exception e) {
            System.err.println("Error during download: " + e.getMessage());
            return false;
        }
    }
}

