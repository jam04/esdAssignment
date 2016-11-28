-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 27, 2016 at 06:38 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `xyz_assoc`
--

-- --------------------------------------------------------

--
-- Table structure for table `claims`
--

CREATE TABLE IF NOT EXISTS `claims` (
`id` int(11) NOT NULL,
  `mem_id` text NOT NULL,
  `date` date NOT NULL,
  `rationale` text NOT NULL,
  `status` varchar(10) NOT NULL,
  `amount` float NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `claims`
--

INSERT INTO `claims` (`id`, `mem_id`, `date`, `rationale`, `status`, `amount`) VALUES
(1, 'me-aydin', '2016-11-26', 'Injury', 'APPROVED', 120),
(2, 'me-aydin', '2016-11-26', 'Accident', 'APPROVED', 90),
(3, 'c-aznable', '2015-10-13', 'Eye Surgery', 'APPROVED', 130),
(4, 'c-aznable', '2016-11-26', 'Car crash', 'REJECTED', 200),
(5, 'b-baker', '2015-05-12', 'Broken Bicycle', 'REJECTED', 35),
(6, 'b-baker', '2015-12-08', 'Broken Elbow', 'APPROVED', 90);

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE IF NOT EXISTS `members` (
  `id` text CHARACTER SET ascii NOT NULL,
  `name` text CHARACTER SET ascii,
  `address` text CHARACTER SET ascii,
  `dob` date DEFAULT NULL,
  `dor` date DEFAULT NULL,
  `status` text NOT NULL,
  `balance` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`id`, `name`, `address`, `dob`, `dor`, `status`, `balance`) VALUES
('b-baker', 'Beth Baker', '514, River Way', '1981-09-07', '2017-02-06', 'APPROVED', 0),
('c-aznable', 'Char Aznable', '01, Loum Street', '1979-01-01', '2017-02-08', 'APPROVED', 0),
('j-blocks', 'Joe Blocks', '100, Guest Avenue', '1970-01-01', '2016-11-30', 'APPROVED', 0),
('j-king', 'Jake King', '10, Lions Lane', '1989-03-12', '2016-11-26', 'APPLIED', 10),
('j-white', 'Jane White', '172, Cobble Road', '1993-03-03', '2017-11-26', 'APPROVED', 0),
('j-wright', 'James Wright', '10 Hargrove Avenue', '1990-05-03', '2016-11-26', 'APPLIED', 10),
('me-aydin', 'Mehmet Aydin', '148 Station Rd, London, N3 2SG', '1968-10-20', '2017-03-01', 'APPROVED', 0),
('s-booker', 'Sam Booker', '34 Willow Hill', '1979-06-24', '2016-11-26', 'APPLIED', 10),
('t-hart', 'Thomas Hart', 'Oakdale Cottage', '1954-04-01', '2016-11-26', 'APPLIED', 10),
('t-wang', 'Tianyi Wang', '25 Long Down Avenue', '1994-01-01', '2016-10-31', 'SUSPENDED', 10);

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE IF NOT EXISTS `payments` (
`id` int(11) NOT NULL,
  `mem_id` text NOT NULL,
  `type_of_payment` char(10) NOT NULL,
  `amount` float NOT NULL,
  `date` datetime NOT NULL,
  `status` char(10) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`id`, `mem_id`, `type_of_payment`, `amount`, `date`, `status`) VALUES
(1, 'c-aznable', 'MEMBER', 10, '2016-11-26 00:00:00', 'APPROVED'),
(2, 'b-baker', 'MEMBER', 10, '2016-11-26 00:00:00', 'APPROVED'),
(3, 'j-blocks', 'MEMBER', 10, '2016-11-26 00:00:00', 'APPROVED'),
(4, 'j-king', 'MEMBER', 10, '2016-11-26 00:00:00', 'SUBMITTED'),
(5, 'j-white', 'MEMBER', 10, '2016-11-26 00:00:00', 'APPROVED'),
(6, 'j-wright', 'MEMBER', 10, '2016-11-26 00:00:00', 'SUBMITTED'),
(7, 's-booker', 'MEMBER', 10, '2016-11-26 00:00:00', 'SUBMITTED'),
(8, 't-hart', 'MEMBER', 10, '2016-11-26 00:00:00', 'SUBMITTED'),
(9, 't-wang', 'MEMBER', 10, '2016-11-26 00:00:00', 'APPROVED');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` text CHARACTER SET ascii NOT NULL,
  `password` text NOT NULL,
  `status` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `password`, `status`) VALUES
('admin', 'Admin123', 'ADMIN'),
('b-baker', '070981', 'APPROVED'),
('c-aznable', '010179', 'APPROVED'),
('j-blocks', '010170', 'APPROVED'),
('j-king', '120389', 'APPLIED'),
('j-white', '030393', 'APPROVED'),
('j-wright', '030590', 'APPLIED'),
('me-aydin', '201068', 'APPROVED'),
('s-booker', '240679', 'APPLIED'),
('t-hart', '010454', 'APPLIED'),
('t-wang', '010194', 'SUSPENDED');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `claims`
--
ALTER TABLE `claims`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
 ADD PRIMARY KEY (`id`(10));

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`id`(10));

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `claims`
--
ALTER TABLE `claims`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
