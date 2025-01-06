# **Spring Boot Application**

## **Description**

Cette application basée sur **Spring Boot** et **WebFlux** offre des fonctionnalités avancées telles que la gestion des créneaux horaires, la gestion des commandes, et une authentification sécurisée avec JWT. Elle est conçue pour être performante et extensible.

 Lien de la documentation : http://localhost:8089/swagger-ui/index.htm

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

## **Détails techniques du projet :**

### **Implémentation des principes HATEOAS :**
- Intégration des hypermédias dans les réponses de l'API REST pour améliorer la navigabilité des endpoints.

### **Sécurisation de l'API avec Spring Security et JWT :**
- Authentification via JWT avec des utilisateurs de test intégrés.
  - **Utilisateur de test :**
    - **Username** : `user`
    - **Password** : `password`

### **Utilisation de WebFlux pour une solution réactive :**
- Gestion non-bloquante des flux de données pour des performances accrues.

### **Documentation de l'API REST :**
- Utilisation de Springdoc OpenAPI pour générer et exposer une documentation Swagger interactive.

### **Proposition de persistance des données :**
- Intégration de **R2DBC** pour une approche réactive avec une base de données relationnelle.

### **Dockerisation de l’application :**
- Création d’un fichier Docker et d’une configuration Docker Compose pour simplifier le déploiement.

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

