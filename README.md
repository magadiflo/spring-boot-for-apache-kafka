# [Spring Boot con Apache Kafka](https://www.youtube.com/watch?v=UbbyW5Z1lv8&t=3205s)

---

## Instalando Apache Kafka

- Ir a la siguiente dirección y dar en download: https://kafka.apache.org/quickstart
- Se abrirá una página donde descargaremos un archivo comprimido `.tgz`, en mi caso descargué el archivo
  `kafka_2.13-3.5.0.tgz`
- Luego de descargar el archivo lo descomprimiremos en la raíz del disco `C:\\`. En mi caso quedó de esta forma:
  `C:\kafka_2.13-3.5.0`
- **NOTA:** Recomendable **que sea en la raíz del disco C:/**, sino podría traer problemas si lo hacemos en otro lugar.

## Configuraciones iniciales

Realizamos la siguiente configuración **(manual)** para que `Kafka funcione con Windows`, ya que por defecto está
configurado para que funcione con servidores Linux. Por lo tanto, abrimos el archivo `server.properties` ubicado:

````bash
C:\kafka_2.13-3.5.0\config\server.properties
````

Cambiamos el directorio que apunta a un directorio linux por nuestro directorio de windows:

````properties
############################# Log Basics #############################
# A comma separated list of directories under which to store log files
#log.dirs=/tmp/kafka-logs # Por defecto
log.dirs=C:/kafka_2.13-3.5.0/kafka-logs
````

También modificamos el archivo `zookeeper.properties` ubicado en:

````bash
C:\kafka_2.13-3.5.0\config\zookeeper.properties
````

De la misma manera cambiamos el directorio que apunta a linux por nuestro directorio de windows, además cambiamos el
directorio `zookeeper` por `zookeeper-data`:

````properties
# the directory where the snapshot is stored.
#dataDir=/tmp/zookeeper # Por defecto
dataDir=C:/kafka_2.13-3.5.0/zookeeper-data
````

## Iniciar servidor Kafka y Zookeeper

- Iniciar la terminal en modo de administrador.
- Ubicarnos mediante la terminal en nuestro directorio de instalación `C:\kafka_2.13-3.5.0`
- Ejecutamos los comandos en el siguiente orden:

**Inicie el servicio ZooKeeper**

```bash
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

**Iniciar Kafka**

````bash
.\bin\windows\kafka-server-start.bat .\config\server.properties
````

## Crear un Topic

Crearemos un topic (tema) sobre el cual se enviarán los mensajes. Recuerda que **los mensajes
siempre deben estar dentro de un Topic**, porque si no tenemos un Topic, básicamente no podríamos
enviar mensajes.

El **topic** que crearemos se llamará `example-topic` y por defecto `Apache Kakfa` corre en el puerto `9092`.

**Crea un nuevo topic en el servidor de kafka**

````bash
.\bin\windows\kafka-topics.bat --create --topic example-topic --bootstrap-server localhost:9092
````

**Describir los detalles de un topic**

````bash
.\bin\windows\kafka-topics.bat --describe --topic example-topic --bootstrap-server localhost:9092
````

**Listar todos los topics que existen dentro del broker de Apache Kafka**

````bash
.\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
````

**Inicia una consola para ver mensajes de un topic específico**

````bash
.\bin\windows\kafka-console-consumer.bat --topic example-topic --bootstrap-server localhost:9092
````

**Inicia una consola para enviar mensajes a un topic específico**

````bash
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic example-topic
````

---

## Proyecto Spring Boot

Creamos el proyecto en [spring.io](https://start.spring.io/) con las siguientes dependencias maven:

````xml
<!--Spring Boot: 3.1.3-->
<!--Java: 17-->
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
````
