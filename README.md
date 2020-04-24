# Capitulo 7 - Primefaces

Este capitulo contiene ejemplos de algunos componentes de la libreria Primefaces

## Prerrequisitos

* Instalar [AdoptOpenJDK 8](https://adoptopenjdk.net/)
* Instalar [Git](https://git-scm.com/)
* Instalar [Maven](https://maven.apache.org/)
* Instalar [Payara Server 5.x](https://www.payara.fish/products/downloads/#community)
* Instalar [MariaDB](https://mariadb.com/)
* Instalar [Conector J 2.6](https://downloads.mariadb.org/connector-java/) - Este conector es un archivo con extension .jar, que una vez descargado se debe copiar dentro del directorio (/payara/glassfish/domains/domain1/lib) de payara server.

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

* **Pool de conexiones**
  * 1 - Nuevo...
  * 2 - Nombre de Pool: *universidadPool*
  * 3 - Tipo de recurso : *java.sql.Driver*
  * 4 - Click en siguiente
  * 5 - Driver Classname:  *org.mariadb.jdbc.Driver*
  * 6 - Agregar las siguientes propiedades adicionales: 
    * 6.1 - serverName : *localhost*
    * 6.2 - databaseName : *Universidad*
    * 6.3 - URL : *jdbc:mariadb://localhost:3306/Universidad*
    * 6.4 - user: *nombreDeUsuario*
    * 6.5 - password: *password*
    
Una vez finalizada la creación del pool de conexiones, se debe realizar un "ping" para comprobar que el proceso se haya realizado de manera exitosa.

Para ello haga clic en el botón con la leyenda "Ping" que se encuentra dentro de la pestaña general 
    
* **Recurso JDBC**
  * 1 - Nuevo...
  * 2 - Nombre JNDI :     *jdbc/universidad*
  * 3 - Nombre Pool : *universidadPool*
  * 4 - Click en aceptar
  
## Desplegar *.war* en Payara Server


