🚀 EthosPath: Tu Camino, Tu Comunidad
EthosPath no es solo una aplicación de gestión de tareas; es una plataforma social de gamificación diseñada para romper la rutina, superar retos personales y, sobre todo, conectar con personas que comparten tus mismos intereses y ganas de mejorar.

💡 La Idea Detrás del Proyecto
La filosofía de EthosPath se basa en que el crecimiento personal no tiene por qué ser un camino solitario. Queremos transformar los hábitos diarios en "misiones" épicas. ¿Quieres empezar a correr? ¿Quieres aprender un idioma? ¿Quieres mejorar tus habilidades de programación?

En EthosPath:

Las misiones son el motor: No son simples tareas, son retos con dificultad y recompensas que te ayudan a subir de nivel.

La comunidad es el corazón: Al completar retos, no solo ganas XP, sino que te vuelves parte de un ecosistema donde puedes ver qué están haciendo otros, inspirarte y conocer gente con tus mismas metas.

Gamificación Real: Implementamos un sistema de niveles y rangos para que tu progreso sea visual y gratificante.

🛠️ El Motor Tecnológico (Stack)
Para este primer gran proyecto, hemos elegido un stack robusto pero flexible:

Backend: Java 21 con Spring Boot, aprovechando la potencia de los servicios REST para una comunicación fluida.

Base de Datos: Gestión de datos relacionales para mantener la integridad de los perfiles y misiones.

Seguridad: Configuración personalizada para permitir un desarrollo ágil y conectividad total entre dispositivos.

Arquitectura DTO: Hemos diseñado un sistema de transferencia de datos optimizado para que la aplicación móvil reciba solo lo que necesita, ahorrando datos y batería.

🗺️ Mapa de la API (Endpoints)
👤 Perfil y Social
POST /api/users/register: Únete a la comunidad.

POST /api/users/login: Entra en tu camino.

GET /api/stats/user/{id}: Tu "Hoja de Personaje" (nivel, XP, barra de progreso).

⚔️ El Tablón de Misiones
GET /api/missions: Explora todos los retos disponibles creados por la comunidad.

GET /api/categories: Filtra por lo que te apasiona (Deporte, Mente, Social, Tech).

📈 Tu Progreso
POST /api/user-missions/start: Acepta un nuevo desafío.

POST /api/user-missions/complete: Reclama tu recompensa y sube de nivel.

🎯 Próximos Pasos (Roadmap)
Este es solo el comienzo. Para hacer de EthosPath el sitio ideal para conocer gente, tenemos en el radar:

Sistema de Amigos: Para comparar progresos y hacer misiones conjuntas.

Misiones Globales: Retos masivos para toda la comunidad al mismo tiempo.

Chat por Categorías: Espacios para hablar con gente que está haciendo tus mismas misiones.

Desarrollado con pasión por: Adrián Rey Sanchez-Ortiz
Buscando siempre la excelencia técnica y el impacto social.
