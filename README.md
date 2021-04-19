# NHCR - EMR Mediator

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e1c4d7781b074e7a8791a4fcf7b3130c)](https://app.codacy.com/gh/SoftmedTanzania/emr-mediator-nhcr?utm_source=github.com&utm_medium=referral&utm_content=SoftmedTanzania/emr-mediator-nhcr&utm_campaign=Badge_Grade_Settings)
[![Java CI Badge](https://github.com/SoftmedTanzania/emr-mediator-nhcr/workflows/Java%20CI%20with%20Maven/badge.svg)](https://github.com/SoftmedTanzania/emr-mediator-nhcr/actions?query=workflow%3A%22Java+CI+with+Maven%22)
[![Coverage Status](https://coveralls.io/repos/github/SoftmedTanzania/emr-mediator-nhcr/badge.svg?branch=development)](https://coveralls.io/github/SoftmedTanzania/emr-mediator-nhcr?branch=development)

An [OpenHIM](http://openhim.org/) mediator for handling system integrations between National Health Client Registry (NHCR) and EMRs.

## 1. Dev Requirements

1. Java 1.8
2. IntelliJ or Visual Studio Code
3. Maven 3.6.3

## 2. Pre-Deployment

**The file contents of `mediator.properties` and `mediator-registration-info.json` must be changed before deployment**

This idea behind this mediator is to deploy the same JAR with a different set of configurations parameters based on the
system to connect.

### 3 Configuration Parameters

The configuration parameters specific to the mediator and destination system can be found at

`src/main/resources/mediator.properties`

```
    # Mediator Properties
    mediator.name=EMR-Mediator-NHCR
    mediator.host=localhost
    mediator.port=3026
    mediator.timeout=60000
    mediator.heartbeats=true

    core.host=localhost
    core.api.port=8080
    core.api.user=root@openhim.org
    core.api.password=openhim-password
```

The configuration parameters specific to the mediator and the mediator's metadata can be found at

`src/main/resources/mediator-registration-info.json`

```
    {
        "urn": "urn:uuid:4ef307a0-a114-11eb-83c5-cd3414c149f3",
        "version": "0.1.0",
        "name": "EMR Mediator NHCR",
        "description": "An OpenHIM mediator to handle data sharing between NHCR and EMR",
        "endpoints": [
            {   
                "name": "EMR Mediator NHCR Route",
                "host": "localhost",
                "port": "3026",
                "path": "/emr",
                "type": "http"
            }
        ],
        "defaultChannelConfig": [
            {
                "name": "EMR Mediator NHCR",
                "urlPattern": "^/emr$",
                "type": "http",
                "allow": ["emrmediatornhcr"],
                "routes": [
                    {
                        "name": "EMR Mediator NHCR Route",
                        "host": "localhost",
                        "port": "3026",
                        "path": "/emr",
                        "type": "http",
                        "primary": "true"
                    }
                ]
            }
        ]
    }
```

## 4. Deployment

To build and run the mediator after performing the above configurations, run the following

```
  mvn clean package -DskipTests=true -e source:jar javadoc:jar
  java -jar target/emr-mediator-nhcr-<version>-jar-with-dependencies.jar
```
