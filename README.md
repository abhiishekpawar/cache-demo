# CacheCommerce: High-Performance E-Commerce Product API with Redis Caching

## 🚀 Project Overview

**CacheCommerce** is a Spring Boot-based E-Commerce Product Catalog API that leverages **Redis caching** (via Redis Cloud) to optimize data retrieval, reduce database load, and improve API response times. This project demonstrates **real-world caching strategies** used in high-traffic retail applications.

---

## 💡 Features

* CRUD operations for products (`GET`, `POST`, `PUT`, `DELETE`)
* Category-based product filtering
* **Caching** of frequently accessed data:

    * `/products` → Cached list of all products
    * `/products/{id}` → Cached individual products
    * `/products/category/{name}` → Optional caching for category-based listings
* Cache invalidation for updates and deletes to prevent stale data
* Redis TTL (time-to-live) and idle-time reset for frequently accessed products
* Logging to demonstrate cache hits vs database access

---

## 🛠️ Tech Stack

* **Backend:** Spring Boot 3.5.6
* **Database:** MySQL (for product persistence)
* **Cache:** Redis Cloud (managed Redis instance)
* **Logging:** SLF4J 
* **Build Tool:** Maven 

---

## 💾 Project Structure

```
src
 └─ main
     ├─ java/com/example/cachecommerce
     │   ├─ config/        → Redis cache configuration
     │   ├─ controller/    → REST API controllers
     │   ├─ model/         → Product entity
     │   ├─ repository/    → JPA repositories
     │   └─ service/       → Business logic + caching
     └─ resources/
         └─ application.properties
```

---

## ⚡ Caching Strategy

| Cache Name          | Key / Usage               | TTL          | Purpose                                        |
| ------------------- | ------------------------- | ------------ | ---------------------------------------------- |
| `all_products`      | N/A (entire product list) | 30 seconds   | Homepage / frequently accessed product listing |
| `products`          | Product ID                | 5–10 minutes | Individual product details                     |
| `category_products` | Category name             | 2–5 minutes  | Category-based product filtering               |

**Annotations Used:**

* `@Cacheable` → Read-heavy endpoints
* `@CachePut` → Updates product cache on modification
* `@CacheEvict` → Remove stale data on delete/update

---

## 🔧 Setup Instructions

1. **Clone the repository**

```bash
git clone https://github.com/abhiishekpawar/cache-demo.git
cd cache-demo
```

2. **Configure Redis Cloud**

* Obtain your Redis Cloud endpoint, port, and password
* Update `application.properties`:

```properties
spring.cache.type=redis
spring.redis.host=<your-redis-host>
spring.redis.port=<your-redis-port>
spring.redis.password=<your-redis-password>
```

3. **Configure Database**

* Set up PostgreSQL/MySQL locally or on cloud
* Update `spring.datasource.*` properties in `application.properties`

4. **Run the application**

```bash
mvn spring-boot:run
```

or

```bash
./gradlew bootRun
```

5. **Test API Endpoints**

* `GET /products` → fetch all products
* `GET /products/{id}` → fetch product by ID
* `GET /products/category/{name}` → fetch products by category
* `POST /products` → create new product
* `PUT /products/{id}` → update product
* `DELETE /products/{id}` → delete product

---

## 📈 Performance Benefits

* **Reduced database calls:** Repeated reads are served from Redis cache
* **Faster response times:** Product fetches reduced from ~500ms → ~50ms (demo metric)
* **Real-world caching patterns:** TTL, idle-time reset, cache invalidation, category-level caching

---

## 📂 Sample Product JSON

```json
{
  "name": "Wireless Headphones",
  "description": "Noise-cancelling over-ear headphones",
  "price": 299.99,
  "category": "Electronics",
  "stockQuantity": 50
}
```

---

## 📌 Key Learnings

* Implemented **Redis caching** to improve performance for high-traffic endpoints
* Learned **Spring Boot cache annotations** (`@Cacheable`, `@CachePut`, `@CacheEvict`)
* Demonstrated **cache TTL management**, **per-key configuration**, and **idle-time reset**
* Built a **real-world E-Commerce API** with CRUD + caching + logging
