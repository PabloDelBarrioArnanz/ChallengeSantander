Challenge Eventos Reactivos Santander
En este challenge se propondrá un caso práctico real que cubrirá un caso de uso para una arquitectura orientada a eventos. Las tecnologías necesarias para completarlo son Java 8, Kafka y Spring Cloud Stream.
Se asumirá nivel profiency en el uso de programación funcional de java8, además de ser necesario realizar tests unitarios con una cobertura del 80%.
El Kafka tendréis que desplegarlo en un contenedor de docker, con docker compose.
Caso práctico
Nos encontramos ante una empresa de logística, la cual se encarga de gestionar los envíos nacionales de otras empresas para su distribución.
La petición para la creación de un envío será recibida mediante Api rest, que recibirá una lista de envíos que contendrá los siguientes campos obligatorios:
-	Empresa
-	Fecha de envio formato dd/mm/yy
-	Ciudad
-	Dirección
-	Peso en kg
Un envío válido sería:
Empresa : “Amazon”
Fecha de envio: “10/10/20”
Ciudad Envio: “Malaga”
Dirección: “Calle falsa 123”
Peso en kg: 2,7

Después de recibir la lista, se deberá agrupar la lista de pedidos por ciudad de envío y enviarlo a un tópico de Kafka, donde estará otro microservicio escuchando estos envios transformados.

El microservicio tendrá una base de datos donde estarán almacenados los pedidos pendientes de procesar hasta que se den algunas condiciones especiales.

Para que un lote de envíos a una ciudad sea procesado, deberán darse al menos una de las siguientes condiciones:
-	Que haya como mínimo 10 envíos pendientes en esa ciudad
-	Que el peso de los pedidos procesados supere los 15kg




Si no consigue introducir el mapa a la base de datos ->
Deberá enviar un mensaje a otro tópico para monitorización con un KO explicando el motivo por el KO ( mapa no valido, db caída, etc).

Si consigue introducir el mapa a la base de datos ->
Deberá enviar un mensaje a otro tópico con un OK diciendo el número de envíos introducidos en la base de datos en cada ciudad.

Si después aceptar envíos a procesar en la base de datos, se cumple algunas de las dos condiciones anteriores para alguna de las ciudades, enviará un mensaje por cada una de las ciudades con la lista de pedidos que se hayan procesado a un tercer tópico.

Además, será necesario eliminar de la tabla todos los envíos procesados.

Por ejemplo, imaginemos que hay en la base de datos actualmente 7 envíos pendientes a Barcelona, y hacen una petición con un listado de envíos y hay 5 envíos a Barcelona.
Después de introducirlos, habrá 12 envíos hacia Barcelona, por lo que cumplirá la condición de que haya como mínimo 10 envíos y esos 12 envíos se procesarán, se enviará un mensaje a este tercer tópico y se borraran de la base de datos. 
