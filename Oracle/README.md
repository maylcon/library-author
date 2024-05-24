# Construir y Ejecutar la Imagen Oracle

## Instrucciones

### 1. Crear la Red en Docker
Primero, vamos a crear la red dentro de Docker para que los contenedores puedan comunicarse entre sí:

```bash
docker network create oracle-network -d bridge
```

### 2. Construir la Imagen Docker de Oracle
Seguido, vamos a construir la imagen Docker de Oracle para tener una base de datos provisional:

```bash
docker build -t oracle-xe-with-user-and-tables .
```

### 3. Verificar la Creación de la Imagen
Verificamos que la imagen se haya creado con el siguiente comando:

```bash
docker images
```

### 4. Ejecutar el Contenedor
Luego, ejecutamos un contenedor a partir de esta imagen:

```bash
docker run -d -it --network=oracle-network --name oracle-xe-container -p 1521:1521 -p 5500:5500 oracle-xe-with-user-and-tables
```

### 5. Verificar el Contenedor en Ejecución
Finalmente, verificamos que el contenedor se esté ejecutando con el siguiente comando:

```bash
docker ps
```

## Notas
Al finalizar la revisión, para dejar los contenedores limpios, puedes ejecutar los siguientes comandos en ese orden:

```bash
docker stop oracle-xe-container
docker rm oracle-xe-container
```

## Sobre el Autor
Este proyecto fue realizado por:
##### 🌟 Maylcon Ramirez 
##### 📧 sanson.saray@gmail.com
##### 🛠 Especialista en Ingeniería de Software