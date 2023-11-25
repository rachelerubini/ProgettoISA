-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: isa-palestra
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `amministratore`
--

DROP TABLE IF EXISTS `amministratore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amministratore` (
  `ID_A` int NOT NULL AUTO_INCREMENT,
  `MAIL` varchar(40) NOT NULL,
  `PASSWORD` varchar(15) NOT NULL,
  PRIMARY KEY (`ID_A`),
  UNIQUE KEY `MAIL_UNIQUE` (`MAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amministratore`
--

LOCK TABLES `amministratore` WRITE;
/*!40000 ALTER TABLE `amministratore` DISABLE KEYS */;
INSERT INTO `amministratore` VALUES (1,'martino@gmail.com','martino1'),(2,'silvia@gmail.com','silvia1'),(3,'giuseppe@gmail.com','giuseppe1');
/*!40000 ALTER TABLE `amministratore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `ID_CL` int NOT NULL AUTO_INCREMENT,
  `SSN` char(9) DEFAULT NULL,
  `NOME` varchar(15) DEFAULT NULL,
  `COGNOME` varchar(15) DEFAULT NULL,
  `MAIL` varchar(40) NOT NULL,
  `PASSWORD` varchar(15) NOT NULL,
  `NASCITA` date DEFAULT NULL,
  `DELETED` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`ID_CL`),
  UNIQUE KEY `MAIL_UNIQUE` (`MAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'123456DDD','Achille','Poggi','achille.achi@libero.com','achille1','2003-07-29','N'),(2,'113456AAA','Anna','Sole','anna.sole@gmail.it','anna1','2000-12-09','N'),(3,'123456ZZZ','Anselmo','Biondi','anselmo@gmail.com','anselmo1','1955-07-08','N'),(4,'123456TTT','Giuseppina','Ferreri','giuseppina@mail.it','giuseppina1','1955-12-12','N'),(5,'12345678A','Matteo','Brina','matteo@gmail.com','matteo1','1990-03-12','N'),(6,'123456789','Simone','Molinari','simone@gmail.com','simone1','1999-11-12','N'),(7,'123456FFF','Claudio','Solferini','claudio.solf@gmail.com','claudio1','2005-06-12','N'),(8,'12345678R','Rachele','Rubini','rachele@gmail.com','rachele1','2000-03-27','N'),(9,'123456LLL','Giovanna','Sperti','giovanna@gmail.com','giovanna1','1967-04-01','N'),(10,'123456WWW','Evelina','Fioravanti','evelina@libero.com','evelina1','1947-09-09','N'),(11,'123456QQQ','Francesco','Davidi','francescod@gmail.it','francesco1','1985-05-05','N'),(12,'123456III','Vittorio','Bruni','vittorio@gmail.com','vittorio1','1977-08-11','N'),(13,'123456SSS','Gemma','Gagliani','gemma@gmail.com','gemma1','1920-10-23','N'),(14,'123456NNN','Giovanna','Sole','giov@gmail.it','giovanna1','1987-04-25','N'),(15,'12345678M','Margherita','Rizzieri','rizzieri@gmail.com','margherita1','1999-07-29','N'),(16,'123456VVV','Luca','Morelli','luca.mo@libero.com','luca1','1966-12-24','N'),(17,'123456MMM','Maria Sole','Bianchi','mary.sole@mail.com','maria1','2000-04-04','N'),(18,'123456CCC','Nicole','Fatteri','nicole.niki@gmail.it','nicole1','2006-09-26','N'),(19,'123456BBB','Fabrizio','Pensieri','fabrizio@gmail.com','fabrizio1','1977-10-19','N'),(20,'123456HHH','Silvio','Lenzi','silvio@gmail.com','silvio1','2005-03-08','N'),(21,'123456KKK','Simone','Masi','simo.masi@mail.com','simone1','1988-05-06','N'),(22,'12345678G','Stefano','Lacovara','stefanello@gmail.com','stefano1','2005-09-08','N'),(23,'12345678K','Stefania','Guggi','stenigucci@yahoo.it','stefania1','1985-12-06','N'),(24,'123456UUU','Tina','Verdi','verdi@gmail.it','tina1','1998-05-29','N'),(25,'123456JJJ','Umberto','Benedetti','umberty@hotmail.com','umberto1','1977-12-06','N'),(26,'123456OOO','Vincenzo','Sgarbanti','vince.sgarb@hotmail.com','vincenzo1','1999-04-04','N'),(27,'123456PPP','Elisa','Farsoni','elisa.farsoni@gmail.com','elisa1','1998-03-27','N'),(31,'1234567OO','Diego','Carrara','diego.carrara@gmail.com','diego1','1963-01-13','N');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corso`
--

DROP TABLE IF EXISTS `corso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corso` (
  `ID_CO` int NOT NULL AUTO_INCREMENT,
  `NOME` varchar(15) NOT NULL,
  `TIPO` varchar(15) DEFAULT NULL,
  `LIVELLO` varchar(25) DEFAULT NULL,
  `DELETED` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`ID_CO`),
  UNIQUE KEY `NOME_UNIQUE` (`NOME`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corso`
--

LOCK TABLES `corso` WRITE;
/*!40000 ALTER TABLE `corso` DISABLE KEYS */;
INSERT INTO `corso` VALUES (1,'boxe','extreme','professionale','N'),(2,'GAG','cardio','intermedio','N'),(3,'pilates','peaceful','avanzato','N'),(4,'yoga','peaceful','principiante','N'),(5,'zumba','cardio','avanzato','N'),(9,'TotalBody','cardio','principiante','N');
/*!40000 ALTER TABLE `corso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iscrizione`
--

DROP TABLE IF EXISTS `iscrizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iscrizione` (
  `ID_CL` int NOT NULL,
  `ID_CO` int NOT NULL,
  `DELETED` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`ID_CL`,`ID_CO`),
  KEY `ID_CO_idx` (`ID_CO`),
  KEY `ID_CL_idx` (`ID_CL`),
  CONSTRAINT `ID_CL` FOREIGN KEY (`ID_CL`) REFERENCES `cliente` (`ID_CL`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `ID_CO` FOREIGN KEY (`ID_CO`) REFERENCES `corso` (`ID_CO`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iscrizione`
--

LOCK TABLES `iscrizione` WRITE;
/*!40000 ALTER TABLE `iscrizione` DISABLE KEYS */;
INSERT INTO `iscrizione` VALUES (1,2,'N'),(2,5,'N'),(3,1,'N'),(3,5,'N'),(4,9,'N'),(5,2,'N'),(7,3,'N'),(11,1,'N'),(12,4,'N'),(12,9,'N'),(16,4,'N'),(17,9,'N'),(21,3,'N'),(23,2,'N'),(24,1,'N'),(26,9,'N');
/*!40000 ALTER TABLE `iscrizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensione` (
  `ID_R` int NOT NULL AUTO_INCREMENT,
  `VOTO` int DEFAULT NULL,
  `DATA` date DEFAULT NULL,
  `DELETED` varchar(1) DEFAULT 'N',
  `ID_COR` int NOT NULL,
  `ID_CLR` int NOT NULL,
  PRIMARY KEY (`ID_R`),
  KEY `ID_COR_idx` (`ID_COR`),
  KEY `ID_CLR_idx` (`ID_CLR`),
  CONSTRAINT `ID_CLR` FOREIGN KEY (`ID_CLR`) REFERENCES `cliente` (`ID_CL`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `ID_COR` FOREIGN KEY (`ID_COR`) REFERENCES `corso` (`ID_CO`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES (1,7,'2021-07-26','N',5,6),(2,8,'2020-12-03','N',3,5),(3,10,'2021-04-28','N',4,22),(4,8,'2021-02-12','N',1,15),(5,6,'2020-12-29','N',2,18),(6,9,'2021-06-27','N',1,1),(7,8,'2021-07-07','N',3,20),(8,10,'2020-11-12','N',4,12),(9,10,'2021-05-23','N',2,24),(10,10,'2021-01-26','N',2,25),(11,7,'2021-01-30','N',5,9),(12,8,'2021-06-25','N',3,17),(13,9,'2021-04-01','N',4,14),(14,6,'2021-03-05','N',4,14),(15,10,'2020-11-11','N',4,14),(16,8,'2021-07-09','N',4,12),(17,9,'2021-03-02','N',5,6),(18,7,'2020-09-21','N',5,6),(19,10,'2020-12-25','N',5,6),(20,7,'2021-01-23','N',2,4),(24,7,'2021-01-23','N',1,31);
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-12 12:01:20
