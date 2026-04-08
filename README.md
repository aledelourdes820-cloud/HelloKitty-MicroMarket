🎀 Hello Kitty: MicroMarket API 🎀

Este proyecto nace del deseo de crear un sistema de gestión de ventas que sea tan eficiente como encantador.
Se trata de una REST API desarrollada con Java 17 y Spring Boot, diseñada específicamente para administrar las
operaciones diarias de un micromercado temático.

He puesto especial cuidado en la lógica de negocio para asegurar que cada transacción sea perfecta. El sistema
no solo registra ventas, sino que también protege la integridad de la tienda validando el stock en tiempo real
y gestionando de forma automática los cálculos

💖Detalles Técnicos Específicos

Para lograr una arquitectura robusta y organizada, 
implementé los siguientes estándares:

Persistencia de Datos: Utilicé una base de datos H2 en memoria, 
configurada con una URL personalizada (jdbc:h2:mem:hellokitty) para
mantener un entorno de pruebas ágil y limpio.

Gestión de Versiones Profesional: * Estructuré el trabajo mediante ramas
(Main, Develop y Feature) para asegurar que la rama principal siempre contenga 
código estable.

Utilicé Pull Requests para integrar cada nueva funcionalidad, evitando cambios 
directos en la versión final.

Apliqué Conventional Commits (feat:, docs:, fix:) para que el historial de cambios
sea legible y profesional.

Pruebas de API: Todas las rutas (Endpoints) fueron verificadas exhaustivamente mediante 
Postman, asegurando respuestas correctas (como el código 201 Created) y mensajes de error
claros cuando el stock es insuficiente.

🌸 Mi Objetivo

Mi meta con este MicroMarket fue demostrar que el desarrollo de software puede ser 
técnicamente riguroso sin perder la esencia y la estética. Es un sistema pequeño en 
escala, pero grande en organización y buenas prácticas de ingeniería.

🌸 Mi Objetivo
Mi meta con este MicroMarket fue demostrar que el desarrollo de software puede ser 
técnicamente riguroso sin perder la esencia y la estética. Es un sistema pequeño en 
escala, pero grande en organización y buenas prácticas de ingeniería.

