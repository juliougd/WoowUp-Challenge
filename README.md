# WoowupChallegne

Desafio WoowUp que consta de 4 artefactos
- Respuestas a preguntas de propósito general
- Código fuente del sistema de Alertas
- Archivo .jar compilado del sistema
- Consulta SQL

### Preguntas de propósito general
***
##### ¿Me contarías sobre algún artículo, webinar, podcast o libro de algún tema de tecnología o desarrollo que leíste recientemente? ¿Por qué te gustó y por qué debería leerlo yo?

Clean code es un libro que ya lleva bastante tiempo, posee sabiduría de un gran desarrollador que es Robert Martin, tiene un sólido contenido orientado a objetos y si vas a desarrollar un sistema basado en este paradigma lo más probable es que gran parte de su contenido te sea de utilidad, sobre todo lo recomiendo a quienes estén empezando en el mundo del desarrollo, también tiene cosas que ya no son tan relevantes en la actualidad ya que en los lenguajes de programación se empezaron a desarrollar nuevas funcionalidades que hacen que las recomendaciones que tiene este libro queden un poco obsoletas. No obstante este libro fue muy relevante en su tiempo, pienso que es bueno leerlo al menos una vez para ver que tiene que compartir el señor robert martin, tiene vieja sabiduría que es válida hoy en dia.

##### ¿Qué feature de WoowUp te gustó mucho? ¿Por qué?

Big data, Realizar reportes de resultados en tiempo real con un volúmenes de datos grandes, buscar patrones que ayuden a predecir el comportamiento futuro requiere de una infraestructura enorme y mucha ciencia de la computación, pienso que es un campo desafiante y a la vez muy apasionante.

### Sistema de notificaciones
##### Supuestos
1 ) Las alertas de tipo informativas son las únicas que pueden expirar, se deberá indicar su duracion con un valor entero durante el proceso de creacion de la misma.
##### Diseños
![DC](https://github.com/juliougd/WoowUp-Challenge/blob/main/imagenes/DiagramaDeClases.png?raw=true)
![Navegabilidad](https://github.com/juliougd/WoowUp-Challenge/blob/main/imagenes/Navegabilidad.png?raw=true)

##### Tecnologias
***
Tecnologías utilizadas en el desafio:
* [maven.compiler.source]: Version 1.7
* [maven.compiler.target]: Version 1.7
* [junit]: Version 4.11
* [openjdk]: Version 17.0.2

##### Ejecutar sistema
***
Ejecutar el archivo [challenge woowup.jar] desde la consola
```
$ git clone https://gitlab.com/julioD/woowupchallegne.git
$ cd ../path/to/the/challenge woowup.jar
$ java -jar "challenge woowup.jar"
```
##### Ejecutar pruebas (VS Code)
***
```
$ Abrir la carpeta del codigo fuente con VS code
$ instalar [Extension Pack for Java]: v0.21.0 o bien [Test Runner for Java]: v0.34.0
$ Seleccionar el archivo en la ruta: challenge\src\test\java\challenge\woowup\AppTest.java
$ Ejecutar las pruebas con ayuda de la interface que brinda la extención "Test Runner for Java"
```

### Consulta SQL 
#### Enunciado : 
Escribir una consulta SQL que traiga todos los clientes que han comprado en total más de 100,000$ en los últimos 12 meses usando las siguientes tablas: 

Clientes: ID, Nombre, Apellido

Ventas: Fecha, Sucursal, Numero_factura, Importe, Id_cliente
#### Respuesta SQL
```
SELECT nombre, SUM(importe) as total
FROM ventas INNER JOIN clientes
on id = id_cliente and (DATE_PART('days', NOW()::timestamp - fecha::timestamp)<365)
GROUP by id
HAVING SUM(importe) > 100000
```
