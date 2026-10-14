Corporate Admin Panel

Корпоративная административная панель с системой разграничения прав доступа на основе ролей (RBAC). Проект разработан с использованием Java 17+, Spring Boot 3 и соблюдением архитектурных принципов SOLID и паттернов проектирования.

Технологический стек

Core: Java 17+, Spring Boot 3.x

Security: Spring Security (RBAC, BCrypt password hashing, Custom UserDetailsService)

Data Access: Spring Data JPA, Hibernate

Database: H2 Database (In-Memory / Dev), PostgreSQL (Production Ready)

View/UI: Thymeleaf, HTML5/CSS3

Tools: Lombok, Maven

Архитектура и принципы проектирования (SOLID)

В архитектуре приложения реализовано разделение ответственности по слоям (Layered Architecture):

Single Responsibility Principle (SRP):

AdminController — обрабатывает только HTTP-запросы и навигацию.

UserService — содержит исключительно бизнес-логику управления пользователями.

CustomUserDetailsService — отвечает за интеграцию аутентификации Spring Security с базой данных.

Open/Closed Principle (OCP) & Interface Segregation (ISP):

Бизнес-логика вынесена в интерфейс UserService и его реализацию UserServiceImpl, что позволяет расширять функционал без модификации существующего кода.

Dependency Inversion Principle (DIP):

Контроллеры и сервисы зависят от абстракций (интерфейсов), а не от конкретных реализаций (UserRepository, UserService).

Design Patterns:

Repository Pattern: Использование Spring Data JPA для абстрагирования работы с БД.

Strategy Pattern: Использование PasswordEncoder (BCrypt) для гибкой смены алгоритма шифрования.

Инструкция по запуску

1. Клонирование репозитория

git clone https://github.com/KashkahaAV/corporate-admin-panel.git
cd corporate-admin-panel


2. Сборка и запуск приложения

mvn clean install
mvn spring-boot:run


Приложение доступно по адресу: http://localhost:8080

Учетные данные по умолчанию

При первом старте приложения автоматически инициализируются тестовые данные (DataInitializer):

URL админ-панели: http://localhost:8080/admin/users

Логин: admin

Пароль: admin123

Роль: ROLE_ADMIN

H2 Console (Просмотр базы данных)

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (пустое поле)

Структура проекта

src/main/java/com/corporate/adminpanel/
├── config/              # Конфигурация Spring Security, PasswordEncoder и DataInitializer
├── controller/          # Spring MVC контроллеры (AdminController)
├── model/               # JPA сущности (User, Role)
├── repository/          # Spring Data JPA репозитории
└── service/             # Интерфейсы и реализация бизнес-логики (UserService)
