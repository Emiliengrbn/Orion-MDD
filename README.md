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
