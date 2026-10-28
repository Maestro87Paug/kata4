# Kata 4

## Objetivo

Consumir un servicio HTTP desde Java mediante HttpClient, transformar la respuesta JSON en objetos y mostrar el resultado por consola.

Separar la interfaz del cliente, el puerto de servicio, el adaptador HTTP, el parseo de la respuesta y la presentación del resultado.

## Ejecución

Abre el proyecto en IntelliJ y ejecuta primero la clase Main para iniciar el servidor HTTP.

Después ejecuta ClientMain para consultar la serie de Fibonacci.

La versión final consulta diez términos de Fibonacci y muestra el resultado por consola.

El servidor utiliza el puerto 8081.

Es necesario mantener el servidor iniciado para ejecutar el cliente.

## JDK y dependencias

Versión 26.

Build: Maven.

Dependencias utilizadas:

- Javalin 7.2.4.
- Gson 2.15.0.
- SLF4J Simple 2.0.20.

HttpClient forma parte de Java y no necesita dependencias externas.

## Cómo compilar y ejecutar

Desde la raíz del proyecto, donde se encuentra pom.xml:

```bash
mvn clean compile
```

Para iniciar el servidor:

```bash
mvn exec:java -Dexec.mainClass="software.ulpgc.kata4.Main"
```

Para ejecutar el cliente desde otra terminal:

```bash
mvn exec:java -Dexec.mainClass="software.ulpgc.kata4.ClientMain"
```

Estos comandos de ejecución necesitan el plugin exec de Maven.

También se pueden ejecutar las clases Main y ClientMain desde IntelliJ.

Para cambiar la URL base y el número de términos se pueden utilizar argumentos.

Por ejemplo:

```text
http://localhost:8081 8
```

## Estructura de la entrega y clases principales

- Código fuente: src/main/java/software/ulpgc/kata4.
- Paquete: software.ulpgc.kata4.
- Main: Inicia el servidor HTTP mediante Javalin.
- ClientMain: Ejecuta el cliente y muestra los resultados.
- FibonacciSeries: Representa la serie de Fibonacci.
- FibonacciService: Interfaz que define la operación de consulta.
- HttpFibonacciService: Realiza la petición HTTP y transforma la respuesta JSON.
- UseCase: Interfaz del caso de uso.
- FibonacciCalculationUseCase: Calcula la serie solicitada.
- SeriesSerializer: Interfaz de serialización.
- JsonSeriesSerializer: Convierte la serie en JSON mediante Gson.
- EndPoint: Interfaz de los endpoints.
- FibonacciEndPoint: Atiende las consultas de Fibonacci.
- HolaEndPoint: Atiende las peticiones de saludo.

ClientMain depende de FibonacciService y no directamente de la implementación HTTP.

La presentación está separada de la comunicación con el servidor.

El adaptador HTTP no imprime el resultado final.

## Servicio HTTP

El proyecto utiliza un servidor local desarrollado con Javalin.

URL base:

```text
http://localhost:8081
```

Endpoint principal:

```text
GET /fibonacci?n=10
```

El parámetro n indica el número de términos solicitados.

El servidor devuelve un array JSON con los números de Fibonacci.

También está disponible el endpoint:

```text
GET /hola/{name}
```

La URL base se configura desde ClientMain.

HttpFibonacciService construye la petición y comprueba el código de respuesta.

## Tratamiento de errores

Si el servidor no está disponible, se muestra un mensaje de error de conexión.

Si el servidor devuelve un código HTTP distinto de 200, se informa del código recibido.

Si la respuesta JSON no tiene el formato esperado, se muestra un mensaje de error.

Una respuesta vacía produce un mensaje indicando que no se han recibido datos.

El servidor devuelve HTTP 400 cuando el número solicitado es inferior a 0 o superior a 47.

Se utilizan tiempos de espera para evitar que las peticiones queden bloqueadas.

Las peticiones interrumpidas también se controlan desde ClientMain.

