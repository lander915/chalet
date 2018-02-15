CREATE DATABASE  IF NOT EXISTS `chalet` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `chalet`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: chalet
-- ------------------------------------------------------
-- Server version	5.7.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `inventaris`
--

DROP TABLE IF EXISTS `inventaris`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventaris` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) NOT NULL,
  `prijs` decimal(10,2) NOT NULL,
  `aantal` int(11) DEFAULT NULL,
  `img` varchar(1000) DEFAULT NULL,
  `verbruik` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventaris`
--

LOCK TABLES `inventaris` WRITE;
/*!40000 ALTER TABLE `inventaris` DISABLE KEYS */;
INSERT INTO `inventaris` VALUES (1,'Jupiler',0.75,41,'jupiler.jpg',3),(2,'Stella',0.75,33,'stella.jpg',1),(3,'Kriek',0.75,29,'kriek.jpg',1),(4,'Kwaremont',1.50,10,'kwaremont.jpg',2),(5,'Duvel',1.50,6,'duvel.jpg',2),(6,'Omer',1.50,8,'default.PNG',0),(9,'test',0.03,2,'test.png',0),(8,'CocaCola',0.75,12,'CocaCola.png',0);
/*!40000 ALTER TABLE `inventaris` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leden`
--

DROP TABLE IF EXISTS `leden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leden` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) NOT NULL,
  `geld` decimal(10,2) DEFAULT '0.00',
  `img` varchar(1000) DEFAULT 'default.PNG',
  `verbruik` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leden`
--

LOCK TABLES `leden` WRITE;
/*!40000 ALTER TABLE `leden` DISABLE KEYS */;
INSERT INTO `leden` VALUES (1,'Lander',11.00,'Lander.png',5.25),(2,'Dante',4.25,'Dante.png',0.00),(3,'Tuur',17.50,'Tuur.png',0.00),(4,'Evert',7.50,'Evert.png',0.00),(5,'Ernesto',-0.75,'Ernesto.png',5.25),(6,'Henri',6.35,'default.PNG',0.00),(9,'Delphine',8.00,'Delphine.png',0.00),(13,'Eva',2.00,'default.PNG',0.00);
/*!40000 ALTER TABLE `leden` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-15 18:35:51
