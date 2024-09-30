-- MySQL dump 10.13  Distrib 8.0.39, for Linux (x86_64)
--
-- Host: localhost    Database: natetrystuff
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('1','flopiti','db/changelog/db.changelog-1.0.xml','2024-09-10 10:43:27',1,'EXECUTED','8:116813e91dd0c3fb9075a426a46b012c','renameTable newTableName=meal_schedules, oldTableName=meal_schedule','',NULL,'4.17.0',NULL,NULL,'5979407009');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,_binary '\0',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `day`
--

DROP TABLE IF EXISTS `day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `day` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `in_office` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `day`
--

LOCK TABLES `day` WRITE;
/*!40000 ALTER TABLE `day` DISABLE KEYS */;
INSERT INTO `day` VALUES (1,'2024-08-31',0),(2,'2024-09-01',0),(3,'2024-09-03',1),(4,'2024-09-04',1),(5,'2024-09-05',0),(6,'2024-09-06',1),(7,'2024-09-09',1),(8,'2024-09-10',0),(9,'2024-09-12',1),(10,'2024-09-11',1);
/*!40000 ALTER TABLE `day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient` (
  `ingredient_id` int NOT NULL AUTO_INCREMENT,
  `ingredient_name` varchar(255) NOT NULL,
  PRIMARY KEY (`ingredient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (1,'water'),(2,'Fettucinee'),(3,'Alfredo Sauce'),(4,'White Onion'),(5,'Chicken'),(6,'Onion'),(7,'Garlic'),(8,'Oyster Sauce'),(9,'Celeri'),(10,'Soy Sayce'),(11,'Eggs'),(12,'Beef'),(13,'Shells'),(14,'Tacos mix'),(15,'Sour Cream'),(16,'Tomato'),(17,'Lettuce'),(18,'Red Onions'),(19,'Cheese'),(20,'Ham'),(21,'Bread'),(22,'Margarine'),(23,'Ground Beef'),(24,'Pasta'),(25,'Onions'),(26,'Mozzarella'),(27,'Tomato Sauce'),(28,'Hot Dog Bun'),(29,'Hot Dog sausage'),(30,'Cold Meat'),(31,'Red Onion'),(32,'Hamburger Helper Box'),(33,'Milk'),(34,'Saucisses Costco'),(35,'Costco Dumpling'),(36,'Macaroni'),(37,'Cheddar Brick'),(38,'Ground Meat'),(39,'Rice');
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal` (
  `meal_id` int NOT NULL AUTO_INCREMENT,
  `meal_name` varchar(255) NOT NULL,
  PRIMARY KEY (`meal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
