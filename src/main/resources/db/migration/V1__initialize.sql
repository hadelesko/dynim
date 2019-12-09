-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 09, 2019 at 02:40 PM
-- Server version: 5.7.28-0ubuntu0.16.04.2
-- PHP Version: 7.0.33-0ubuntu0.16.04.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dynamicinventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `eflow`
--

CREATE TABLE `eflow` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `quantityflow` double NOT NULL,
  `fournisseur_fournisseur_id` int(11) DEFAULT NULL,
  `material_mat_id` int(11) DEFAULT NULL,
  `mat_id` int(11) DEFAULT NULL,
  `fournisseur_id` int(11) DEFAULT NULL,
  `date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `eflow`
--

INSERT INTO `eflow` (`id`, `description`, `quantityflow`, `fournisseur_fournisseur_id`, `material_mat_id`, `mat_id`, `fournisseur_id`, `date`) VALUES
(6, 'Tubercule douce africaine et sud americaine', 524, 3, 3, 3, NULL, '2018-12-10'),
(7, 'fruit a pepin d origine equoriale', 354, 6, 2, NULL, NULL, '2018-12-10'),
(8, 'Fruit d origine africaine et caraibienne', 534, 3, 2, NULL, NULL, '2018-12-10'),
(9, 'Reception of 127.0Banane from fournisseur 7', 127, 7, 2, NULL, NULL, '2018-12-10 20:18:39.211'),
(10, 'Reception of  509.0 Banane from fournisseur 5', 509, 5, 2, 2, NULL, '2018-12-14 21:21:47.079'),
(11, 'Reception of  56.0 lait de soja from fournisseur 4', 56, 4, 7, 7, NULL, '2018-12-17 09:59:05.887'),
(12, 'Reception of  55.0 mango from fournisseur 2', 55, 2, 9, NULL, NULL, '2018-12-24 19:58:41.833'),
(13, 'Reception of  152.0 yam from fournisseur 5', 152, 5, 10, NULL, NULL, '2018-12-26 10:34:28.686'),
(14, 'Reception of  57.0 yam from fournisseur 5', 57, 5, 10, NULL, NULL, '2018-12-26 12:46:49.972'),
(15, 'Reception of  25.0 manioc from fournisseur 3', 25, 3, 1, NULL, NULL, '2018-12-26 14:47:59.552'),
(16, 'Reception of  143.0 manioc from fournisseur 2', 143, 2, 1, NULL, NULL, '2018-12-26 14:50:51.4'),
(17, 'Reception of  41.0 manioc from fournisseur 4', 41, 4, 1, NULL, NULL, '2018-12-26 14:52:07.674'),
(18, 'Reception of  21.0 manioc from fournisseur 5', 21, 5, 1, 1, NULL, '2018-12-26 14:52:58.541'),
(19, 'Reception of  45.0 yam from fournisseur 8', 45, 8, 10, 10, NULL, '2018-12-26 16:01:28.579'),
(20, 'Reception of  45.0 mango from fournisseur 7', 45, 7, 9, NULL, NULL, '2018-12-26 16:02:41.51'),
(21, 'Reception of  52.0 mango from fournisseur 2', 52, 2, 9, 9, NULL, '2018-12-26 16:05:19.671'),
(22, 'Reception of  120.0 Ginger from fournisseur 8', 120, 8, 4, NULL, NULL, '2018-12-26 16:09:55.875'),
(23, 'Reception of  250.0 Soja Milk from fournisseur 5', 250, 5, 5, 5, NULL, '2018-12-26 16:14:32.151'),
(24, 'Reception of  34.0 Orange from fournisseur 4', 34, 4, 6, NULL, NULL, '2018-12-26 16:16:33.738'),
(25, 'Reception of  300.0 sodabi from fournisseur 4', 300, 4, 8, 8, NULL, '2018-12-26 16:17:29.466'),
(26, 'Reception of  180.0  Orange from fournisseur 6', 180, 6, 6, NULL, NULL, '2019-01-10 08:24:25.024'),
(27, 'Reception of  100.0  Ginger from fournisseur 2', 100, 2, 4, 4, NULL, '2019-01-10 08:30:01.146'),
(28, 'Reception of  100.0  Orange from fournisseur 6', 100, 6, 6, NULL, NULL, '2019-01-10 08:32:47.258'),
(29, 'Reception of  41.0  Orange from fournisseur 2', 41, 2, 6, 6, NULL, '2019-01-10 11:59:39.443');

-- --------------------------------------------------------

--
-- Table structure for table `ex_retour`
--

CREATE TABLE `ex_retour` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `origine` varchar(255) DEFAULT NULL,
  `returned_quantity` double NOT NULL,
  `material_mat_id` int(11) DEFAULT NULL,
  `material_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `flow`
