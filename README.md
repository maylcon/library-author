**Construir y ejecutar la imagen**

Pre-requisitos
Debe contar con Git (opcional), Docker, JDK 17 y Maven previamente configurados

Lo primero que vamos a realizar es descargar el proyecto y ubicarlo en una ruta reconocida
dentro del proyecto vamos a encontrar una carpeta que se llama "Oracle"
y vamos a realizar los pasos que estan en el READM.md de la carpeta ORACLE.

cuando terminemos de montar la imagen de ORACLE vamos a generar el archivo JAR para la imagen

- mvn clean install -Dmaven.test.skip=true

Al finalizar verificamos  en la carpeta TARGET del proyecto que se cree un archivo llamado "library-0.0.1-SNAPSHOT.jar"

Ahora vamos a construir la imagen Docker del servicio backend con el siguiente comando

- docker build -t library-service .

Luego, verificas que la imagen se cree con el siguiente comando

- docker images

Ejecutamos el contenedor a partir de esta imagen

- docker run -d --network=oracle-network --name library-service-container -p 8181:8181 library-service

Final mente verificamos que el contenedor se este ejecutando

docker ps

NOTA: Al finalizar la revision para dejar los contenedores limpios puedes ejecutar los siguientes comandos en ese orden
- docker stop library-service-container
- docker rm library-service-container