INSERT INTO `meal` VALUES (3,'Fettucinee Alfredo Chicken'),(4,'Chicken Fried Rice'),(6,'Tacos'),(7,'American Breakfast'),(8,'Bolognese Pasta'),(9,'Hot Dogs'),(10,'Sandwich'),(11,'Hamburger Helper'),(13,'Saucisses & Salad'),(14,'Costco Dumplings'),(15,'Macaroni Gratin'),(16,'Butter Chicken');
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal_ingredient`
--

DROP TABLE IF EXISTS `meal_ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal_ingredient` (
  `meal_ingredient_id` int NOT NULL AUTO_INCREMENT,
  `meal_id` int DEFAULT NULL,
  `ingredient_id` int DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`meal_ingredient_id`),
  KEY `meal_id` (`meal_id`),
  KEY `ingredient_id` (`ingredient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal_ingredient`
--

LOCK TABLES `meal_ingredient` WRITE;
/*!40000 ALTER TABLE `meal_ingredient` DISABLE KEYS */;
INSERT INTO `meal_ingredient` VALUES (1,NULL,1,500,'ml'),(5,4,5,2,'Breast'),(6,4,6,2,'u'),(7,4,7,3,'Cloves'),(8,4,8,2,'Tbs'),(9,4,9,0.5,'u'),(10,4,10,3,'Tbs'),(11,4,11,3,'u'),(12,NULL,5,2,'Breast'),(13,NULL,6,2,'u'),(14,NULL,7,3,'Cloves'),(15,NULL,8,2,'Tbs'),(16,NULL,9,0.5,'u'),(17,NULL,10,3,'Tbs'),(18,NULL,11,3,'u'),(19,6,12,500,'g'),(20,6,13,4,'u'),(21,6,14,1,'u'),(22,6,15,200,'ml'),(23,6,16,1,'u'),(24,6,17,2,'leaves'),(25,6,18,1,'u'),(26,6,19,500,'g'),(27,7,11,3,'u'),(28,7,20,3,'slices'),(29,7,21,1,'toast'),(30,7,22,1,'Tbs'),(31,8,23,500,'g'),(32,8,24,500,'g'),(33,8,25,1,'u'),(34,8,7,3,'cloves'),(35,8,26,1,'Tbs'),(36,8,27,500,'ml'),(37,9,28,3,'u'),(38,9,29,3,'u'),(39,9,6,0.5,'u'),(40,10,21,2,'toast'),(41,10,30,200,'g'),(42,10,19,2,'slices'),(43,10,31,0.5,'u'),(44,10,17,1,'Leaf'),(45,11,23,500,'g'),(46,11,32,1,'u'),(47,11,33,2,'cups'),(51,3,2,250,'g'),(52,3,3,0.5,'Sauce'),(53,3,4,1,'u'),(54,13,34,2,'u'),(55,14,35,5,'u'),(56,15,36,500,'g'),(57,15,37,0.25,'u'),(58,15,38,500,'g'),(59,16,39,0.5,'Cup');
/*!40000 ALTER TABLE `meal_ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal_schedules`
--

DROP TABLE IF EXISTS `meal_schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal_schedules` (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `meal_id` int DEFAULT NULL,
  `scheduled_time` datetime NOT NULL,
  `occasion` varchar(255) DEFAULT NULL,
  `prepared` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`schedule_id`),
  KEY `meal_id` (`meal_id`),
  CONSTRAINT `meal_schedules_ibfk_1` FOREIGN KEY (`meal_id`) REFERENCES `meal` (`meal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal_schedules`
--

LOCK TABLES `meal_schedules` WRITE;
/*!40000 ALTER TABLE `meal_schedules` DISABLE KEYS */;
INSERT INTO `meal_schedules` VALUES (5,3,'2024-05-28 00:00:00',NULL,0),(6,3,'2024-05-29 00:00:00',NULL,0),(7,4,'2024-05-31 00:00:00',NULL,0),(8,7,'2024-06-02 00:00:00',NULL,0),(11,9,'2024-06-02 00:00:00',NULL,0),(12,8,'2024-06-03 00:00:00',NULL,0),(13,8,'2024-06-04 00:00:00',NULL,0),(14,10,'2024-06-03 00:00:00',NULL,0),(16,7,'2024-06-01 00:00:00',NULL,0),(17,11,'2024-06-01 00:00:00',NULL,0),(20,10,'2024-06-10 00:00:00',NULL,0),(21,4,'2024-06-10 00:00:00',NULL,0),(26,4,'2024-06-11 00:00:00',NULL,0),(27,4,'2024-06-12 00:00:00',NULL,0),(31,3,'2024-07-16 00:00:00',NULL,0),(36,10,'2024-07-18 00:00:00',NULL,0),(37,11,'2024-07-18 00:00:00',NULL,0),(38,3,'2024-07-17 00:00:00',NULL,0),(39,11,'2024-07-17 00:00:00',NULL,0),(40,4,'2024-07-22 00:00:00',NULL,0),(41,4,'2024-07-23 00:00:00',NULL,0),(42,4,'2024-07-24 00:00:00',NULL,0),(44,4,'2024-07-26 00:00:00',NULL,0),(45,7,'2024-08-05 00:00:00',NULL,0),(47,7,'2024-08-06 00:00:00',NULL,0),(49,14,'2024-08-06 00:00:00',NULL,0),(51,15,'2024-08-04 00:00:00',NULL,0),(52,15,'2024-08-05 00:00:00',NULL,0),(53,4,'2024-08-22 00:00:00',NULL,0),(55,3,'2024-09-03 00:00:00','lunch',0),(56,4,'2024-09-04 00:00:00','lunch',0),(58,16,'2024-09-04 00:00:00',NULL,0),(59,15,'2024-09-06 00:00:00','lunch',0),(60,4,'2024-09-09 00:00:00','lunch',0),(62,4,'2024-09-11 00:00:00','lunch',1);
/*!40000 ALTER TABLE `meal_schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_path`
--

DROP TABLE IF EXISTS `project_path`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_path` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_path`
--

LOCK TABLES `project_path` WRITE;
/*!40000 ALTER TABLE `project_path` DISABLE KEYS */;
INSERT INTO `project_path` VALUES (2,'/dev-projects');
/*!40000 ALTER TABLE `project_path` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-10 21:55:19
