# Projet MDD - Orion

Ce projet est une application web complète composée de :
- **Frontend** : Angular 14
- **Backend** : Java 17 (Spring Boot)
- **Base de Données** : MySQL

---

## 📂 Prérequis

Assurez-vous d'avoir installé sur votre machine :
- **Node.js** (>= 16.x) et **npm**
- **Angular CLI** (>= 14.x)
- **Java JDK** (version 17)
- **Maven**
- **MySQL Server**

---

## ⚙️ 1. Installation de la base de données (MySQL)

1.1 **Créez la base de données**

   ```sql
   CREATE DATABASE;```

1.2 **Initialisez les tables de la base de données**

   ```sql
   USE ma_base; ```

Table theme

   ```sql
   CREATE TABLE theme (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     title VARCHAR(255),
     content VARCHAR(255)
   ) ENGINE=InnoDB; ```

Table user

   ```sql
   CREATE TABLE user (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     username VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL,
     password VARCHAR(255) NOT NULL,
     created_at TIMESTAMP NULL,
     updated_at TIMESTAMP NULL
   ) ENGINE=InnoDB; ```

Table article

   ```sql
   CREATE TABLE article (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     title VARCHAR(255) NOT NULL,
     author VARCHAR(255) NOT NULL,
     content LONGTEXT,
     created_at TIMESTAMP NULL,
     updated_at TIMESTAMP NULL,
     theme_id INT,
     FOREIGN KEY (theme_id) REFERENCES theme(id)
   ) ENGINE=InnoDB; ```

Table messages

   ```sql
   CREATE TABLE messages (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     article_id INT NOT NULL,
     user_id INT NOT NULL,
     content VARCHAR(255),
     FOREIGN KEY (article_id) REFERENCES article(id),
     FOREIGN KEY (user_id) REFERENCES user(id)
   ) ENGINE=InnoDB; ```

## ⚙️ 2. Installation et lancement du Frontend (Angular)

2.1 **Clonez le projet**

   ```git clone https://github.com/Emiliengrbn/Orion-MDD.git```
   ```cd front```

2.2 **Installez les dépendances**

   ```npm install```

2.3 **Démarrez l'application**

   ```ng serve```

2.4 **Accédez à l'application**

   [http://localhost:4200](http://localhost:4200)
   

## ⚙️ 3. Installation et lancement du Backen (Java)

3.1 **Configurez le fichier application.properties**

    ```spring.datasource.url=jdbc:mysql://localhost:3306/NOM_DE_LA_BDD
    spring.datasource.username=root
    spring.datasource.password=MDP_DE_LA_BDD
    server.port=9080
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true```

3.2 **Démarrez l'application**

   ```./mvnw spring-boot:run```
