## Activité Pratique N°1 - Injection des dépendances 
**Part I**

---
### 1. Interface IDAO avec une méthode getDate

https://github.com/achrafhammi/J2EEArchitectureCourse/blob/d65dc45cc68a6d4b5cdb6e168b3974d81f75dd7d/src/main/java/ma/enset/DAO/IDao.java#L1-L5

### 2. Implementation de IDAO

https://github.com/achrafhammi/J2EEArchitectureCourse/blob/b389e6e4b4a66729ed52b437ea9732409d01b841/src/main/java/ma/enset/DAO/DaoImpl.java#L1-L11

### 3. Interface IMetier

https://github.com/achrafhammi/J2EEArchitectureCourse/blob/d65dc45cc68a6d4b5cdb6e168b3974d81f75dd7d/src/main/java/ma/enset/services/IMetier.java#L1-L5

### 4. Implementation MetierImpl

https://github.com/achrafhammi/J2EEArchitectureCourse/blob/b389e6e4b4a66729ed52b437ea9732409d01b841/src/main/java/ma/enset/services/MetierImpl.java#L7-L24

### 5. a Instantiation Statique
https://github.com/achrafhammi/J2EEArchitectureCourse/blob/b389e6e4b4a66729ed52b437ea9732409d01b841/src/main/java/ma/enset/presentation/Presentation.java#L11-L19
### 5. b Instantiation Dynamique
https://github.com/achrafhammi/J2EEArchitectureCourse/blob/b389e6e4b4a66729ed52b437ea9732409d01b841/src/main/java/ma/enset/presentation/Presentation2.java#L16-L31

### 5. c. 1 Instantiation par Spring: XML version
https://github.com/achrafhammi/J2EEArchitectureCourse/blob/b389e6e4b4a66729ed52b437ea9732409d01b841/src/main/java/ma/enset/presentation/PresentationByXml.java#L11-L17
avec spring-ioc.xml


### 5. c. 2 Instantiation par Spring: Annotations
https://github.com/achrafhammi/J2EEArchitectureCourse/blob/b389e6e4b4a66729ed52b437ea9732409d01b841/src/main/java/ma/enset/presentation/PresentationByAnnotations.java#L7-L13

