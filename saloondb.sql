-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 16, 2019 at 06:19 PM
-- Server version: 5.7.27-0ubuntu0.18.04.1
-- PHP Version: 7.2.24-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `saloondb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `custid` int(11) NOT NULL,
  `saloon_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `custid` int(11) NOT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `style` varchar(25) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `dupdated` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`custid`, `fname`, `lname`, `email`, `sex`, `style`, `phone`, `address`, `dupdated`) VALUES
(2, 'AINE', 'BRIAN', 'brian@brain.com', 'Male', 'AFRO-BIG', '0782717171', 'KASH', '2019-11-11');

-- --------------------------------------------------------

--
-- Table structure for table `defaults`
--

CREATE TABLE `defaults` (
  `defid` int(11) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `defaults`
--

INSERT INTO `defaults` (`defid`, `fname`, `lname`, `phone`, `date`, `amount`) VALUES
(1, 'tumusiime', 'ezra', '9898989898', '2014-11-19', 54545),
(2, 'Tumusiime', 'Ezra', '0781109108', '2014-11-19', 1500),
(3, 'Natwijuka', 'Isophel', '0789787878', '2014-11-19', 2000),
(4, 'qwerty', 'junior', '0781109108', '2014-11-19', 2000);

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

CREATE TABLE `discount` (
  `discid` int(11) NOT NULL,
  `datefrom` varchar(50) NOT NULL,
  `dateto` varchar(50) NOT NULL,
  `amt2` int(15) NOT NULL,
  `stylename` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `discount`
--

INSERT INTO `discount` (`discid`, `datefrom`, `dateto`, `amt2`, `stylename`) VALUES
(1, 'Mon Nov 04 16:08:04 EAT 2019', 'Mon Nov 11 16:04:33 EAT 2019', 1000, 'AFRO-BIG'),
(2, '14/11/19', '14/11/19', 1000, 'Brush-big'),
(3, '14/11/19', '14/11/19', 2, 'DRADES');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `payid` int(11) NOT NULL,
  `amt` varchar(25) DEFAULT NULL,
  `debt` varchar(25) DEFAULT NULL,
  `bal` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `recid` int(11) NOT NULL,
  `dateofissue` varchar(45) DEFAULT NULL,
  `to` varchar(45) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `payments_payid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `roleid` int(11) NOT NULL,
  `rolename` varchar(45) DEFAULT NULL,
  `uid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `saloon`
--

CREATE TABLE `saloon` (
  `id` int(11) NOT NULL,
  `saloonname` varchar(45) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `saloon`
--

INSERT INTO `saloon` (`id`, `saloonname`, `phone`, `location`) VALUES
(1, 'QWERTY', '1234567890', 'KASH2');

-- --------------------------------------------------------

--
-- Table structure for table `styles`
--

CREATE TABLE `styles` (
  `stylename` varchar(45) NOT NULL,
  `amount` varchar(45) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `styles`
--

INSERT INTO `styles` (`stylename`, `amount`, `sex`, `description`) VALUES
('AFRO-BIG', '35000', 'Female', 'cool for the youth ladies'),
('AFRO-SMALL', '30000', 'Female', 'cool for all afriacan women'),
('Brush-big', '10000', 'Male', 'cool for gentle-men'),
('DRADES', '50000', 'Both', 'for fake people only'),
('KABUZI', '1000', 'Male', 'men style'),
('MOHAC', '2000', 'Male', 'nice style for fake niggas');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `uid` int(11) NOT NULL,
  `uname` varchar(25) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `rolename` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uid`, `uname`, `password`, `gender`, `phone`, `fname`, `lname`, `rolename`) VALUES
(4, 'junior', 'junior2018', 'Male', '0781109107', 'Tumusiime', 'Ezra', 'Admin'),
(6, 'aine', 'qwertyuiop', 'Male', '0781109108', 'AINE', 'BRAIN', 'Worker'),
(7, 'jet', 'junior2018', 'Male', '0705040545', 'JET', 'JUNIOR', 'Worker'),
(8, 'shafik', 'junior2018', 'Male', '0781109107', 'MATOVU', 'SHAFIK', 'Admin'),
(9, 'doctor', 'nabaasa1234', 'Male', '0781234234', 'NABAASA', 'EVERIST', 'Admin'),
(10, 'tp', 'tandekapaul', 'Male', '0898898989', 'TANDEKA', 'PAUL', 'Worker'),
(11, 'isan', 'qwerty1234', 'Male', '0781818181', 'NATWIJUKA', 'ISOPHEL', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `worker`
--

CREATE TABLE `worker` (
  `workerid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `discid` int(11) NOT NULL,
  `payid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminid`),
  ADD KEY `fk_admin_user1_idx` (`uid`),
  ADD KEY `fk_admin_customer1_idx` (`custid`),
  ADD KEY `fk_admin_saloon1_idx` (`saloon_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`custid`);

--
-- Indexes for table `defaults`
--
ALTER TABLE `defaults`
  ADD PRIMARY KEY (`defid`);

--
-- Indexes for table `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`discid`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payid`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`recid`),
  ADD KEY `fk_receipt_payments1_idx` (`payments_payid`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`roleid`),
  ADD KEY `fk_roles_user1_idx` (`uid`);

--
-- Indexes for table `saloon`
--
ALTER TABLE `saloon`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `styles`
--
ALTER TABLE `styles`
  ADD PRIMARY KEY (`stylename`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uid`);

--
-- Indexes for table `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`workerid`),
  ADD KEY `fk_worker_user1_idx` (`uid`),
  ADD KEY `fk_worker_discount1_idx` (`discid`),
  ADD KEY `fk_worker_payments1_idx` (`payid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `adminid` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `custid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `defaults`
--
ALTER TABLE `defaults`
  MODIFY `defid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `discount`
--
ALTER TABLE `discount`
  MODIFY `discid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `payid` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `receipt`
--
ALTER TABLE `receipt`
  MODIFY `recid` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `roleid` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `saloon`
--
ALTER TABLE `saloon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `fk_admin_customer1` FOREIGN KEY (`custid`) REFERENCES `customer` (`custid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_admin_saloon1` FOREIGN KEY (`saloon_id`) REFERENCES `mydb`.`saloon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_admin_user1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `receipt`
--
ALTER TABLE `receipt`
  ADD CONSTRAINT `fk_receipt_payments1` FOREIGN KEY (`payments_payid`) REFERENCES `payments` (`payid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `roles`
--
ALTER TABLE `roles`
  ADD CONSTRAINT `fk_roles_user1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `worker`
--
ALTER TABLE `worker`
  ADD CONSTRAINT `fk_worker_discount1` FOREIGN KEY (`discid`) REFERENCES `discount` (`discid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_worker_payments1` FOREIGN KEY (`payid`) REFERENCES `payments` (`payid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_worker_user1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
