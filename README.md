# MCBotLauncher

![MCBotLauncher](https://img.shields.io/badge/version-1.0-blue.svg)
![License](https://img.shields.io/badge/license-MIT-green.svg)
![Platform](https://img.shields.io/badge/platform-Windows-yellow.svg)
![Java](https://img.shields.io/badge/java-11%2B-red.svg)

MCBotLauncher is a sophisticated GUI application for launching Minecraft bots with various attack methods. This tool is bundled with its own JRE, making it independent of any Java installation on your system.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
  - [Example](#example)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [Disclaimer](#disclaimer)
- [Acknowledgements](#acknowledgements)

## Features

- **Bundled JRE**: Runs without requiring a pre-installed Java environment.
- **Diverse Attack Methods**: Multiple bot attack methods to choose from.
- **Protocol Support**: Supports a variety of Minecraft protocols.
- **User-Friendly GUI**: Modern, sleek, black-themed interface.
- **Easy Control**: Start and stop the bot attack with a single click.
- **Proxy Support**: Includes proxy support for more complex operations.

## Requirements

- **Operating System**: Windows 7/8/10/11
- **RAM**: Minimum 2GB, recommended 4GB
- **Disk Space**: At least 100MB of free space

## Installation

1. **Download the Executable**: Get the latest release from the [Releases]([https://github.com/R-Samir-Bhuiyan-A/mcbot.exe/releases) page.
2. **Extract the Files**: Unzip the downloaded file to a directory of your choice.


## Usage

1. **Launch the Application**: Double-click `MCBot.exe` to open the application.
2. **Configure Settings**:
    - **Server Details**: Enter the target server's `IP:PORT`.
    - **Minecraft Protocol**: Select the appropriate protocol version.
    - **Bot Method**: Choose the bot attack method from the dropdown.
    - **Duration**: Set the attack duration in seconds.
    - **CPS**: Configure the clicks per second.
3. **Start the Bot**: Click the `Run` button to initiate the attack.
4. **Stop the Bot**: Click the `Stop` button to terminate the attack. This emulates a Ctrl+C command.



### Disclaimer

**Disclaimer:** This tool is intended for educational purposes only. It is designed to be used by server administrators to test the robustness and security of their own servers. Any unauthorized use of this tool on servers you do not own or have permission to test is strictly prohibited. The author does not consent to any malicious use of this tool and is not responsible for any damages caused by improper use.


### Acknowledgements
- **Launch4j** for wrapping Java applications into Windows native executables.
- **Minecraft** for the game platform.





### Example

```plaintext
IP:PORT: 127.0.0.1:25565
Protocol: 754 (1.16.5)
Method: botjoiner
Seconds: 60
CPS: 10


