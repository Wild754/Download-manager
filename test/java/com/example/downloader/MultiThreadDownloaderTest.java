package com.example.downloader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiThreadDownloaderTest {

    @Test
    void testMultiThreadDownload() {
        MultiThreadDownloader downloader = new MultiThreadDownloader();
        boolean result = downloader.downloadFile("https://example.com/largefile.zip", "largefile.zip");
        assertTrue(result, "File should be downloaded successfully using multiple threads.");
    }
}
