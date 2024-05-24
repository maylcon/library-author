**Construir y ejecutar la imagen**

Primero vamos a crear la red dentro de Docker para que los contenedores tengan comunicación.

- docker network create oracle-network -d bridge

Seguido vamos a construir la imagen Docker de Oracle para tener unas BD provisional

- docker build -t oracle-xe-with-user-and-tables .

Verificas que la imagen se cree con el siguiente comando

- docker images

Luego, ejecuta un contenedor a partir de esta imagen

docker run -d -it --network=oracle-network --name oracle-xe-container -p 1521:1521 -p 5500:5500 oracle-xe-with-user-and-tables

Final mente verificamos que el contenedor se este ejecutando

docker ps

NOTA: Al finalizar la revision para dejar los contenedores limpios puedes ejecutar los siguientes comandos en ese orden
- docker stop oracle-xe-container
- docker rm oracle-xe-container