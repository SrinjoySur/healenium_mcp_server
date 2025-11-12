package com.healenium_mcp.healenium_mcp_server;

import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class HealeniumToolService {
    private final ThreadLocal<SelfHealingDriver> driver = new ThreadLocal<>();

    @Tool(description = "Starts A Browser Session")
    @SuppressWarnings("unused")
    public void startBrowser() {
        if (driver.get() == null) {
            WebDriver webDriver = null;
            Exception lastException = null;

            // Try Chrome first
            try {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--remote-allow-origins=*");
                webDriver = new ChromeDriver(chromeOptions);
            } catch (Exception e) {
                lastException = e;
                System.out.println("Chrome not available, trying Edge: " + e.getMessage());
            }

            // If Chrome failed, try Edge
            if (webDriver == null) {
                try {
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--no-sandbox");
                    edgeOptions.addArguments("--disable-dev-shm-usage");
                    edgeOptions.addArguments("--disable-gpu");
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    webDriver = new EdgeDriver(edgeOptions);
                } catch (Exception e) {
                    lastException = e;
                    System.out.println("Edge not available: " + e.getMessage());
                }
            }

            if (webDriver != null) {
                this.driver.set(SelfHealingDriver.create(webDriver));
            } else {
                throw new RuntimeException("Failed to initialize any WebDriver (tried Chrome and Edge): " +
                    (lastException != null ? lastException.getMessage() : "No browsers available"));
            }
        }
    }

    @Tool(description = "Closes All Browser Sessions")
    @SuppressWarnings("unused")
    public void closeBrowser() {
        SelfHealingDriver d = driver.get();
        if (d != null) {
            try {
                d.quit();
            } catch (Exception e) {
                // Log the error but continue with cleanup
                System.err.println("Error closing WebDriver: " + e.getMessage());
            } finally {
                driver.remove();
            }
        }
    }

    @SuppressWarnings("unused")
    public SelfHealingDriver getDriver() {
        return driver.get();
    }

    @PreDestroy
    @SuppressWarnings("unused")
    public void cleanup() {
        closeBrowser();
    }
}
