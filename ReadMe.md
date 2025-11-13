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
- **Parameters**: 
  - `browser` (String): Browser type - "chrome", "edge", "firefox" (defaults to Chrome if invalid)
- **Usage**: Initializes a new WebDriver browser instance with Healenium self-healing capabilities

### `navigateTo`
- **Description**: Navigate to a specific URL
- **Parameters**: 
  - `url` (String): The URL to navigate to
- **Usage**: Opens the specified URL in the current browser session

### `findElement`
- **Description**: Find a single web element using various locator strategies
- **Parameters**: 
  - `type` (String): Locator type - "id", "classname", "tagname", "name", "link text", "partial link text", "css selector", "xpath"
  - `value` (String): The locator value
- **Usage**: Locates and verifies the presence of a web element, returns "Element Found" if successful

### `findElements`
- **Description**: Find multiple web elements using various locator strategies
- **Parameters**: 
  - `type` (String): Locator type - "id", "classname", "tagname", "name", "link text", "partial link text", "css selector", "xpath"
  - `value` (String): The locator value
- **Usage**: Locates multiple elements matching the criteria, returns "All Elements Found" if successful

### `clickElement`
- **Description**: Click on a single web element
- **Parameters**: 
  - `type` (String): Locator type - "id", "classname", "tagname", "name", "link text", "partial link text", "css selector", "xpath"
  - `value` (String): The locator value
- **Usage**: Waits for element to be clickable and performs click action, returns "Element Clicked" if successful

### `clickElements`
- **Description**: Click on multiple web elements
- **Parameters**: 
  - `type` (String): Locator type - "id", "classname", "tagname", "name", "link text", "partial link text", "css selector", "xpath"
  - `value` (String): The locator value
- **Usage**: Finds all matching elements and clicks each one, returns "All Elements Clicked" if successful

### `hoverOverElement`
- **Description**: Hover the mouse pointer over a single web element
- **Parameters**: 
  - `type` (String): Locator type - "id", "classname", "tagname", "name", "link text", "partial link text", "css selector", "xpath"
  - `value` (String): The locator value
- **Usage**: Moves the mouse cursor to the specified element, useful for triggering hover effects and dropdown menus, returns "Successfully Hovered" if successful

### `hoverOverAll`
- **Description**: Hover the mouse pointer over multiple web elements sequentially
- **Parameters**: 
  - `type` (String): Locator type - "id", "classname", "tagname", "name", "link text", "partial link text", "css selector", "xpath"
  - `value` (String): The locator value
- **Usage**: Sequentially moves the mouse cursor over all matching elements, returns "Successfully Hovered Over All" if successful

### `sendKeysToElement`
- **Description**: Send keyboard input (text) to a web element
- **Parameters**: 
  - `type` (String): Locator type - "id", "classname", "tagname", "name", "link text", "partial link text", "css selector", "xpath"
  - `value` (String): The locator value
  - `keys` (String): The text/keys to send to the element
- **Usage**: Locates an input element and sends the specified text to it, commonly used for filling forms and text fields, returns "Keys Sent Successfully" if successful

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

<!--
/**
 * Healenium Web Server Configuration
 * 
 * This section describes the setup process for the Healenium web server component,
 * which is a critical dependency for the self-healing functionality of this MCP server.
 * 
 * The Healenium web server acts as a backend service that:
 * - Stores healing data and learning algorithms
 * - Provides REST APIs for healing operations
 * - Manages element locator mappings and suggestions
 * - Offers a web dashboard for monitoring healing activities
 * 
 * @requires Port 8000 must be available on localhost
 * @requires Docker (if using containerized setup)
 * @requires Shell script execution permissions
 * 
 * @critical Without this server running, self-healing capabilities will be disabled
 *          and the MCP server may throw connection errors during browser automation
 */
-->

**Important**: This MCP server requires the Healenium web application to be running locally on port 8000 for proper self-healing functionality.

To set up Healenium web server:

1. **Using Shell Script (Required)**:
```bash
# Execute the Healenium startup script
# This script handles Docker container setup and service initialization
sh start_healenium.sh
```

2. **Verify Setup**:
   - Open `http://localhost:8000` in your browser
   - You should see the Healenium dashboard
   - The script should start the Healenium web server on port 8000
   - Check the dashboard shows "Service Status: Running"

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

<!--
/**
 * System Architecture Overview
 * 
 * This diagram illustrates the interaction flow between different components:
 * 
 * @component AI Agent - External agent that consumes MCP tools via JSON-RPC
 * @component MCP Server - Spring Boot application exposing web automation tools
 * @component HealeniumToolService - Service layer containing @Tool annotated methods
 * @component SelfHealingDriver - Wrapper around Selenium WebDriver with healing capabilities
 * @component Healenium Web Server - Backend service for healing logic and data storage
 * @component Web Browser - Target browser instances (Chrome, Firefox, Edge)
 * 
 * @flow 1. AI Agent calls MCP tool methods via JSON-RPC protocol
 * @flow 2. MCP Server routes calls to HealeniumToolService methods
 * @flow 3. Service methods use SelfHealingDriver for browser automation
 * @flow 4. When locators fail, SelfHealingDriver queries Healenium Web Server
 * @flow 5. Healenium Web Server provides alternative locators using ML algorithms
 * @flow 6. Browser actions are executed with healed locators
 */
-->

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────────┐
│   AI Agent      │◄──►│  MCP Server      │◄──►│ HealeniumToolService│
│   (External)    │    │  (Spring Boot)   │    │   (@Tool methods)   │
└─────────────────┘    └──────────────────┘    └─────────────────────┘
                                                         │
                                                         ▼
                       ┌─────────────────────────────────────────────┐
                       │           SelfHealingDriver                 │
                       │        (Selenium + Healing Logic)          │
                       └─────────────────────────────────────────────┘
                                         │                │
                                         ▼                ▼
                       ┌─────────────────────┐  ┌─────────────────┐
                       │ Healenium Web Server│  │   Web Browser   │
                       │   (Port 8000)       │  │ Chrome/Edge/FF  │
                       └─────────────────────┘  └─────────────────┘
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
@Tool(description = "Send text to a web element")
public String sendText(String type, String value, String text) {
    try {
        WebElement element = locateElement(type, value);
        element.clear();
        element.sendKeys(text);
        return "Text sent to element";
    } catch (Exception e) {
        throw new RuntimeException("Failed to send text: " + e.getMessage());
    }
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
   - For Chrome: Ensure Chrome browser is installed and up to date

2. **Port already in use**
   - Check if another instance is running on port 8080 (MCP Server)
   - Check if Healenium web server is running on port 8000
   - Configure a different port in `application.properties`

3. **Healenium healing not working**
   - Verify Healenium web server is running: `http://localhost:8000`
   - Check `healenium.properties` configuration
   - Ensure proper element locators are used
   - Review healing logs in the Healenium dashboard

4. **NoSuchElementException errors**
   - Verify element locators are correct (id, classname, xpath, etc.)
   - Check if elements are loaded before interaction
   - Ensure WebDriverWait timeout (20 seconds) is sufficient

5. **Browser startup failures**
   - Verify browser parameter is correct ("chrome", "edge", "firefox")
   - Ensure browser drivers are compatible with installed browser versions
   - Check system PATH for browser executables

6. **MCP connection issues**
   - Verify Spring Boot application is running on expected port
   - Check application logs for startup errors
   - Ensure Java 21 is installed and configured correctly

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