--

CREATE TABLE `flow` (
  `flow_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `flow_quantity` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `location_location_id` int(11) DEFAULT NULL,
  `material_mat_id` int(11) DEFAULT NULL,
  `supplier_supplier_id` int(11) DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  `mat_id` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `destination` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `flow`
--

INSERT INTO `flow` (`flow_id`, `description`, `flow_quantity`, `name`, `location_location_id`, `material_mat_id`, `supplier_supplier_id`, `supplier_id`, `mat_id`, `location_id`, `date`, `destination`) VALUES
(1, 'Reception of BleMon Dec 03 03:21:36 CST 2018', 217, 'Reception Ble', NULL, NULL, NULL, NULL, NULL, NULL, NULL, ''),
(2, 'Reception of maniocMon Dec 03 04:53:16 CST 2018', 0, 'Reception manioc', NULL, NULL, NULL, NULL, NULL, NULL, NULL, ''),
(3, 'Reception of bananeMon Dec 03 04:58:19 CST 2018', 569, 'Reception banane', NULL, NULL, NULL, NULL, NULL, NULL, NULL, ''),
(4, 'Reception of BananeMon Dec 03 05:21:39 CST 2018', 78, 'Reception Banane', NULL, 2, NULL, NULL, NULL, NULL, NULL, ''),
(5, 'Reception of maniocMon Dec 03 05:22:20 CST 2018', 453, 'Reception manioc', NULL, 1, NULL, NULL, NULL, NULL, NULL, ''),
(6, 'Reception of potatosMon Dec 03 05:23:28 CST 2018', 567, 'Reception potatos', NULL, NULL, NULL, NULL, NULL, NULL, NULL, ''),
(7, 'Reception of maniocFri Dec 07 15:00:45 CST 2018', 450, 'Reception manioc', NULL, 1, NULL, NULL, NULL, NULL, NULL, ''),
(8, 'Reception of maniocSat Dec 08 23:16:18 CST 2018', 44, 'Reception manioc', NULL, 1, NULL, NULL, NULL, NULL, NULL, ''),
(9, 'Reception of material ', 53, 'Reception', 9, 1, 1, NULL, NULL, NULL, '2018-12-10 00:00:00', 'warehouse'),
(10, 'Reception of GingerMon Dec 10 18:46:30 CST 2018', 500, 'Reception Ginger', NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-10 18:46:31', 'Warehouse'),
(11, 'Reception of Soja MilkMon Dec 10 19:03:41 CST 2018', 323, 'Reception Soja Milk', NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-10 19:03:41', 'Warehouse'),
(12, 'Reception of OrangeMon Dec 10 19:35:06 CST 2018', 255, 'Reception Orange', NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-10 19:35:06', 'Warehouse'),
(13, 'Reception of BananeMon Dec 10 20:05:36 CST 2018', 127, 'Reception Banane', NULL, 2, NULL, NULL, NULL, NULL, '2018-12-10 20:05:36', 'Warehouse'),
(14, 'Reception of BananeMon Dec 10 20:18:39 CST 2018', 127, 'Reception Banane', NULL, 2, NULL, NULL, NULL, NULL, '2018-12-10 20:18:40', 'Warehouse'),
(15, 'Reception of BananeFri Dec 14 21:21:47 CST 2018', 509, 'Reception Banane', NULL, 2, NULL, NULL, NULL, NULL, '2018-12-14 21:21:47', 'Warehouse'),
(16, 'Reception of lait de sojaMon Dec 17 09:58:37 CST 2018', 56, 'Reception lait de soja', NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-17 09:58:37', 'Warehouse'),
(17, 'Reception of lait de sojaMon Dec 17 09:59:05 CST 2018', 56, 'Reception lait de soja', NULL, 7, NULL, NULL, NULL, NULL, '2018-12-17 09:59:06', 'Warehouse'),
(18, 'Reception of sodabiMon Dec 17 10:01:23 CST 2018', 56, 'Reception sodabi', NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-17 10:01:23', 'Warehouse'),
(19, 'Reception of mangoMon Dec 17 14:28:30 CST 2018', 566, 'Reception mango', NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-17 14:28:30', 'Warehouse'),
(20, 'Reception of mangoMon Dec 24 19:58:41 CST 2018', 55, 'Reception mango', NULL, 9, NULL, NULL, NULL, NULL, '2018-12-24 19:58:42', 'Warehouse'),
(21, 'Reception of yamWed Dec 26 10:31:04 CST 2018', 120, 'Reception yam', NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-26 10:31:05', 'Warehouse'),
(22, 'Reception of yamWed Dec 26 10:34:28 CST 2018', 152, 'Reception yam', NULL, 10, NULL, NULL, NULL, NULL, '2018-12-26 10:34:29', 'Warehouse'),
(23, 'Reception of yamWed Dec 26 12:46:49 CST 2018', 57, 'Reception yam', NULL, 10, NULL, NULL, NULL, NULL, '2018-12-26 12:46:50', 'Warehouse'),
(24, 'Reception of maniocWed Dec 26 14:47:59 CST 2018', 25, 'Reception manioc', NULL, 1, NULL, NULL, NULL, NULL, '2018-12-26 14:48:00', 'Warehouse'),
(25, 'Reception of maniocWed Dec 26 14:50:51 CST 2018', 143, 'Reception manioc', NULL, 1, NULL, NULL, NULL, NULL, '2018-12-26 14:50:51', 'Warehouse'),
(26, 'Reception of maniocWed Dec 26 14:52:07 CST 2018', 41, 'Reception manioc', NULL, 1, NULL, NULL, NULL, NULL, '2018-12-26 14:52:08', 'Warehouse'),
(27, 'Reception of maniocWed Dec 26 14:52:58 CST 2018', 21, 'Reception manioc', NULL, 1, NULL, NULL, NULL, NULL, '2018-12-26 14:52:59', 'Warehouse'),
(28, 'Reception of yamWed Dec 26 16:01:28 CST 2018', 45, 'Reception yam', NULL, 10, NULL, NULL, NULL, NULL, '2018-12-26 16:01:29', 'Warehouse'),
(29, 'Reception of mangoWed Dec 26 16:02:41 CST 2018', 45, 'Reception mango', NULL, 9, NULL, NULL, NULL, NULL, '2018-12-26 16:02:42', 'Warehouse'),
(30, 'Reception of mangoWed Dec 26 16:05:19 CST 2018', 52, 'Reception mango', NULL, 9, NULL, NULL, NULL, NULL, '2018-12-26 16:05:20', 'Warehouse'),
(31, 'Reception of GingerWed Dec 26 16:09:55 CST 2018', 120, 'Reception Ginger', NULL, 4, NULL, NULL, NULL, NULL, '2018-12-26 16:09:56', 'Warehouse'),
(32, 'Reception of Soja MilkWed Dec 26 16:14:32 CST 2018', 250, 'Reception Soja Milk', NULL, 5, NULL, NULL, NULL, NULL, '2018-12-26 16:14:32', 'Warehouse'),
(33, 'Reception of OrangeWed Dec 26 16:16:33 CST 2018', 34, 'Reception Orange', NULL, 6, NULL, NULL, NULL, NULL, '2018-12-26 16:16:34', 'Warehouse'),
(34, 'Reception of sodabiWed Dec 26 16:17:29 CST 2018', 300, 'Reception sodabi', NULL, 8, NULL, NULL, NULL, NULL, '2018-12-26 16:17:29', 'Warehouse'),
(35, 'Reception of ignameSun Jan 06 17:55:45 CST 2019', 564, 'Reception igname', NULL, NULL, NULL, NULL, NULL, NULL, '2019-01-06 17:55:46', 'Warehouse'),
(36, 'Reception of OrangeThu Jan 10 08:24:25 CST 2019', 180, 'Reception Orange', NULL, 6, NULL, NULL, NULL, NULL, '2019-01-10 08:24:25', 'Warehouse'),
(37, 'Reception of GingerThu Jan 10 08:30:01 CST 2019', 100, 'Reception Ginger', NULL, 4, NULL, NULL, NULL, NULL, '2019-01-10 08:30:01', 'Warehouse'),
(38, 'Reception of OrangeThu Jan 10 08:32:47 CST 2019', 100, 'Reception Orange', NULL, 6, NULL, NULL, NULL, NULL, '2019-01-10 08:32:47', 'Warehouse'),
(39, 'Reception of OrangeThu Jan 10 11:59:39 CST 2019', 41, 'Reception Orange', NULL, 6, NULL, NULL, NULL, NULL, '2019-01-10 11:59:39', 'Warehouse'),
(40, 'Reception of haricotThu Mar 07 11:00:04 CST 2019', 250, 'Reception haricot', NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-07 11:00:04', 'Warehouse'),
(41, 'Reception of papayaThu Mar 07 11:25:00 CST 2019', 50, 'Reception papaya', NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-07 11:25:00', 'Warehouse');

-- --------------------------------------------------------

--
-- Table structure for table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `fournisseur_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `fax` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fournisseur`
--

INSERT INTO `fournisseur` (`fournisseur_id`, `address`, `email`, `fax`, `name`, `phone`) VALUES
(2, '2341 AVENUE DE LALBERTE', 'benoi@leriche.com', 1524373848990, 'Benoit le riche', 1827373637734),
(3, 'Duisseldorf 7634', 'gregorie@gmail.com', 6353647585989, 'Gregory', 53436464883939),
(4, 'Brijan Ave Duisburg 5344', 'francis@benoit.com', 635474745859, 'Briangunga ', 354804958821),
(5, 'Avenue de la liberation rue 75', 'otp@phosphate.tg', 2283548739, 'OTP TOGO', 22845673897),
(6, 'Albert Avenue 245', 'Alberto@togovich.com', 3635987301, 'Albertinoch', 6755342899),
(7, 'Gregorian Allee 22343', 'gottier@francoise.com', 28837484859, 'Gottier', 36458293834),
(8, 'Duisseldorf 76782', 'legrandet@gmail.com', 162378938347, 'GRANDET', 13242637384893);

-- --------------------------------------------------------

--
-- Table structure for table `fournisseur_materials`
--

CREATE TABLE `fournisseur_materials` (
  `fournisseurs_fournisseur_id` int(11) NOT NULL,
  `materials_mat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fournisseur_materials`
--

INSERT INTO `fournisseur_materials` (`fournisseurs_fournisseur_id`, `materials_mat_id`) VALUES
(3, 2),
(7, 3),
(7, 9),
(8, 7),
(8, 10),
(8, 4),
(5, 1),
(5, 5),
(4, 1),
(4, 6),
(4, 8),
(6, 2),
(6, 6),
(6, 6),
(2, 1),
(2, 9),
(2, 4),
(2, 6);

-- --------------------------------------------------------

--
-- Table structure for table `in_retour`
--

CREATE TABLE `in_retour` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `origine` varchar(255) DEFAULT NULL,
  `returned_quantity` double NOT NULL,
  `material_mat_id` int(11) DEFAULT NULL,
  `material_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `location_id` int(11) NOT NULL,
  `location_stock` double NOT NULL,
  `name` varchar(255) NOT NULL,
  `material_mat_id` int(11) DEFAULT NULL,
  `material_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`location_id`, `location_stock`, `name`, `material_mat_id`, `material_id`) VALUES
(1, 920, 'TRD', 1, NULL),
(2, 2298, 'GRE', 2, NULL),
(3, 1091, 'FRB', 3, NULL),
(5, 0, 'GGT1', 3, 3),
(6, 775, 'TED', 1, NULL),
(8, 26, 'GOSS', 1, NULL),
(9, 400, 'FRC', 1, NULL),
(10, 600, 'IGDR', 4, NULL),
(11, 578, 'DAS', 5, NULL),
(12, 610, 'GFO', 6, NULL),
(13, 56, 'BB123', 8, NULL),
(14, 167, 'FRT01', 9, NULL),
(15, 454, 'BGR1', 9, NULL),
(17, 112, 'DDR1', 7, 7),
(18, 374, 'BB002', 10, NULL),
(19, 20, 'BRAB', 4, NULL),
(20, 564, 'WQR', 11, 11),
(21, 0, 'BWQR', 1, 1),
(22, 300, 'FFTE', 8, 8),
(23, 40, 'frrr', 13, 13);

-- --------------------------------------------------------

--
-- Table structure for table `material_order`
--

CREATE TABLE `material_order` (
  `order_id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `destination` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_quantity` double NOT NULL,
  `material_mat_id` int(11) DEFAULT NULL,
  `mat_id` int(11) DEFAULT NULL,
  `orderd_quantity` double NOT NULL,
  `is_picked` bit(1) NOT NULL,
  `ordered_quantity` double NOT NULL,
  `is_delivered` bit(1) NOT NULL,
  `delivered` bit(1) NOT NULL,
  `picked` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `mmaterial`
--

CREATE TABLE `mmaterial` (
  `mat_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `mat_name` varchar(255) DEFAULT NULL,
  `stock` double NOT NULL,
  `supplier_supplier_id` int(11) DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mmaterial`
--

INSERT INTO `mmaterial` (`mat_id`, `description`, `mat_name`, `stock`, `supplier_supplier_id`, `supplier_id`) VALUES
(1, 'Tubercule douce africaine et sud americaine', 'manioc', 1431, NULL, 1),
(2, 'Fruit d origine africaine riche en vitamine c', 'Banane', 2298, NULL, 1),
(3, 'Tubercule douce africaine et sud americaine', 'potatos', 1091, 1, 1),
(4, 'condiment ', 'Ginger', 720, NULL, 2),
(5, 'Lait riche an proteine vegetal', 'Soja Milk', 573, NULL, 3),
(6, 'Fruit d origine africaine riche en vitamine c', 'Orange', 610, NULL, 2),
(7, 'fron sja', 'lait de soja', 112, NULL, 2),
(8, 'Boisson alcolise', 'sodabi', 356, NULL, 2),
(9, 'Fruit d origine africaine riche en vitamine c', 'mango', 718, NULL, 3),
(10, 'Tubercule douce africaine for fufu', 'yam', 374, NULL, 3),
(11, 'Tubercle  bassar', 'igname', 564, 2, 2),
(12, 'legume fereuse', 'haricot', 250, 2, 2),
(13, 'spanish fructs', 'papaya', 50, 4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `supplier_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `fax` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`supplier_id`, `address`, `email`, `fax`, `name`, `phone`) VALUES
(1, 'Roenteger Av 2736', 'roentger@roenteger.com', 53625342629, 'Phillip le sage', 3541213443546),
(2, '36458 Timber Ave ', 'amaro@amaro.com', 2536485759, 'Pierre Amaro', 4564756238),
(3, '35 Saint Mikel Av Four', 'robert@legrand.com', 35464787585, 'Robert le grand', 25348214589),
(4, 'Tangieta Benin', 'adbr@ghfmail.com', 25243794747, 'Abraham', 25346828283);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `eflow`
--
ALTER TABLE `eflow`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKms3oupjtv9rwmnuhk3611cuhw` (`fournisseur_fournisseur_id`),
  ADD KEY `FKprtf5baw1fi78ed3s777lbu6n` (`material_mat_id`),
  ADD KEY `FKmshc6p408j941ojm0l1bpg3dq` (`mat_id`),
  ADD KEY `FKrf3tondklf43j2a2etj9s8i89` (`fournisseur_id`);

--
-- Indexes for table `ex_retour`
--
ALTER TABLE `ex_retour`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK39kfjg1562hw6wcubu2x3dm6` (`material_mat_id`),
  ADD KEY `FKmxraofcx5h4ffspuf71bamxap` (`material_id`);

