# Capitulo 7 - Primefaces

Este capitulo contiene ejemplos de algunos componentes de la libreria Primefaces

## Prerequisitos

* Instalar [AdoptOpenJDK](https://adoptopenjdk.net/)
* Instalar [Git](https://git-scm.com/)
* Instalar [Maven](https://maven.apache.org/)
* Instalar [Payara Server 5.x](https://www.payara.fish/products/downloads/#community)
* Instalar [MariaDB](https://mariadb.com/)

## Clonar proyecto

Abrir una terminal/consola y clonar el repositorio

```
git clone https://github.com/Francisco-Castillo/capitulo-primefaces/
```
## Crear base de datos

Una vez clonado debe crear una base de datos

```
CREATE DATABASE Universidad;
```

## Restaurar script 

Dirijase al directorio /src/main/resources

```
cd directorio/proyecto/
cd capitulo-primefaces/
cd src/main/resources/
mysql -unombreUsuario -pPasswordUsuario Universidad < universidad.sql
```
## Levantar Payara Server

```
cd directorio/instalacion/payaraServer/bin/
```
En linux

```
./asadmin start-domain
```
## Crear pool de conexiones y recurso JDBC
Para que la aplicación pueda funcionar es necesario crear un datasource, más conocido como pool de conexiones. Para ello colocar en el navegador web la siguiente direccion: (http://localhost:4848) que mostrara el panel de admin de Payara.


