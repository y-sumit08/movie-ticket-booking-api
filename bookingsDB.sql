-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: localhost    Database: bookmyshow
-- ------------------------------------------------------
-- Server version	8.0.19



--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Inserting data into table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` VALUES (37),(37),(37),(37);

--
-- Table structure for table `table_booking`
--

DROP TABLE IF EXISTS `table_booking`;
CREATE TABLE `table_booking` (
  `show_id` bigint NOT NULL,
  `a1` int NOT NULL,
  `a2` int NOT NULL,
  `a3` int NOT NULL,
  `a4` int NOT NULL,
  `a5` int NOT NULL,
  `b1` int NOT NULL,
  `b2` int NOT NULL,
  `b3` int NOT NULL,
  `b4` int NOT NULL,
  `b5` int NOT NULL,
  `c1` int NOT NULL,
  `c2` int NOT NULL,
  `c3` int NOT NULL,
  `c4` int NOT NULL,
  `c5` int NOT NULL,
  PRIMARY KEY (`show_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Inserting data into table `table_booking`
--


INSERT INTO `table_booking` VALUES (32,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(33,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(34,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(35,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0),(36,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);

--
-- Table structure for table `table_city`
--

DROP TABLE IF EXISTS `table_city`;
CREATE TABLE `table_city` (
  `c_id` bigint NOT NULL,
  `c_name` varchar(255) NOT NULL,
  `c_pincode` varchar(255) NOT NULL,
  `c_state` varchar(255) NOT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Inserting data into table `table_city`
--

INSERT INTO `table_city` VALUES (1,'Bengaluru','590056','Karnataka'),(2,'Belagavi','590059','Karnataka'),(3,'Maysuru','590103','Karnataka');

--
-- Table structure for table `table_movie`
--

DROP TABLE IF EXISTS `table_movie`;
CREATE TABLE `table_movie` (
  `m_id` bigint NOT NULL,
  `m_description` varchar(255) DEFAULT NULL,
  `m_director` varchar(255) NOT NULL,
  `m_name` varchar(255) NOT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Inserting data into table `table_movie`
--

INSERT INTO `table_movie` VALUES (10,'description about the WAR 3 movie here','WAR 3 directors','WAR 3'),(11,'description about the WAR 3 movie here','Remo','Shtreed Dance 3');

--
-- Table structure for table `table_show`
--

DROP TABLE IF EXISTS `table_show`;
CREATE TABLE `table_show` (
  `show_id` bigint NOT NULL,
  `show_time` varchar(255) NOT NULL,
  `the_movie_m_id` bigint DEFAULT NULL,
  `the_theatre_t_id` bigint DEFAULT NULL,
  PRIMARY KEY (`show_id`),
  KEY `FKsayo2xxw82i3o8h7twmmps3um` (`the_movie_m_id`),
  KEY `FKeg2ca18ko9iie9lo3h6ay2ury` (`the_theatre_t_id`),
  CONSTRAINT `FKeg2ca18ko9iie9lo3h6ay2ury` FOREIGN KEY (`the_theatre_t_id`) REFERENCES `table_theatre` (`t_id`),
  CONSTRAINT `FKsayo2xxw82i3o8h7twmmps3um` FOREIGN KEY (`the_movie_m_id`) REFERENCES `table_movie` (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Inserting data into table `table_show`
--

INSERT INTO `table_show` VALUES (12,'4/4/2020 - 09:00 AM',10,4),(13,'4/4/2020 - 12:00 PM',10,4),(14,'4/4/2020 - 03:00 PM',10,4),(15,'4/4/2020 - 06:00 PM',10,4),(16,'4/4/2020 - 09:00 PM',10,4),(17,'4/4/2020 - 3:00 PM',11,4),(18,'4/4/2020 - 9:00 PM',11,4),(19,'4/4/2020 - 9:00 AM',10,5),(20,'4/4/2020 - 9:00 PM',10,5),(21,'4/4/2020 - 9:00 PM',10,6),(22,'4/4/2020 - 9:00 PM',10,7),(23,'4/4/2020 - 9:00 PM',10,8),(24,'4/4/2020 - 3:00 PM',10,9),(25,'4/4/2020 - 6:00 PM',11,9),(26,'4/4/2020 - 6:00 PM',11,8),(27,'4/4/2020 - 6:00 PM',11,7),(28,'4/4/2020 - 6:00 PM',11,6),(29,'4/4/2020 - 6:00 PM',11,5),(30,'4/4/2020 - 6:00 PM',11,4),(32,'4/4/2020 - 6:00 PM',10,9),(33,'4/4/2020 - 6:00 PM',10,9),(34,'4/4/2020 - 6:00 PM',10,4),(35,'4/4/2020 - 6:00 PM',10,5),(36,'4/4/2020 - 6:00 PM',10,6);

--
-- Table structure for table `table_theatre`
--

DROP TABLE IF EXISTS `table_theatre`;
CREATE TABLE `table_theatre` (
  `t_id` bigint NOT NULL,
  `t_area` varchar(255) NOT NULL,
  `t_name` varchar(255) NOT NULL,
  `city_c_id` bigint DEFAULT NULL,
  PRIMARY KEY (`t_id`),
  KEY `FKtflvxj66v6b9kicc9kwh3js3g` (`city_c_id`),
  CONSTRAINT `FKtflvxj66v6b9kicc9kwh3js3g` FOREIGN KEY (`city_c_id`) REFERENCES `table_city` (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Inserting data into table `table_theatre`
--
INSERT INTO `table_theatre` VALUES (4,'Koramangala','Finox mall',1),(5,'Koramangala','PVR Kormangala',1),(6,'RD Collage POST','Shrinival theatre',1),(7,'Head post office','INOX Cinema',2),(8,'Goods shed Road','Chitra theatre',2),(9,'Old school theatre road','Rajkamal theatre',3);

