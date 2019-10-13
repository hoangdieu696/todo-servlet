# Todo web app 

### Description
- Implements simple web app with Maven, Servlet, MySQL
- Current version: 1.x

### Common commands

- Creating a project
```sh
mvn archetype:generate 
	-DgroupId=devops
	-DartifactId=webapp
	-DarchetypeArtifactId=maven-archetype-webapp
	-DinteractiveMode=false
```

- Build the project
```sh
mvn package # take the compiled code and package it in its distributable format, such as a JAR.

mvn verify # run any checks to verify the package is valid and meets quality criteria

mvn install # install the package into the local repository, for use as a dependency in other projects locally

mvn clean # cleans up artifacts created by prior builds

mvn site # generates site documentation for this project

# multiple phases at once
mvn clean install site
```