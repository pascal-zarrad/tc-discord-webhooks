# TC Discord WebHooks [![Build Status](https://travis-ci.org/pascal-zarrad/tc-discord-webhooks.svg?branch=master)](https://travis-ci.org/pascal-zarrad/tc-discord-webhooks)

> **This plugin is in a community maintainance state**
> 
> I use GitHub Actions instead of TeamCity right now and don't have time to maintain or update this plugin by myself.
> I decided to discontinue the development of this plugin.
> As of 2021/12/06, this plugin still works and can be installed manually.
> 
> I won't provide any maintainance of the plugin by myself. If anyone wants to keep it up to date or contribute code (features, fixes, etc...), I'd be happy about that. Feel always free to do that if you want to.
> I will continue to manage contributions and publish new versions of the plugin. 

## About
A TeamCity plugin which allows the easy creation of Discord WebHooks to notify users on a Discord server about the current build status of projects.

**Features:**
 - Discord Integration to get notified on a Discord server
 - Simple setup
 - Beautiful messages out of the box

## System Requirements
To use this plugin you need at least TeamCity 2018.2 and Java 8.
In addition, a Discord server is necessary as you can't have webhooks without a server.

## Installation
Simply put the plugins ZIP file into the plugin's directory of your TeamCity server and restart it.
You should also be able to upload the plugin using the settings page of your TeamCity installation.
For further information refer to the official JetBrains documentation: [Installing additional plugins](https://www.jetbrains.com/help/teamcity/installing-additional-plugins.html)

## Usage
The usage of this plugin is simple.
Just create a webhook for one of your Discord channels as shown here: [Discord WebHook HowTo](https://support.discordapp.com/hc/en-us/articles/228383668-Intro-to-Webhooks).

Then enter the WebHook URL and a Username (recommended to also enter the name!) under "My Settings & Tools" -> "Notification Rules" -> "Discord WebHook".
Configure your rules as normal. You should now receive notifications on Discord!

If you use a proxy, ensure that `http.proxyHost` and `http.proxyPort` are set properly.

## Development on Linux

### Prerequisites
To develop and build TC Discord Webhooks on Linux you need the following tools:
 - Docker
 - docker-compose
 - make

Note that you might also just use the `mvn` command of your local Maven 2 installation to build the project.
But using the Makefile ensures that you have your environment configured properly.

Also ensure your local system is configured properly.
If you encounter issues while downloading Maven dependencies, you might check [this](https://stackoverflow.com/a/45644890)
StackOverflow comment that might help you to fix the issue.

**Use the following setup ONLY for development purposes! It is not production ready!**

### Building

Use the build target of the Makefile to build the project:
```
make build
```
To build the project without `make`, simply run the `package` goal of Maven.

### Running the TeamCity development server

Start the local TeamCity server:
```
make up
```

Stop the local TeamCity server:
```
make up
```

Delete the containers of the local TeamCity server:
```
make down
```

Rebuild the plugin and restart the TeamCity server:
```
make redeploy
```

Rebuild the local Docker stack:
```
make rebuild_docker
```

## Development on Windows

### Prerequisites
To develop and build TC Discord Webhooks on Linux you need the following tools:
 - Docker Desktop
 - Maven 2

**Use the following setup ONLY for development purposes! It is not production ready!**

### Building

To build the project without Docker, simply run the `package` goal of Maven:
```
mvn clean package
```

### Running the TeamCity development server

To set up the containers and start the Docker stack, take a look at the commands below.
After the containers have been started you can open TeamCity in your browser.
TeamCity should be reachable at `http://localhost:8080/`.

Start the local TeamCity server:
```
docker-compose up -d
```

Stop the local TeamCity server:
```
docker-compose stop
```

Remove the local TeamCity server containers:
```
docker-compose down
```