## Git

Repositorio:

https://github.com/Maestro87Paug/kata4

Las repeticiones se organizan en first-repetition, second-repetition y third-repetition.

Cada repetición se conserva en una rama identificable.

La rama principal debe contener la versión final de la entrega.

Los commits permiten localizar la implementación inicial y las tres repeticiones.

## Cambios realizados

- Añadí ClientMain para consumir el servicio HTTP.
- Definí FibonacciService como puerto de servicio.
- Implementé HttpFibonacciService como adaptador HTTP.
- Utilicé HttpClient para realizar peticiones GET.
- Centralicé la URL base del servicio.
- Utilicé Gson para transformar la respuesta JSON.
- Representé los datos mediante FibonacciSeries.
- Separé la comunicación HTTP de la presentación.
- Añadí el tratamiento de errores de conexión y códigos HTTP.
- Añadí la validación de respuestas JSON incorrectas.
- Incorporé tiempos de espera en las peticiones.
- Añadí SLF4J Simple para mostrar los mensajes del servidor.

## Implementación inicial

Servidor HTTP local mediante Javalin.

Cliente que realiza una petición GET al endpoint de Fibonacci.

Transformación de la respuesta JSON en un objeto FibonacciSeries.

Presentación de los resultados por consola.

Control de errores de conexión, HTTP y respuestas incorrectas.

## Repeticiones

En las tres repeticiones se mantiene el mismo objetivo y la misma estructura: interfaz, puerto de servicio, adaptador HTTP, parser y modelo de datos.

### Primera repetición

Rama: first-repetition.

Reconstrucción del cliente HTTP para consultar cinco términos de Fibonacci.

La respuesta JSON se transforma en un objeto FibonacciSeries.

El resultado se muestra por consola.

Se contempla el error de conexión cuando el servidor no está disponible.

### Segunda repetición

Rama: second-repetition.

Reconstrucción del cliente HTTP para consultar ocho términos de Fibonacci.

Se mantiene el mismo endpoint y la misma estructura.

Se destaca el último término de la serie en la salida.

Se añaden tiempos de espera para la conexión y la petición.

Se contempla el error HTTP 400.

### Tercera repetición

Rama: third-repetition.

Reconstrucción del cliente HTTP para consultar diez términos de Fibonacci.

Se mantiene el mismo flujo de petición, transformación y presentación.

Se mejora la validación del JSON recibido.

Se comprueba que la respuesta contiene un array de números enteros.

Se muestra la serie y el total de términos recibidos.

Se contemplan respuestas vacías, códigos HTTP de error y errores de conexión.

## Ejemplo de salida

Para una petición de diez términos, el resultado esperado es:

```text
Serie de Fibonacci (10 términos):
[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
Total: 10 términos
```

## Verificación

Se contemplan los siguientes casos de prueba.

**Consulta correcta**

Con el servidor iniciado, ejecutar ClientMain.

Resultado esperado:

```text
Serie de Fibonacci (10 términos):
[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
Total: 10 términos
```

**Servicio no disponible**

Detener Main y ejecutar ClientMain.

Mensaje esperado:

```text
No se puede conectar con el servicio
```

**Código HTTP 400**

Ejecutar el cliente con n=-1.

Mensaje esperado:

```text
Error: Error HTTP 400
```

**Código HTTP 404**

Utilizar como URL base:

```text
http://localhost:8081/no-existe
```

Mensaje esperado:

```text
Error: Error HTTP 404
```

**Respuesta vacía**

Ejecutar el cliente con n=0.

Mensaje esperado:

```text
Error: El servicio ha devuelto una respuesta vacía
```

**Respuesta incorrecta**

El parser comprueba que el JSON recibido tenga el formato esperado.

Si el contenido no es válido, se muestra un mensaje de error.

Los casos anteriores deben ejecutarse y comprobarse antes de la entrega.

La URL base puede modificarse sin cambiar todas las clases del proyecto.

## Vídeo
