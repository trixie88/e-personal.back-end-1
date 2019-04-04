CREATE DATABASE  IF NOT EXISTS `tseam_six_3` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tseam_six_3`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: tseam_six_3
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `area` (
  `idarea` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`idarea`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'Voula','Panos 6'),(2,'glfada','ormou 4'),(3,'Marousi','xlois'),(4,'Ampelokipoi','Mixalakopoulou'),(5,'Elliniko','kiprou');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message` (
  `idmessage` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_sender_id` int(11) NOT NULL,
  `fk_receiver_id` int(11) NOT NULL,
  `time_sent` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` longtext NOT NULL,
  PRIMARY KEY (`idmessage`),
  KEY `fk_sender_idx` (`fk_sender_id`),
  KEY `fk_receiver_idx` (`fk_receiver_id`),
  CONSTRAINT `fk_receiver` FOREIGN KEY (`fk_receiver_id`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sender` FOREIGN KEY (`fk_sender_id`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,30,2,'2019-02-11 03:39:51','hi'),(2,30,2,'2019-02-11 03:39:55','hello'),(3,30,2,'2019-02-11 03:39:56','how r u?'),(4,30,2,'2019-02-11 03:39:58','fine u?'),(5,2,30,'2019-02-11 04:39:58','ADFS'),(6,2,43,'2019-02-12 04:39:58','fff'),(7,2,43,'2019-02-13 04:39:58','kkk'),(8,2,30,'2019-02-14 04:39:58','lll'),(9,1,30,'2019-02-15 04:39:58','γηφη'),(10,43,2,'2019-04-01 06:01:52','eeee'),(11,43,1,'2019-04-01 06:01:52','yyyy'),(12,43,1,'2019-04-01 06:02:44','err'),(13,43,1,'2019-04-01 06:02:44','ferf'),(14,43,1,'2019-04-01 06:02:44','fer'),(15,43,1,'2019-04-01 06:02:44','f'),(16,43,1,'2019-04-01 06:02:44','rf'),(17,43,1,'2019-04-01 06:02:44','r'),(18,43,1,'2019-04-01 06:02:44','fer'),(19,43,1,'2019-04-01 06:02:44','rf'),(20,43,1,'2019-04-01 06:02:44','erf'),(21,43,1,'2019-04-01 06:02:44','er'),(22,43,1,'2019-04-01 06:02:44','f'),(23,43,1,'2019-04-01 06:02:44','erf'),(24,43,1,'2019-04-01 06:02:44','er'),(25,43,1,'2019-04-01 06:02:44','fer'),(26,43,1,'2019-04-01 06:02:44','f'),(27,43,1,'2019-04-01 06:02:44','ferfer'),(28,43,1,'2019-04-01 06:02:44','ff'),(29,43,1,'2019-04-01 06:02:44','er'),(30,43,1,'2019-04-01 06:02:44','fer'),(31,43,1,'2019-04-01 06:02:44','f'),(32,43,1,'2019-04-01 06:02:44','er'),(33,43,2,'2019-04-02 03:46:21','gggg'),(34,43,43,'2019-04-02 04:06:29','eeee'),(39,43,43,'2019-04-02 04:09:33','dwedwe'),(40,43,43,'2019-04-02 04:09:52','fwefwe'),(41,43,43,'2019-04-02 04:10:01','feferfer');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `review` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_session_id` int(11) unsigned NOT NULL,
  `comment` longtext NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `rating` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_session_idx` (`fk_session_id`),
  CONSTRAINT `fk_session` FOREIGN KEY (`fk_session_id`) REFERENCES `training_session` (`idtraining_session`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (4,4,'kkkk\n','2019-03-25 23:48:16',2),(6,5,')))))))','2019-03-28 00:16:47',5),(7,6,'haha','2019-03-28 00:33:00',5),(8,11,'eeee','2019-04-01 06:14:24',5),(9,14,'kalo','2019-04-01 06:14:53',4),(10,12,'tromner','2019-04-01 06:14:53',6);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'user'),(2,'trainer'),(3,'admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `token` (
  `token_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `alphanumeric` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `iduser` int(11) NOT NULL,
  `date_of_creation` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`token_id`),
  KEY `fk_idusertoken_idx` (`iduser`),
  CONSTRAINT `fk_idusertoken` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,'njd4fr0FERkjf',1,'2019-03-17 14:13:23'),(2,'ferfre',2,'2019-03-17 15:42:30'),(3,'ee203f6e-b287-4e80-8877-c7375d5c00b0',1,'2019-03-17 16:21:34'),(4,'5a7d7fc4-880c-4a3c-aebc-11a6bbcf2c6b',2,'2019-03-17 16:22:32'),(5,'ec42b52b-a2e2-41a0-8c17-3b69bb625231',1,'2019-03-17 16:41:31'),(6,'cd4da2e0-4594-44e1-a59b-136afe7b0abe',1,'2019-03-17 16:41:34'),(7,'70172c7f-8b79-43f7-b2e1-2fa63fed7119',1,'2019-03-17 16:41:35'),(8,'608e231b-055f-4bb0-b269-b401f20a69c3',1,'2019-03-17 16:41:36'),(9,'46d91896-7e74-4055-a631-f58c3b60add7',1,'2019-03-17 16:43:30'),(10,'a8828f1e-99b9-4ebc-82c7-bbabd130ca13',1,'2019-03-17 17:39:23'),(11,'0e586a22-1948-4354-89e7-899de9570f9a',1,'2019-03-17 17:53:32'),(12,'b0bf1a12-4438-4529-ab2c-89b1abae8315',1,'2019-03-17 18:16:22'),(13,'440a89f6-542b-4ca0-b3a0-e2ccfd304337',1,'2019-03-17 18:16:23'),(14,'51e70b36-12b4-4939-aca3-f097aa4bcb8e',1,'2019-03-17 18:16:24'),(15,'d775c853-a360-4c0b-a7c4-6b598643db1a',1,'2019-03-17 19:42:13'),(17,'871edfee-a3da-478c-b7b7-2308fe32e8d6',18,'2019-03-21 12:59:29'),(18,'abe435c0-0c33-46c8-9e65-6b2424c99b96',18,'2019-03-21 13:02:08'),(19,'83d6ae35-cc5e-444a-a7c3-31609690b7d1',18,'2019-03-21 13:04:17'),(20,'e24484d3-a1f6-418e-a5ef-abc2aad59fda',20,'2019-03-21 13:04:28'),(21,'6606bb47-ce6f-4ede-9d0d-8f5a0e6f4aa5',18,'2019-03-21 13:04:43'),(22,'368aee45-3950-4fc2-bb2d-65256eda1320',21,'2019-03-21 15:00:45'),(23,'39d7218b-ed2d-4182-9d0f-af4334824e88',18,'2019-03-21 15:46:23'),(24,'6e243e40-8438-499d-aa1e-29c8671cd891',18,'2019-03-21 15:47:30'),(25,'986f9395-aee9-4d77-a778-4993a39ccc1d',18,'2019-03-21 15:47:47'),(26,'2754f2d4-c78a-4480-91bf-90309fdf92f6',18,'2019-03-21 15:49:44'),(27,'cb78a76c-8b80-466a-8ae4-e976d4e0f670',30,'2019-03-21 15:52:04'),(28,'760ba5e5-c7c9-4ad7-94b0-3e804d5ac70e',30,'2019-03-21 15:52:07'),(34,'97eb14d0-a66a-4e24-b323-589524384e99',30,'2019-03-24 23:03:56'),(35,'e9ee220b-e247-41bc-a9c1-6ee61de28e69',30,'2019-03-24 23:07:53'),(36,'e0ff052f-35db-47eb-8d25-028927507bb2',30,'2019-03-24 23:10:14'),(37,'95b66a69-6a8c-48d2-bd58-b821cc0f1f18',30,'2019-03-25 00:06:31'),(38,'07a96d72-9a54-4298-8870-9917521339e5',30,'2019-03-25 00:29:07'),(39,'0506ba52-f4bf-4cc9-b2ef-64d72b09a1fb',30,'2019-03-25 01:31:18'),(40,'9ac9247b-adb2-4cf8-9f57-118a77d1fe7a',30,'2019-03-25 17:14:08'),(41,'94d015f5-81ed-4840-b79b-6bb52e2207f0',30,'2019-03-25 23:05:52'),(42,'52db271e-cd66-4a47-b210-16d4828830b4',30,'2019-03-25 23:05:59'),(43,'9635f08f-0a0c-4090-97db-ae50a326bd7b',30,'2019-03-25 23:06:26'),(44,'c23b8059-790e-40af-b6f4-cca5c9e36dee',30,'2019-03-25 23:06:27'),(45,'048ceed3-5f8f-4b7d-978c-7c039acf0a55',30,'2019-03-25 23:06:27'),(47,'5604ed01-aa49-41ba-98a5-6a72eb25fb33',30,'2019-03-27 02:14:14'),(48,'8fa31f52-4d3f-4b64-a628-68acb365d3ef',30,'2019-03-27 02:14:38'),(49,'8217e437-bde4-43b0-a883-eca1f96ec3f1',30,'2019-03-27 19:11:22'),(50,'29d8f4a8-6885-4bed-b111-d458c0718d34',43,'2019-04-01 01:12:45'),(51,'50b34817-ebe4-47ac-a055-b8139b7288d1',43,'2019-04-01 01:31:05'),(52,'da3eabe4-c2ea-4caf-8432-0ea6c72b2f7a',43,'2019-04-01 01:45:01'),(53,'c102112d-593c-41cc-9d71-88da2b84d6d7',43,'2019-04-01 01:46:18'),(54,'cb4ebe5c-fd1d-4fc5-8bab-cd0c31bbad86',43,'2019-04-01 01:46:34'),(55,'85d981a0-adb6-4afa-8abd-a942d3372582',43,'2019-04-01 02:42:42'),(56,'fcfbd869-2cfd-4fe3-84ad-2579879112cf',43,'2019-04-01 04:32:37'),(57,'ff66ca77-1efd-4c22-ab13-a73be8ede92b',43,'2019-04-01 21:21:54'),(58,'64a19855-5dfe-447d-85ae-8b1811f56800',43,'2019-04-02 02:28:59'),(59,'6e7878c4-9696-4f43-90d5-44879a8a901f',43,'2019-04-02 04:15:27'),(60,'0197f61b-6cd0-4c77-b9ae-6faa83c283f6',43,'2019-04-02 05:08:55'),(61,'d7c0c0e3-d121-4f4c-8993-a1cc50caf44c',43,'2019-04-02 05:30:55'),(62,'dccced0d-91ed-46a2-ae32-d943090d490d',43,'2019-04-02 05:47:17'),(63,'d944d44d-48ff-4f5e-be91-74ad18316aa2',43,'2019-04-02 05:57:36'),(64,'a96a836b-e567-4c99-baca-8bc4f9a344c1',43,'2019-04-02 18:44:16'),(65,'95de2689-4922-44fb-b7fc-1b1348460a30',43,'2019-04-02 20:50:00'),(66,'1185c41e-0fa5-409b-8de8-d10c22fd083d',43,'2019-04-02 21:03:04'),(67,'5118e429-6cca-429c-ab9b-76013a356df3',43,'2019-04-03 02:46:12'),(68,'36957438-4aac-4edf-8996-7cbac2c326cf',43,'2019-04-03 03:04:10'),(69,'3f153c0f-d1bf-4d6b-bb3f-3987e3cc2d5a',43,'2019-04-04 03:18:46'),(70,'fe259c80-e1bb-45d9-af05-3745182a0f45',43,'2019-04-04 03:23:55'),(71,'d70f6a14-7040-4378-9d03-9e942f31e1e0',43,'2019-04-04 04:07:58'),(72,'b3594d85-7deb-48dc-b240-dd5aa0953c81',43,'2019-04-04 06:13:14'),(73,'f784744b-d096-4430-b95d-2b92e0b882a3',43,'2019-04-04 19:05:33');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainer_area`
--

DROP TABLE IF EXISTS `trainer_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `trainer_area` (
  `fk_trainer_id` int(11) NOT NULL,
  `fk_area_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_trainer_id`,`fk_area_id`),
  KEY `fk_areaid_idx` (`fk_area_id`),
  CONSTRAINT `fk_area` FOREIGN KEY (`fk_area_id`) REFERENCES `area` (`idarea`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_trainer` FOREIGN KEY (`fk_trainer_id`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer_area`
--

LOCK TABLES `trainer_area` WRITE;
/*!40000 ALTER TABLE `trainer_area` DISABLE KEYS */;
INSERT INTO `trainer_area` VALUES (30,1),(30,2),(43,3),(43,5);
/*!40000 ALTER TABLE `trainer_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainer_specialization`
--

DROP TABLE IF EXISTS `trainer_specialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `trainer_specialization` (
  `fk_trainer_id` int(11) NOT NULL,
  `fk_training_type` int(11) NOT NULL,
  PRIMARY KEY (`fk_trainer_id`,`fk_training_type`),
  KEY `fk_training_type_idx` (`fk_training_type`),
  CONSTRAINT `fk_trainer_type` FOREIGN KEY (`fk_trainer_id`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_training_type` FOREIGN KEY (`fk_training_type`) REFERENCES `training_type` (`idtraining_type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer_specialization`
--

LOCK TABLES `trainer_specialization` WRITE;
/*!40000 ALTER TABLE `trainer_specialization` DISABLE KEYS */;
INSERT INTO `trainer_specialization` VALUES (30,1),(43,1),(30,2),(43,2),(30,3),(43,3),(43,4),(30,5),(43,5);
/*!40000 ALTER TABLE `trainer_specialization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_session`
--

DROP TABLE IF EXISTS `training_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `training_session` (
  `idtraining_session` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_client_id` int(11) NOT NULL,
  `fk_trainer_id` int(11) NOT NULL,
  `fk_area_id` int(11) NOT NULL,
  `fk_training_type` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `is_read` int(11) NOT NULL DEFAULT '0',
  `is_canceled` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idtraining_session`),
  KEY `fk_session_client_idx` (`fk_client_id`),
  KEY `fk_session_trainer_idx` (`fk_trainer_id`),
  KEY `fk_session_type_idx` (`fk_training_type`),
  KEY `fk_area_idx` (`fk_area_id`),
  CONSTRAINT `fk_session_area` FOREIGN KEY (`fk_area_id`) REFERENCES `area` (`idarea`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_session_client` FOREIGN KEY (`fk_client_id`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_session_trainer` FOREIGN KEY (`fk_trainer_id`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_session_type` FOREIGN KEY (`fk_training_type`) REFERENCES `training_type` (`idtraining_type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_session`
--

LOCK TABLES `training_session` WRITE;
/*!40000 ALTER TABLE `training_session` DISABLE KEYS */;
INSERT INTO `training_session` VALUES (4,18,30,1,1,'2019-04-17','16:00:00',1,1),(5,1,30,1,2,'2019-05-17','16:00:00',0,1),(6,1,30,1,1,'2019-06-17','16:00:00',0,1),(7,1,30,1,2,'2019-07-17','16:00:00',0,0),(8,1,30,1,1,'2019-07-17','17:00:00',0,0),(9,43,30,1,1,'2019-05-18','16:00:00',0,0),(10,43,30,1,2,'2019-05-20','17:00:00',0,0),(11,20,43,1,1,'2019-04-11','14:00:00',0,0),(12,43,20,1,2,'2019-04-19','15:00:00',0,0),(13,2,43,1,2,'2019-04-20','18:00:00',0,1),(14,3,43,1,2,'2019-05-22','17:00:00',0,0),(15,3,30,1,2,'2019-05-22','17:00:00',0,0),(16,2,43,1,2,'2019-04-20','18:00:00',0,1),(17,2,43,1,2,'2019-04-20','20:00:00',0,1),(18,2,43,1,2,'2019-04-21','21:00:00',0,1),(19,2,30,1,2,'2019-05-12','12:00:00',0,0),(20,2,30,1,2,'2019-05-12','16:00:00',0,0),(21,2,43,1,2,'2019-04-27','18:00:00',0,0),(22,2,30,1,2,'2019-04-22','11:00:00',0,0),(23,2,30,1,2,'2019-04-22','12:00:00',0,0),(24,3,30,1,2,'2019-05-30','17:00:00',0,0),(25,3,30,1,2,'2019-04-22','14:00:00',0,0),(26,3,30,1,2,'2019-04-22','18:00:00',0,0),(27,43,30,1,1,'2019-04-22','15:00:00',0,0),(28,43,30,1,1,'2019-05-30','13:00:00',0,0),(29,43,30,1,1,'2019-04-22','17:00:00',0,0),(30,43,30,2,2,'2019-04-22','16:00:00',0,0),(31,43,30,1,1,'2019-04-09','17:00:00',0,0),(32,43,30,1,1,'2019-04-22','19:00:00',0,0),(33,43,30,1,1,'2019-04-13','14:00:00',0,0),(34,43,30,1,1,'2019-04-29','16:00:00',0,0),(35,30,43,1,1,'2019-04-02','15:00:00',0,1),(36,43,30,1,1,'2019-04-22','10:00:00',0,0),(37,43,43,2,2,'2019-04-22','16:00:00',0,0);
/*!40000 ALTER TABLE `training_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_type`
--

DROP TABLE IF EXISTS `training_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `training_type` (
  `idtraining_type` int(11) NOT NULL,
  `specialization_title` varchar(45) NOT NULL,
  PRIMARY KEY (`idtraining_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_type`
--

LOCK TABLES `training_type` WRITE;
/*!40000 ALTER TABLE `training_type` DISABLE KEYS */;
INSERT INTO `training_type` VALUES (1,'Boxing'),(2,'Run'),(3,'Pilates'),(4,'Yoga'),(5,'VromoKsilo');
/*!40000 ALTER TABLE `training_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `price` double NOT NULL DEFAULT '0',
  `description` varchar(2000) DEFAULT NULL,
  `fk_role_id` int(11) NOT NULL DEFAULT '1',
  `photo_link` varchar(100) DEFAULT NULL,
  `is_active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_roleid_idx` (`fk_role_id`),
  CONSTRAINT `fk_roleid` FOREIGN KEY (`fk_role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'kok','1','kostas','stas',NULL,10,NULL,1,NULL,0),(2,'lol','2','nick','bell',NULL,0,NULL,1,NULL,1),(3,'theo','123','theo','theo','da@hotmalil.com',10,'trelars',1,NULL,1),(6,'theo3','123','theo','theo','da@hotmalil.com6',10,'trelars',1,NULL,1),(8,'theo33','123efre','theo','theo','da@hotmalil.com36',10,'trelars',1,NULL,1),(10,'theo3d3','123efre','theo','theo','da@hotmalil.com3df6',10,'trelars',1,NULL,1),(12,'theo3d399','123efre','theo','theo','da@hotmalil.jjcom3df6',10,'trelars',1,NULL,1),(13,'theo3d3rr99','123efre','theo','theo','da@hotmalil.jjcrrom3df6',10,'trelars',1,NULL,1),(14,'theo3d3dfrefrr99','123efre','theo','theo','da@hotmalil.jjcfrefrrom3df6',10,'trelars',1,NULL,1),(15,'theo3d3dfrfefrr99','123efre','theo','theo','da@hotmalfil.jjcfrefrrom3df6',10,'trelars',1,NULL,1),(16,'john','12344','theo2','theo3333','ferfre@jfo.com',10,'trelars',1,NULL,1),(17,'joh4n','12344','theo2','theo3333','ferfre@jfo.444com',10,'trelars',1,NULL,1),(18,'paris','cDnN4y7jETNno6mbGt1ETw==','paris','paris','paris@paris.com',0,NULL,1,NULL,1),(20,'tom','cD3UIujyCAU5jdo2lvUiGQ==','Tom','Jones','tom@mail.com',0,NULL,1,NULL,1),(21,'pete','O5ge6jPG8YN15FVxo6s97g==','pete','pete','pete@mail.com',0,NULL,1,NULL,1),(22,'paul','cjE/AwiKavixRg/EE87grw==','paul','paul','paul@mail.com',0,NULL,1,NULL,1),(23,'paul1','1RSUQcS9uDOJbREWb93NJA==','paul1','paul1','paul1@mail.com',0,NULL,1,NULL,1),(24,'pete1','O5ge6jPG8YN15FVxo6s97g==','pete1','pete1','pete1@mail.com',0,NULL,1,NULL,1),(25,'pete4','O5ge6jPG8YN15FVxo6s97g==','pete4','pete4','pete4@mail.com',0,NULL,1,NULL,1),(26,'pete5','O5ge6jPG8YN15FVxo6s97g==','pete5','pete5','pete5@mail.com',0,NULL,1,NULL,1),(27,'pete6','ZuK6dFjEE5pbx1ULpb2LJQ==','pete5','pete5','pete6@mail.com',0,NULL,1,NULL,1),(28,'pete8','uYahYv3pL+1lDCZs0tn7ig==','pete5','pete5','pete8@mail.com',0,NULL,1,NULL,1),(29,'parist','cDnN4y7jETNno6mbGt1ETw==','paris','paris','parits@mail.com',0,NULL,1,NULL,1),(30,'pete0','aCaB0Cp+YwBwJvRWT90THg==','pete5','pete5','pete0@mail.com',0,NULL,1,NULL,1),(31,'paul0','aCaB0Cp+YwBwJvRWT90THg==','pete5','pete5','paul0@mail.com',0,NULL,1,NULL,1),(39,'gus','aCaB0Cp+YwBwJvRWT90THg==','sfdsg','fgdfhsg','gus@mail.com',40,'ndjqj',1,NULL,1),(40,'kostas','iYtqiwn2BQ4G/tGSq4Qckw==','kostas','Stasinos','stas@outlook.com',0,NULL,2,NULL,1),(41,'kio','\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','sfdsg','fgdfhsg','gus555@mail.com',40,'ndjqj',1,NULL,0),(42,'kios','aCaB0Cp+YwBwJvRWT90THg==','sfdsg','fgdfhsg','555@mail.com',40,'ndjqj',1,NULL,0),(43,'trixakias2288','O5ge6jPG8YN15FVxo6s97g==','Theodoross','Bouras','damcbour@hotmail.com',202,NULL,2,NULL,1),(44,'trixakias2299','O5ge6jPG8YN15FVxo6s97g==','theodoros','bouras','damcbour33@hotmail.com',0,NULL,1,NULL,0),(46,'trixakias22889','O5ge6jPG8YN15FVxo6s97g==','theodoros','bouras','damcbourww@hotmail.com',0,NULL,1,NULL,0),(48,'trixakissjsj','O5ge6jPG8YN15FVxo6s97g==','theodoros','bouras','damcbourddd@hotmail.com',0,NULL,1,NULL,0),(49,'trixakias228844','O5ge6jPG8YN15FVxo6s97g==','theodoros','bouras','damcboeeeur@hotmail.com',0,NULL,1,NULL,0),(50,'user558899','O5ge6jPG8YN15FVxo6s97g==','theodoros','bouras','damcbddour@hotmail.com',0,NULL,1,NULL,0),(51,'user44433333','O5ge6jPG8YN15FVxo6s97g==','theodoros','bouras','damcbouffffr@hotmail.com',0,NULL,1,NULL,0),(53,'trixakias22dd88','O5ge6jPG8YN15FVxo6s97g==','theodoros','bouras','damcbrrour@hotmail.com',0,NULL,1,NULL,0),(54,'trixakias228EEEFF8','O5ge6jPG8YN15FVxo6s97g==','theodoros','bouras','damcboDWRFWRFur@hotmail.com',0,NULL,1,NULL,0),(55,'userdwedwed','O5ge6jPG8YN15FVxo6s97g==','THEODOROS','BOURAS','megalouwedwedkos88@gmail.cferom',0,NULL,1,NULL,0),(56,'trixakias22888888899','O5ge6jPG8YN15FVxo6s97g==','theodoros','bouras','damWEDWEDDcbour@hotmail.com',0,NULL,1,NULL,1),(57,'user55588','O5ge6jPG8YN15FVxo6s97g==','THEODOROS','BOURAS','megalouko333s88@gmail.com',0,NULL,1,NULL,0),(58,'trixakias2288cccc','O5ge6jPG8YN15FVxo6s97g==','THEODOROS','BOURAS','damcbdddour@hotmail.com',0,NULL,1,NULL,0),(59,'wedwedwedrffrt','O5ge6jPG8YN15FVxo6s97g==','THEODOROS','BOURAS','megaloukos88@gmail.comferfre',0,NULL,1,NULL,0),(60,'trixakias2288rrrrrr','O5ge6jPG8YN15FVxo6s97g==','theodoros','bouras','damcbferfour@hotmail.com',0,NULL,1,NULL,0),(61,'trixakias22889988778','O5ge6jPG8YN15FVxo6s97g==','theodoros','bouras','damcbou7r@hotmail.com',0,NULL,1,NULL,1),(62,'user223344','O5ge6jPG8YN15FVxo6s97g==','THEODOROS','BOURAS','megaloukos83338@gmail.com',0,NULL,1,NULL,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-04 22:58:53
