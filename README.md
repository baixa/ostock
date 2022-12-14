
<h1 align="center">
  <br>
  <img src="https://cdn-icons-png.flaticon.com/512/4256/4256900.png" alt="Optima Growth" width="200">
  <br>
  Optima Stock
  <br>
</h1>

<h4 align="center">Optima Growth Asset Management Web Resource</h4>

<p align="center">
  <a href="#key-features">Key Features</a> •
  <a href="#services">Services</a> •
  <a href="#languages-and-tools">Languages and Tools</a> •
  <a href="#how-to-use">How To Use</a>
</p>

## Key Features

* Asset Management
* Inventory
* Software supply
* License management

## Services

1. A **Spring Cloud Config server** that is deployed as Docker container and can manage a services' configuration information using a file system/ classpath or GitHub-based repository.
2. A **Eureka server** running as a Spring-Cloud based service. This service will allow multiple service instances to register with it. Clients that need to call a service will use Eureka to look up the physical location of the target service.
3. An **Organization service** that will manage organization data used within Ostock.
4. A **Licensing service** that will manage licensing data used within Ostock.
5. A **Postgres SQL Database** used to hold the data.

## Languages and Tools

The main structure is based on Java (Spring Cloud)

<div>
  <img src="https://github.com/devicons/devicon/blob/master/icons/java/java-original-wordmark.svg" title="Java" alt="Java" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/spring/spring-original-wordmark.svg" title="Spring" alt="Spring" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/postgresql/postgresql-original.svg" title="PostgreSQL"  alt="PostgreSQL" width="40" height="40"/>&nbsp;
</div>

## Required tools

 * [Git](https://git-scm.com)
 * [Java 17+](https://www.oracle.com/java/)
 * [Maven](https://maven.apache.org/)
 * [Docker](https://www.docker.com/)

## How To Use

Check, that required tools was installed and run this commands from your command line:

```bash
# Clone this repository
$ git clone https://github.com/baixa/ostock.git

# Go into the repository
$ cd ostock

# Run shell script
$ bash bin/control.sh
```

---

> GitHub [@baixa](https://github.com/baixa)

