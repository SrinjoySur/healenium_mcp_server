# Healenium MCP Server

A Spring Boot-based Model Context Protocol (MCP) server that provides web automation capabilities using Healenium's self-healing Selenium framework.

## Overview

This project integrates Healenium with MCP to provide AI agents with robust web automation capabilities. Healenium adds self-healing functionality to Selenium WebDriver, automatically fixing broken locators when web elements change.

## Features

- **Self-Healing Web Automation**: Automatically adapts to UI changes using Healenium
- **MCP Integration**: Exposes web automation tools through Model Context Protocol
- **Spring Boot Architecture**: Built on Spring Boot 3.5.7 with Spring AI MCP support
- **WebDriver Support**: Uses Selenium WebDriver interface for web automation

## Available Tools

### `startBrowser`
- **Description**: Starts a new browser session
- **Parameters**: None
- **Usage**: Initializes a new WebDriver browser instance with Healenium self-healing capabilities

### `navigateTo`
- **Description**: Navigate to a specific URL
- **Parameters**: 
  - `url` (String): The URL to navigate to
- **Usage**: Opens the specified URL in the current browser session

### `closeBrowser`
- **Description**: Closes all browser sessions
- **Parameters**: None
- **Usage**: Terminates the current browser session and cleans up resources

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- Web browser (Chrome, Firefox, etc.)
- WebDriver (automatically managed by Selenium)
- **Healenium Web Server**: Must be running locally on port 8000

### Healenium Web Server Setup

**Important**: This MCP server requires the Healenium web application to be running locally on port 8000 for proper self-healing functionality.

To set up Healenium web server:

1. **Using Shell Script (Required)**:
```bash
sh start_healenium.sh
```

2. **Verify Setup**:
   - Open `http://localhost:8000` in your browser
   - You should see the Healenium dashboard
   - The script should start the Healenium web server on port 8000

**Note**: Make sure the `start_healenium.sh` script is executable and available in your system path or current directory.

**Note**: Without the Healenium web server running, the self-healing capabilities will not function, and the MCP server may encounter errors during browser automation tasks.

## Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd healenium_mcp_server
```

2. Install dependencies:
```bash
./mvnw clean install
```

## Configuration

### Application Properties
The server can be configured through `src/main/resources/application.properties`.

### Healenium Properties
Healenium-specific configurations are in `src/main/resources/healenium.properties`.

## Running the Server

### Development Mode
```bash
mvn spring-boot:run
```

### Production Build
```bash
mvn clean package
java -jar target/healenium_mcp_server-0.0.1-SNAPSHOT.jar
```

## Usage with AI Agents

This MCP server can be integrated with AI agents that support the Model Context Protocol. The server exposes web automation tools that agents can use to:

- Start and manage browser sessions
- Navigate to web pages
- Perform web interactions with self-healing capabilities

## Dependencies

### Core Dependencies
- **Spring Boot 3.5.7**: Application framework
- **Spring AI 1.0.3**: MCP server support
- **Healenium Web 3.5.7**: Self-healing Selenium wrapper
- **Selenium**: Web automation framework

### Key Features of Healenium Integration
- **Automatic Locator Healing**: When elements change, Healenium finds alternative locators
- **Machine Learning**: Uses ML algorithms to suggest the best healing strategies
- **Reporting**: Provides detailed reports on healing actions
- **Backwards Compatibility**: Works with existing Selenium tests

## Architecture

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   AI Agent      │◄──►│  MCP Server      │◄──►│  Healenium      │
│                 │    │  (Spring Boot)   │    │  + Selenium     │
└─────────────────┘    └──────────────────┘    └─────────────────┘
                                │
                                ▼
                       ┌─────────────────┐
                       │   Web Browser   │
                       └─────────────────┘
```

## Development

### Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/healenium_mcp/healenium_mcp_server/
│   │       ├── HealeniumMcpServerApplication.java  # Main application
│   │       └── HealeniumToolService.java           # MCP tools
│   └── resources/
│       ├── application.properties                  # App config
│       └── healenium.properties                   # Healenium config
└── test/
    └── java/
        └── com/healenium_mcp/healenium_mcp_server/
            └── HealeniumMcpServerApplicationTests.java
```

### Adding New Tools

To add new web automation tools:

1. Add a method to `HealeniumToolService.java`
2. Annotate it with `@Tool(description = "...")`
3. The method will automatically be exposed as an MCP tool

Example:
```java
@Tool(description = "Click on an element by CSS selector")
public void clickElement(String cssSelector) {
    driver.findElement(By.cssSelector(cssSelector)).click();
}
```

## Testing

Run tests with:
```bash
mvn test
```

## Troubleshooting

### Common Issues

1. **WebDriver not found**
   - Ensure a compatible web browser is installed
   - WebDriver is automatically managed by Selenium

2. **Port already in use**
   - Check if another instance is running
   - Configure a different port in `application.properties`

3. **Healenium healing not working**
   - Check `healenium.properties` configuration
   - Ensure proper element locators are used

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

[Add your license information here]

## Support

For issues related to:
- **Healenium**: Visit [Healenium Documentation](https://healenium.io/)
- **Spring AI MCP**: Check [Spring AI Documentation](https://docs.spring.io/spring-ai/reference/)
- **This project**: Create an issue in this repository

## Version History

- **0.0.1-SNAPSHOT**: Initial version with basic browser automation tools