--
-- Indexes for table `flow`
--
ALTER TABLE `flow`
  ADD PRIMARY KEY (`flow_id`),
  ADD KEY `FK39mtyc3var50i48fl33n21ly` (`location_location_id`),
  ADD KEY `FKqqjjpte8jf2s8li5xsg82kx3w` (`material_mat_id`),
  ADD KEY `FK9344qn55hw3gpf9fjrcrqe8sv` (`supplier_supplier_id`),
  ADD KEY `FKrv2pjx1rv1rcxuxs4xeng0k7v` (`supplier_id`),
  ADD KEY `FK2ew8j05u11onjr66nfa91ma5m` (`mat_id`),
  ADD KEY `FKprmob3ej8oa51w5ulnji9hv8q` (`location_id`);

--
-- Indexes for table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`fournisseur_id`);

--
-- Indexes for table `fournisseur_materials`
--
ALTER TABLE `fournisseur_materials`
  ADD KEY `FK6qipqfd3d4dnd1su1tnrak14t` (`materials_mat_id`),
  ADD KEY `FKfy4eg7kqyslrymphrkpkypb9l` (`fournisseurs_fournisseur_id`);

--
-- Indexes for table `in_retour`
--
ALTER TABLE `in_retour`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkxhcboxh2cuhgc1vmcrvow8co` (`material_mat_id`),
  ADD KEY `FKe4c643poeqqjejiwrgrvxlgbt` (`material_id`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`location_id`),
  ADD KEY `FKcikuirr3j05wf2di0rjt8efw7` (`material_mat_id`),
  ADD KEY `FKf94dmrmesoc4nt2kulj1rg38f` (`material_id`);

--
-- Indexes for table `material_order`
--
ALTER TABLE `material_order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `FKgaoxdasj4y81ibqw8jglxedqc` (`material_mat_id`),
  ADD KEY `FKkdidj20sg4j2731mvwrrie1uu` (`mat_id`);

