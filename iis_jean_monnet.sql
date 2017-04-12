-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 22, 2017 at 06:44 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `iis_jean_monnet`
--

-- --------------------------------------------------------

--
-- Table structure for table `operazioni`
--

CREATE TABLE `operazioni` (
  `id` int(11) NOT NULL,
  `nome` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='elenco operazioni aritmetiche';

--
-- Dumping data for table `operazioni`
--

INSERT INTO `operazioni` (`id`, `nome`) VALUES
(1, 'somma'),
(2, 'sottrazione'),
(3, 'moltiplicazione'),
(4, 'divisione'),
(5, 'radice quadrata');

-- --------------------------------------------------------

--
-- Table structure for table `specializzazioni`
--

CREATE TABLE `specializzazioni` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='specializzazioni presenti in istituto';

--
-- Dumping data for table `specializzazioni`
--

INSERT INTO `specializzazioni` (`id`, `nome`) VALUES
(1, 'informatica e telecomunicazioni'),
(2, 'chimica'),
(3, 'meccanica'),
(4, 'amministrazione finanza e marketing'),
(5, 'liceo scientifico scienze applicate'),
(6, 'liceo linguistico');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `operazioni`
--
ALTER TABLE `operazioni`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `specializzazioni`
--
ALTER TABLE `specializzazioni`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `operazioni`
--
ALTER TABLE `operazioni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `specializzazioni`
--
ALTER TABLE `specializzazioni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
