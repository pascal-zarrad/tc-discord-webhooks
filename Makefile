# Makefile to control the local development environment
PWD=$(shell pwd)

.PHONY: cache_dir
cache_dir:
	mkdir -p m2

# Build the project
.PHONY: build
build: cache_dir
	docker run -it --rm -u 1000 -v "$(PWD):/srv/tcdwbuild:delegated" -v "$(PWD)/m2":/var/maven/.m2 -w /srv/tcdwbuild -e MAVEN_CONFIG=/var/maven/.m2 maven:3.6-amazoncorretto-8 mvn clean package

# Stop the local Docker stack, rebuild the project and start the local Docker stack again
.PHONY: redeploy cache_dir
redeploy:
	docker-compose stop
	docker run -it --rm -u 1000 -v "$(PWD):/srv/tcdwbuild:delegated" -v "$(PWD)/m2":/var/maven/.m2 -w /srv/tcdwbuild -e MAVEN_CONFIG=/var/maven/.m2 maven:3.6-amazoncorretto-8 mvn clean package
	docker-compose up -d

# Start the local development environment
.PHONY: up
up:
	docker-compose up -d

# Stop the local development environment
.PHONY: stop
stop:
	docker-compose stop

# Clear the local development environment
.PHONY: down
down:
	docker-compose down

# Rebuild the local development environment
.PHONY: rebuild_docker
rebuild_docker:
	docker-compose up -d --build --force-recreate

