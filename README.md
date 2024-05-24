# Prueba Backend Java con Spring Boot

## Descripción
Realizar una aplicación que permita la administración de libros y sus respectivos autores cumpliendo las siguientes condiciones:

- Backend realizado en PL/SQL.
- Exponer una capa de servicios mediante API REST (preferiblemente en Java, Maven).
- La aplicación debe permitir las operaciones de inserción, edición, consulta y borrado de información, tanto de libros como de autores, validando sus dependencias.

## Entrega de la Solución
- Código en Github, especificando en el README cómo ejecutar la aplicación y sus dependencias.
- Imagen o imágenes de contenedores Docker, junto con las instrucciones para correr el contenedor.

## Información Adicional
Entregar un portafolio o proyecto con demo funcional más repositorio de código con el que fue construido el proyecto. Esto es necesario para evaluar la calidad del código de proyectos personales que el candidato haya realizado, permitiendo consultar el código en algún repositorio y ver la ejecución de ese código.

## Herramientas y Tecnologías
- Java JDK 17
- Apache Maven 3.9.6
- JPA
- Lombok
- Oracle 21c
- PL/SQL
- Docker
- Git y GitHub

## Estructura de Carpetas y Paquetes del Servicio
Se utiliza una estructura MVC que conecta las diferentes capas por medio de las interfaces y haciendo uso de la inyección de dependencias.

![Estructura del Proyecto](../imagenes/0 estructura del proyecto.png)

## Construir y Ejecutar la Imagen

### Pre-requisitos
Debe contar con los siguientes componentes previamente configurados:
- Git (opcional)
- Docker
- JDK 17
- Maven

### Instrucciones

1. **Descargar el Proyecto**
   Descargar el proyecto y ubicarlo en una ruta reconocida.

2. **Configurar Oracle**
   Dentro del proyecto, vamos a encontrar una carpeta que se llama `Oracle`. Debemos seguir los pasos descritos en el archivo `README.md` de la carpeta `Oracle` para montar la imagen de Oracle.

3. **Generar el Archivo JAR**
   Una vez montada la imagen de Oracle, vamos a generar el archivo JAR para la imagen del servicio backend ejecutando el siguiente comando:

   ```bash
   mvn clean install -Dmaven.test.skip=true
   ```

   Al finalizar, verificamos en la carpeta `target` del proyecto que se haya creado un archivo llamado `library-0.0.1-SNAPSHOT.jar`.

4. **Construir la Imagen Docker del Servicio Backend**
   Vamos a construir la imagen Docker del servicio backend con el siguiente comando:

   ```bash
   docker build -t library-service .
   ```

5. **Verificar la Creación de la Imagen**
   Verificamos que la imagen se haya creado con el siguiente comando:

   ```bash
   docker images
   ```

6. **Ejecutar el Contenedor**
   Ejecutamos el contenedor a partir de esta imagen:

   ```bash
   docker run -d --network=oracle-network --name library-service-container -p 8181:8181 library-service
   ```

7. **Verificar el Contenedor en Ejecución**
   Finalmente, verificamos que el contenedor se esté ejecutando con el siguiente comando:

   ```bash
   docker ps
   ```

### Notas
Al finalizar la revisión, para dejar los contenedores limpios, puedes ejecutar los siguientes comandos en ese orden:

```bash
docker stop library-service-container
docker rm library-service-container
```

## Funcionamiento
Después de tener los contenedores ejecutando, vamos a ingresar a la siguiente URL:

[http://localhost:8181/swagger-ui/index.html#/](http://localhost:8181/swagger-ui/index.html#/)

Allí se encuentra toda la documentación del servicio por medio de Swagger y puedes ejecutar las diferentes consultas.

### Creación de Autor
Para empezar, no hay información en la base de datos, entonces recomiendo iniciar con el endpoint de creación, que debería ser el de Autor.

![Crear Usuario](../imagenes/1 crear usuario.png)

### Validar la Respuesta
Y validamos la respuesta.

![Respuesta Creación Usuario](../imagenes/2 respuesta creacion usuario.png)

### Consultar Todos los Autores
Con el EndPoint `author/getAll` validamos que esté registrado.

![Get All](../imagenes/3 getAll.png)

### Registrar Libros al Autor
Ahora vamos a registrar libros al Autor, utilizando el endpoint `library/create`.

![Library Create](../imagenes/4 library create.png)

### Verificar la Creación del Libro
Verificamos que se cree el libro y que quede asociado al autor que le asignamos.

![Get All Libraries](../imagenes/5 get All libraries.png)

### Eliminar un Autor con Libros Asociados
Por último, si intentamos eliminar un autor que tenga un libro, no nos va a permitir realizar la eliminación.
## Sobre el Autor
Este proyecto fue realizado por:
##### 🌟 Maylcon Ramirez 
##### 📧 sanson.saray@gmail.com
##### 🛠 Especialista en Ingeniería de Software