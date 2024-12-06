package main.java.com.example.downloader;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

public class DownloadWorker implements Runnable {

    private final URL url;
    private final String outputFile;
    private final int startByte;
    private final int endByte;

    public DownloadWorker(URL url, String outputFile, int startByte, int endByte) {
        this.url = url;
        this.outputFile = outputFile;
        this.startByte = startByte;
        this.endByte = endByte;
    }

    @Override
    public void run() {
        try {
            URLConnection connection = url.openConnection();
            String rangeHeader = "bytes=" + startByte + "-" + endByte;
            connection.setRequestProperty("Range", rangeHeader);

            try (InputStream inputStream = connection.getInputStream();
                 RandomAccessFile file = new RandomAccessFile(outputFile, "rw")) {

                file.seek(startByte);
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    file.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("Thread completed: " + startByte + " to " + endByte);

        } catch (Exception e) {
            System.err.println("Error in thread (" + startByte + " to " + endByte + "): " + e.getMessage());
        }
    }
}

