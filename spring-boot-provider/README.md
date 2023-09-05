# Productor

---

## Personalizar banner del microservicio provider

Solo para darle un toque de identidad a la aplicación le configuraremos su banner. Para eso vamos a la siguiente
página [datenkollektiv.de](https://devops.datenkollektiv.de/banner.txt/index.html).

En la página escribimos el texto que queremos mostrar al iniciar la aplicación. Para este caso escribiremos:
`Spring Kakfa Provider`.

En la siguiente dirección `/src/main/resources/` creamos el archivo `banner.txt` y pegamos el gráfico generado.

## Enviando nuestro primer mensaje a Kafka con Spring Boot

Debemos asegurarnos de **tener ejecutando Kafka antes de iniciar la aplicación**, para eso en el **README.md** principal
definimos los comandos a ejecutar, pero lo volveré a escribir aquí para tenerlo a la mano:

### Iniciar servidor Kafka y Zookeeper

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

## Iniciando la aplicación Provider

Luego de tener levantado kafka, levantamos la aplicación, `la primera vez mostrará un error` y es porque en el
`MainProviderApplication` estamos enviando el mensaje al topic `magadiflo-topic` que aún está siendo creado por esta
misma aplicación. Entonces, levantamos por segunda vez la aplicación y esta vez ya no mostrará el error proque el
**topic** fue creado en la primera levantada.

Ahora, si queremos ver el mensaje enviado en la terminal, debemos ejecutar previamente el siguiente comando:

````bash
.\bin\windows\kafka-console-consumer.bat --topic magadiflo-topic --bootstrap-server localhost:9092
````
