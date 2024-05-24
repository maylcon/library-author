**Prueba backend Java con Spring Boot**

Realizar una aplicación que permita la administración de libros y sus respectivos autores
cumpliendo las siguientes condiciones:
- Backend realizado en PL/SQL.
- Exponer una capa de servicios mediante API REST (preferiblemente en Java,
Maven).
La aplicación debe permitir las operaciones de inserción, edición, consulta y borrado de
información, tanto de libros y autores validando sus dependencias.

Entrega de la solución:
1. Código en Github, especificando en el readme como ejecutar la aplicación y sus
dependencias.
2. Imagen o imágenes de contenedores Docker, junto con las instrucciones para
correr el contenedor.
Información Adicional
Entregar un portafolio o proyecto con demo funcional más repositorio de código con el que
fue construido el proyecto. Lo anterior se requiere para ver la calidad de código de
proyectos personales que el candidato haya realizado, por un lado, poder consultar el
código en algún repositorio, y por el otro lado poder ver en ejecución ese código.

**Herramientas y Tecnologias**

Java JDK 17
apache-maven-3.9.6 
JPA
Lombok
Oracle 21c
PL/SQL
Docker
Git y GitHub

**Estructura de carpetas y paquetes del servicio**

Se realiza estructura MVC que conecta las diferentes capaz por medio de las interfaces y haciendo uso de la inyeccion de dependencias

imagen 0

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

**Funcionamiento**

Despues de tener los contenedores ejecutando vamos a ingresar a la siguiente URL
- http://localhost:8181/swagger-ui/index.html#/

alli se encuentra toda la documentacion del servicio por medio de Swagger y puedes ejecutar las diferentes consultas.

Para empezar no hay informacion en la BD entonces recomiendo iniciar con el endpoint de creacion, que deberia ser el de Autor.

imagen 1

y validamos la respuesta 

imagen 2

Con el EndPoint author/getAll validamos que este registrado

imagen 3

Ahora vamos a registrar libros al Autor, entonces vamos a utilizar el endPoint library/create

imagen 4

verificamos que se cree el libro y que quede asociado al autor que le asignamos.

imagen 5

y por ultimo si intentamos eliminar un Autor que tenga un libro, no nos va a permitir realizar la eliminacion