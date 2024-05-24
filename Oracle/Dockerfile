# Utiliza la imagen de Oracle XE desde el Oracle Container Registry como base
FROM container-registry.oracle.com/database/express:21.3.0-xe

# Variables de entorno para la contraseña de SYS y PDB_ADMIN
ENV ORACLE_PWD maylcon123

# Copia el script de inicialización a una ubicación en la imagen Docker
COPY init-user-and-tables.sql /opt/oracle/scripts/startup/

# Exponer puertos
EXPOSE 1521
EXPOSE 5500

# Comando para iniciar Oracle XE y ejecutar el script de inicialización
CMD ["/bin/bash", "-c", "source /opt/oracle/runOracle.sh && sqlplus sys/${ORACLE_PWD}@localhost:1521/XE as sysdba @/opt/oracle/scripts/startup/init-user-and-tables.sql && tail -f /dev/null"]

