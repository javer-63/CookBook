# **CookBook** — это веб-приложение, разработанное на платформе Spring Boot, которое предоставляет функционал для создания, управления и просмотра кулинарных рецептов. Приложение поддерживает категоризацию рецептов и фильтрацию по сложности приготовления.

## Основные функции
### 1. **Рецепты**
- **Просмотр рецептов**: Возможность просматривать все рецепты или фильтровать их по категориям.
- **Детальный просмотр**: Полная информация о рецепте, включая описание, сложность, время приготовления и текст рецепта.
- **Создание рецептов**: Добавление новых рецептов с указанием категории и сложности.
- **Редактирование и удаление**: Полное или частичное обновление рецептов, а также их удаление.

### 2. **Категории**
- **Привязка к рецептам**: Каждый рецепт относится к определенной категории.
- **Фильтрация**: Возможность фильтрации рецептов по выбранной категории.

### 3. **Уровни сложности**
- **Три уровня**: Рецепты могут иметь сложность "Легко", "Средне" или "Сложно".
---
## Технологии и инструменты
- **Фреймворк**: Spring Boot
- **База данных**: PostgreSQL
- **API**: RESTful архитектура
- **Шаблонизатор**: Thymeleaf 
- **Сборка**: Maven
- **Дополнительные технологии**: Lombok для сокращения кода

---

# Контроллеры приложения CookBook
## `RecipeController` — это основной контроллер, отвечающий за работу с рецептами через веб-интерфейс.
### Методы для работы с рецептами
- **redirect (GET /)** — Перенаправляет на страницу со списком рецептов (`/recipes`)
- **recipes (GET /recipes)** — Отображает список всех рецептов с возможностью фильтрации по категории
- **recipe (GET /recipes/{id})** — Отображает детальную страницу конкретного рецепта
- **addForm (GET /recipes/add)** — Форма для добавления нового рецепта
- **addRecipe (POST /recipes/add)** — Обрабатывает создание нового рецепта
- **deleteRecipe (POST /recipes/{id}/delete)** — Удаляет рецепт
- **editForm (GET /recipes/{id}/edit)** — Форма для редактирования рецепта
- **updateRecipe (POST /recipes/{id}/edit)** — Обрабатывает обновление рецепта

---

## `RestRecipeController` — это REST API контроллер для работы с рецептами.
### Методы API
- **recipes (GET /api/recipes)** — Возвращает список рецептов (с фильтрацией по категории)
- **categories (GET /api/categories)** — Возвращает список всех категорий
- **recipe (GET /api/recipes/{id})** — Возвращает конкретный рецепт
- **createRecipe (POST /api/recipes/add)** — Создает новый рецепт
- **deleteRecipe (DELETE /api/recipes/{id})** — Удаляет рецепт
- **updateRecipe (PUT /api/recipes/{id})** — Полностью обновляет рецепт
- **patchRecipe (PATCH /api/recipes/{id})** — Частично обновляет рецепт

---

# Модели приложения CookBook
## 1. **Recipe (Рецепт)**
### Поля
- **id (Long)** — Уникальный идентификатор
- **title (String)** — Название рецепта
- **description (String)** — Краткое описание
- **difficulty (Difficulty enum)** — Сложность приготовления
- **cookingTime (int)** — Время приготовления в минутах
- **category (Category)** — Категория рецепта
- **recipeText (String)** — Полный текст рецепта

## 2. **Category (Категория)**
### Поля
- **id (Long)** — Уникальный идентификатор
- **name (String)** — Название категории

## 3. **Difficulty (Сложность)**
### Значения enum
- **EASY("Легко")**
- **MEDIUM("Средне")**
- **HARD("Сложно")**

---

# Репозитории приложения CookBook
## 1. **RecipeRepository**
### Методы
- **findAll()** — Возвращает все рецепты
- **findAllByCategory(Category category)** — Возвращает рецепты по категории
- **findById(Long id)** — Находит рецепт по ID

## 2. **CategoryRepository**
### Методы
- **findAll()** — Возвращает все категории
- **findByName(String name)** — Находит категорию по названию

---

# Сервисы приложения CookBook
## `RecipeService` — содержит бизнес-логику работы с рецептами
### Основные методы
- **getAll()** — Возвращает все рецепты
- **getAllByCategory(String categoryName)** — Возвращает рецепты по категории
- **getRecipeById(Long id)** — Возвращает рецепт по ID
- **createRecipe(Recipe recipe)** — Создает новый рецепт
- **deleteRecipe(Long id)** — Удаляет рецепт
- **updateRecipe(Long id, Recipe newRecipe)** — Полностью обновляет рецепт
- **updatePartRecipe(Long id, Recipe recipeUpdates)** — Частично обновляет рецепт

---

# Инструкция по запуску приложения CookBook
## Требования
- Java 17+
- PostgreSQL
- Maven 3.8+

## Настройка базы данных
1. Создайте БД:
```sql
CREATE DATABASE cookbook;
```

2. Настройте подключение в `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cookbook
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## Запуск приложения
1. Соберите проект:
```bash
mvn clean package
```

2. Запустите приложение:
```bash
java -jar target/CookBook-0.0.1-SNAPSHOT.jar
```

Приложение будет доступно по адресу: [http://localhost:8080](http://localhost:8080)

## Доступные endpoint'ы
- Веб-интерфейс: `/` (переадресация), `/recipes`
- REST API: `/api/recipes`, `/api/categories`