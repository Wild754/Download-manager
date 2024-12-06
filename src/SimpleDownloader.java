import main.java.com.example.downloader.Downloader;
import main.java.com.example.downloader.MultiThreadDownloader;

public class SimpleDownloader {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java SimpleDownloader <single|multi> <URL> <output-file>");
            return;
        }

        String mode = args[0];
        String url = args[1];
        String outputFile = args[2];

        if ("single".equalsIgnoreCase(mode)) {
            Downloader downloader = new Downloader();
            downloader.downloadFile(url, outputFile);
        } else if ("multi".equalsIgnoreCase(mode)) {
            MultiThreadDownloader multiDownloader = new MultiThreadDownloader();
            multiDownloader.downloadFile(url, outputFile);
        } else {
            System.out.println("Invalid mode. Use 'single' or 'multi'.");
        }
    }
}
