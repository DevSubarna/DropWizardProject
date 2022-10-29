# DropWizardProject
Project for Assessment Mroads

### Run Application
#### Initial Setup

Edit Configuration with program argument src/main/resources/dropwizardmysql.yml
Here dropwizardmysql.yml is the configuration file for database

### REST API

#### PATH: localhost:8080/app/12.120.0.11
#### HTTP Method: GET
#### RESPONSE: 
    Try check in Cache first if not found then try to get from database if not found then request to http://ip-api.com/json/12.120.0.11 
    and then save  into database the result.

### Dropwizard
    JDBI3
    Client
    dropwizard-core
    dropwizard-testing
    dropwizard-jackson
    junit-jupiter
    assertj-core
    mockito-core
    lombok

### Table Name: geolocation

#### Table Schema:

##### Columns:
    id varchar(100)
    query varchar(100) PK
    status varchar(45)
    country varchar(100)
    region varchar(100)
    regionName varchar(45)
    countryCode varchar(45)
    city varchar(45)
    zip varchar(45)
    lat varchar(45)
    lon varchar(45)
    timezone varchar(45)
    isp varchar(45)
    org varchar(45)
    asset varchar(45)