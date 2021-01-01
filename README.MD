# TC Discord WebHooks [![Build Status](https://travis-ci.org/pascal-zarrad/tc-discord-webhooks.svg?branch=master)](https://travis-ci.org/pascal-zarrad/tc-discord-webhooks)

A TeamCity plugin which allows the easy creation of Discord WebHooks to notify users on a Discord server about the current build status of projects.

**Features:**
 - Discord Integration to get notified on a Discord server
 - Simple setup
 - Beautiful messages out of the box

### System Requirements
To use this plugin you need at least TeamCity 2018.2 and Java 8.

### Installation
Simply put the plugins ZIP file into the plugins directory of your TeamCity server and restart it.
 
You should also be able to upload the plugin using the settings page of your TeamCity installation.

### Usage

The usage of this plugin is simple. 
Just create a WebHook for one of your Discord channels as shown here: [Discord WebHook HowTo](https://support.discordapp.com/hc/en-us/articles/228383668-Intro-to-Webhooks).

Then enter the WebHook URL and a Username (recommended to also enter the name!) under "My Settings & Tools" -> "Notification Rules" -> "Discord WebHook".
Configure your rules as normal. You should now receive notifications on Discord!

## Development

### Building
The plugin is written in Java and uses Maven. So, you need the JDK and Maven to build the project.

The project can be build using the following command: ```mvn clean package```

The installation of the build artifact is similar as described under [Installation](#Installation)

### Setting up a TeamCity Server for development
To simplify the development on the plugin, this project is bundled with a docker-compose.yml that provides
everything that is required to get started. 

**Use the following setup ONLY for development purposes! It is not production ready!**

You need:
 - A Docker compatible system
 - Docker
 - Docker-Compose
 
The development setup is composed using the following software:
 - TeamCity
 - TeamCity Agent
 
To setup the containers and start them take a look at the commands below.
After the containers have been started you can open TeamCity in your browser.

Setup:
```docker-compose build```

Start:
```docker-compose up -d```

Stop:
```docker-compose stop```

Finding the address of a container:
```docker inspect <container-name>``` 
