package com.example.downloader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DownloaderTest {

    @Test
    void testDownloadFile() {
        Downloader downloader = new Downloader();
        boolean result = downloader.downloadFile("https://example.com", "test.txt");
        assertTrue(result, "File should be downloaded successfully.");
    }
}
