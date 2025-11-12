package com.healenium_mcp.healenium_mcp_server;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class HealeniumToolService {
    private SelfHealingDriver driver;

    @Tool(description = "Starts A Browser Session")
    @SuppressWarnings("unused")
    public void startBrowser() {
        WebDriver webDriver=new ChromeDriver();
       this.driver= SelfHealingDriver.create(webDriver);
    }

    @Tool(description = "Closes All Browser Sessions")
    @SuppressWarnings("unused")
    public void closeBrowser() {
        driver.quit();
    }
}
