-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 08, 2018 at 11:26 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sensor_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `sensor_data`
--

CREATE TABLE `sensor_data` (
  `id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `data_type` varchar(50) NOT NULL,
  `data` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sensor_data`
--

INSERT INTO `sensor_data` (`id`, `time`, `data_type`, `data`) VALUES
(151, '2018-10-09 04:24:11', 'GPS Coordinate', '10.423000000000002, -20.083998333333334'),
(152, '2018-10-09 04:24:14', 'GPS Coordinate', '10.423000000000002, -20.083998333333334'),
(153, '2018-10-09 04:24:24', 'GPS Coordinate', '10.423000000000002, -20.083998333333334'),
(154, '2018-10-09 04:24:32', 'GPS Coordinate', '66.423, -121.08400000000002'),
(155, '2018-10-09 04:24:42', 'GPS Coordinate', '66.423, -121.08400000000002'),
(156, '2018-10-09 04:24:52', 'GPS Coordinate', '66.423, -121.08400000000002'),
(157, '2018-10-09 04:25:02', 'GPS Coordinate', '66.423, -121.08400000000002'),
(158, '2018-10-09 04:25:12', 'GPS Coordinate', '66.423, -121.08400000000002'),
(159, '2018-10-09 04:25:22', 'GPS Coordinate', '66.423, -121.08400000000002'),
(160, '2018-10-09 04:25:32', 'GPS Coordinate', '66.423, -121.08400000000002'),
(161, '2018-10-09 04:25:42', 'GPS Coordinate', '66.423, -121.08400000000002');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sensor_data`
--
ALTER TABLE `sensor_data`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sensor_data`
--
ALTER TABLE `sensor_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=162;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
