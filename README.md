 Hello Kitty: MicroMarket API 

Este proyecto crea un sistema de gestión de ventas que sea tan eficiente como encantador.
Se trata de una REST API desarrollada con Java 17 y Spring Boot, diseñada  para administrar las
operaciones diarias de un micromercado temático.

He puesto especial cuidado en la lógica de negocio para asegurar que cada transacción sea perfecta. El sistema
no solo registra ventas, sino que también protege la integridad de la tienda validando el stock en tiempo real
y gestionando de forma automática los cálculos

Detalles Técnicos Específicos

Para lograr una arquitectura robusta y organizada, 
implementé los siguientes estándares:

Datos: Utilicé una base de datos H2 en memoria, 
configurada con una URL personalizada (jdbc:h2:mem:hellokitty) para
mantener un entorno de pruebas rapido y limpio.

Gestión de Versiones:Estructuré el trabajo mediante ramas
(Main, Develop y Feature) para asegurar que la rama principal siempre contenga 
código estable.

Utilicé Pull Requests para integrar cada nueva funcionalidad, evitando cambios 
directos en la versión final.

Apliqué Conventional Commits (feat:, docs:, fix:) para que el historial de cambios
sea legible y profesional.

Pruebas de API: Todas las rutas (Endpoints) fueron verificadas exhaustivamente mediante 
Postman, asegurando respuestas correctas (como el código 201 Created) y mensajes de error
claros cuando el stock es insuficiente.