--
-- Indexes for table `mmaterial`
--
ALTER TABLE `mmaterial`
  ADD PRIMARY KEY (`mat_id`),
  ADD KEY `FK61850rgoo1bmnr6i0tdj3m3tt` (`supplier_supplier_id`),
  ADD KEY `FK6jov2e8xgkptltd9389xed6hw` (`supplier_id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`supplier_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `eflow`
--
ALTER TABLE `eflow`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `ex_retour`
--
ALTER TABLE `ex_retour`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `flow`
--
ALTER TABLE `flow`
  MODIFY `flow_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;
--
-- AUTO_INCREMENT for table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `fournisseur_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `in_retour`
--
ALTER TABLE `in_retour`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `location_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `material_order`
--
ALTER TABLE `material_order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mmaterial`
--
ALTER TABLE `mmaterial`
  MODIFY `mat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `supplier_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `eflow`
--
ALTER TABLE `eflow`
  ADD CONSTRAINT `FKms3oupjtv9rwmnuhk3611cuhw` FOREIGN KEY (`fournisseur_fournisseur_id`) REFERENCES `fournisseur` (`fournisseur_id`),
  ADD CONSTRAINT `FKmshc6p408j941ojm0l1bpg3dq` FOREIGN KEY (`mat_id`) REFERENCES `mmaterial` (`mat_id`),
  ADD CONSTRAINT `FKprtf5baw1fi78ed3s777lbu6n` FOREIGN KEY (`material_mat_id`) REFERENCES `mmaterial` (`mat_id`),
  ADD CONSTRAINT `FKrf3tondklf43j2a2etj9s8i89` FOREIGN KEY (`fournisseur_id`) REFERENCES `fournisseur` (`fournisseur_id`);

--
-- Constraints for table `ex_retour`
--
ALTER TABLE `ex_retour`
  ADD CONSTRAINT `FK39kfjg1562hw6wcubu2x3dm6` FOREIGN KEY (`material_mat_id`) REFERENCES `mmaterial` (`mat_id`),
  ADD CONSTRAINT `FKmxraofcx5h4ffspuf71bamxap` FOREIGN KEY (`material_id`) REFERENCES `mmaterial` (`mat_id`);

--
-- Constraints for table `flow`
--
ALTER TABLE `flow`
  ADD CONSTRAINT `FK2ew8j05u11onjr66nfa91ma5m` FOREIGN KEY (`mat_id`) REFERENCES `mmaterial` (`mat_id`),
  ADD CONSTRAINT `FK39mtyc3var50i48fl33n21ly` FOREIGN KEY (`location_location_id`) REFERENCES `location` (`location_id`),
  ADD CONSTRAINT `FK9344qn55hw3gpf9fjrcrqe8sv` FOREIGN KEY (`supplier_supplier_id`) REFERENCES `supplier` (`supplier_id`),
  ADD CONSTRAINT `FKprmob3ej8oa51w5ulnji9hv8q` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`),
  ADD CONSTRAINT `FKqqjjpte8jf2s8li5xsg82kx3w` FOREIGN KEY (`material_mat_id`) REFERENCES `mmaterial` (`mat_id`),
  ADD CONSTRAINT `FKrv2pjx1rv1rcxuxs4xeng0k7v` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`);

--
-- Constraints for table `fournisseur_materials`
--
ALTER TABLE `fournisseur_materials`
  ADD CONSTRAINT `FK6qipqfd3d4dnd1su1tnrak14t` FOREIGN KEY (`materials_mat_id`) REFERENCES `mmaterial` (`mat_id`),
  ADD CONSTRAINT `FKfy4eg7kqyslrymphrkpkypb9l` FOREIGN KEY (`fournisseurs_fournisseur_id`) REFERENCES `fournisseur` (`fournisseur_id`);

--
-- Constraints for table `in_retour`
--
ALTER TABLE `in_retour`
  ADD CONSTRAINT `FKe4c643poeqqjejiwrgrvxlgbt` FOREIGN KEY (`material_id`) REFERENCES `mmaterial` (`mat_id`),
  ADD CONSTRAINT `FKkxhcboxh2cuhgc1vmcrvow8co` FOREIGN KEY (`material_mat_id`) REFERENCES `mmaterial` (`mat_id`);

--
-- Constraints for table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `FKcikuirr3j05wf2di0rjt8efw7` FOREIGN KEY (`material_mat_id`) REFERENCES `mmaterial` (`mat_id`),
  ADD CONSTRAINT `FKf94dmrmesoc4nt2kulj1rg38f` FOREIGN KEY (`material_id`) REFERENCES `mmaterial` (`mat_id`);

--
-- Constraints for table `material_order`
--
ALTER TABLE `material_order`
  ADD CONSTRAINT `FKgaoxdasj4y81ibqw8jglxedqc` FOREIGN KEY (`material_mat_id`) REFERENCES `mmaterial` (`mat_id`),
  ADD CONSTRAINT `FKkdidj20sg4j2731mvwrrie1uu` FOREIGN KEY (`mat_id`) REFERENCES `mmaterial` (`mat_id`);

--
-- Constraints for table `mmaterial`
--
ALTER TABLE `mmaterial`
  ADD CONSTRAINT `FK61850rgoo1bmnr6i0tdj3m3tt` FOREIGN KEY (`supplier_supplier_id`) REFERENCES `supplier` (`supplier_id`),
  ADD CONSTRAINT `FK6jov2e8xgkptltd9389xed6hw` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
