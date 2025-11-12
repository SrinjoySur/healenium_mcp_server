package com.healenium_mcp.healenium_mcp_server;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HealeniumMcpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealeniumMcpServerApplication.class, args);
    }

    @Bean
    @SuppressWarnings("unused")
    public ToolCallbackProvider healeniumTools(HealeniumToolService healeniumToolService) {
        return MethodToolCallbackProvider.builder().toolObjects(healeniumToolService).build();
    }

}
