# TeamCity Discord WebHooks [![Build Status](https://travis-ci.com/PlayerForceHD/tc-discord-webhooks.svg?token=gjN24nudi96G8T4qLD5E&branch=master)](https://travis-ci.com/PlayerForceHD/tc-discord-webhooks)

A TeamCity plugin which allows an easy creation of Discord WebHooks to notify users on a Discord server about the current build status of projects.

**Features:**
 - Discord Integration to get notified on a Discord server
 - Simple setup
 - Beautiful messages out of the box, while being still customizable
 - Per project configuration and global configuration

### Installation
Simply put the plugins ZIP file into the plugins directory of your TeamCity server and restart it, 
or upload the plugin using the settings page of your TeamCity installation.

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
 - Docker compose
 
The development setup is composed using the following software:
 - PostgreSQL
 - TeamCity
 - TeamCity Agent
 
To setup the containers and start them take a look at the commands below.
After the container have been started you can open TeamCity in your browser.
The setup will ask you to connect to a database. There you can continue with the included 
database or connect to the PostgreSQL database.

Login data for PostgreSQL:
```
Host: <container-ip>:5432
User: teamcity
Paswword: teamcity
Database: teamcity
```
 
Setup:
```docker-compose build```

Start:
```docker-compose up -d```

Stop:
```docker-compose stop```

Finding the address of a container:
```docker inspect <container-name>``` 