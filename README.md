# **Spring Boot Application**

## **Description**

Cette application basée sur **Spring Boot** et **WebFlux** offre des fonctionnalités avancées telles que la gestion des créneaux horaires, la gestion des commandes, et une authentification sécurisée avec JWT. Elle est conçue pour être performante et extensible.

---

## **Fonctionnalités**

1. Gestion des créneaux horaires : 
   - Consultation des créneaux disponibles.
   - Réservation de créneaux.

2. Gestion des commandes :
   - Création, mise à jour et suppression de commandes.
   - Consultation des commandes.

3. Authentification JWT :
   - Génération de token JWT après connexion.
   - Sécurisation des endpoints via JWT.

4. Documentation API avec Swagger UI.

---

## **Technologies Utilisées**

- Spring Boot 3.x
- Java 21
- Spring WebFlux
- Spring Security ( JWT )
- JWT
- H2 Database
- Spring Cache (prévu)
- Kafka (prévu)
- Docker et Docker Compose
- Springdoc OpenAPI

---

## **Installation**

### **Prérequis**
- Java 21
- Maven
- Docker

### **Lancer l'application avec Maven**
1. Compiler et packager le projet :
   ```bash
   mvn clean package

2. Exécuter l'application :
   ```bash
   java -jar target/delivery-0.0.1-SNAPSHOT.jar
