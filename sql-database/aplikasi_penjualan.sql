-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2021 at 04:23 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aplikasi_penjualan`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `total_transaksi` ()  BEGIN
SELECT 
SUM(penjualan.Jumlah*penjualan.Harga_perLot) AS Total_Harga
FROM penjualan;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `total_transaksi2` ()  BEGIN
SELECT 
SUM(pembelian.Jumlah*pembelian.Harga_perLot) AS Total_Harga
FROM pembelian;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `Kode_Saham` varchar(4) NOT NULL,
  `Nama_Saham` varchar(150) NOT NULL,
  `Jumlah_Lot` int(11) NOT NULL,
  `Harga/Lot` int(11) NOT NULL,
  `Tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`Kode_Saham`, `Nama_Saham`, `Jumlah_Lot`, `Harga/Lot`, `Tanggal`) VALUES
('ANTM', 'Aneka Tambang Tbk.', 8, 3160000, '2021-04-30'),
('ASII', 'Astra International Tbk.', 5, 550000, '2021-04-30'),
('BBCA', 'Bank Central Asia Tbk.', 7, 3160000, '2021-04-30'),
('BBRI', 'Bank Rakyat Indonesia Tbk.', 8, 405000, '2021-04-30'),
('BRIS', 'Bank Syariah Indonesia Tbk.', 4, 228000, '2021-04-30'),
('TLKM', 'Telkom Indonesia Tbk.', 4, 320000, '2021-04-30');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `Nama` varchar(50) DEFAULT NULL,
  `Email` varchar(40) DEFAULT NULL,
  `Username` varchar(16) NOT NULL,
  `Password` varchar(18) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`Nama`, `Email`, `Username`, `Password`) VALUES
('Wildan Luqmanul Hakim', 'wildanluqmanul@gmail.com', 'admin', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `Username` varchar(16) NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `Jenis Kelamin` varchar(10) NOT NULL,
  `Tempat Lahir` varchar(20) NOT NULL,
  `Tanggal Lahir` date NOT NULL,
  `Agama` enum('Islam','Kristen','Katolik','Hindu','Buddha','Konghucu') NOT NULL,
  `Alamat` varchar(500) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `No HP` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`Username`, `Nama`, `Jenis Kelamin`, `Tempat Lahir`, `Tanggal Lahir`, `Agama`, `Alamat`, `Email`, `No HP`) VALUES
('wildanlh', 'Wildan Luqmaul Hakim', 'Laki-Laki', 'Sumedang', '2002-08-04', 'Islam', 'Sumedang', 'wildanluqmanul@gmail.com', '081224239054');

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE `pembelian` (
  `ID` int(11) NOT NULL,
  `Kode_Saham` varchar(4) NOT NULL,
  `Nama_Saham` varchar(50) NOT NULL,
  `Harga_perLot` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL,
  `Total_Harga` int(11) NOT NULL,
  `Tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `ID` int(11) NOT NULL,
  `Kode_Saham` varchar(4) NOT NULL,
  `Nama_Saham` varchar(50) NOT NULL,
  `Harga_perLot` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL,
  `Total_Harga` int(11) NOT NULL,
  `Tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `penjualan`
--
DELIMITER $$
CREATE TRIGGER `lot_habis` AFTER INSERT ON `penjualan` FOR EACH ROW BEGIN
DELETE FROM barang 
WHERE Jumlah_Lot < 1
AND 
Kode_Saham = NEW.Kode_Saham;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_beli`
--

CREATE TABLE `transaksi_beli` (
  `ID` int(11) NOT NULL,
  `Kode_Saham` varchar(4) NOT NULL,
  `Nama_Saham` varchar(50) NOT NULL,
  `Harga_perLot` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL,
  `Total_Harga` int(11) NOT NULL,
  `Tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi_beli`
--

INSERT INTO `transaksi_beli` (`ID`, `Kode_Saham`, `Nama_Saham`, `Harga_perLot`, `Jumlah`, `Total_Harga`, `Tanggal`) VALUES
(40001, 'ANTM', 'Aneka Tambang Tbk.', 3160000, 1, 3160000, '2021-05-26'),
(40002, 'TLKM', 'Telkom Indonesia Tbk.', 320000, 2, 640000, '2021-05-26'),
(40003, 'TLKM', 'Telkom Indonesia Tbk.', 320000, 1, 320000, '2021-05-26'),
(40004, 'ANTM', 'Aneka Tambang Tbk.', 3160000, 1, 3160000, '2021-05-27');

--
-- Triggers `transaksi_beli`
--
DELIMITER $$
CREATE TRIGGER `transaksipembelian` AFTER INSERT ON `transaksi_beli` FOR EACH ROW BEGIN
UPDATE barang SET Jumlah_Lot = Jumlah_Lot + NEW.Jumlah
WHERE Kode_Saham = NEW.Kode_Saham;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_jual`
--

CREATE TABLE `transaksi_jual` (
  `ID` int(11) NOT NULL,
  `Kode_Saham` varchar(4) NOT NULL,
  `Nama_Saham` varchar(50) NOT NULL,
  `Harga_perLot` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL,
  `Total_Harga` int(11) NOT NULL,
  `Tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi_jual`
--

INSERT INTO `transaksi_jual` (`ID`, `Kode_Saham`, `Nama_Saham`, `Harga_perLot`, `Jumlah`, `Total_Harga`, `Tanggal`) VALUES
(20001, 'ANTM', 'Aneka Tambang Tbk.', 3160000, 1, 3160000, '2021-05-26'),
(20002, 'ANTM', 'Aneka Tambang Tbk.', 3160000, 1, 3160000, '2021-05-26'),
(20003, 'ANTM', 'Aneka Tambang Tbk.', 3160000, 1, 3160000, '2011-05-26');

--
-- Triggers `transaksi_jual`
--
DELIMITER $$
CREATE TRIGGER `transaksi` AFTER INSERT ON `transaksi_jual` FOR EACH ROW BEGIN
UPDATE barang SET Jumlah_Lot = Jumlah_Lot - NEW.Jumlah
WHERE Kode_Saham = NEW.Kode_Saham;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`Kode_Saham`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`Username`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`Username`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Kode_Saham` (`Kode_Saham`) USING BTREE;

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Kode_Saham` (`Kode_Saham`) USING BTREE;

--
-- Indexes for table `transaksi_beli`
--
ALTER TABLE `transaksi_beli`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Kode_Saham` (`Kode_Saham`) USING BTREE;

--
-- Indexes for table `transaksi_jual`
--
ALTER TABLE `transaksi_jual`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Kode_Saham` (`Kode_Saham`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pembelian`
--
ALTER TABLE `pembelian`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20005;

--
-- AUTO_INCREMENT for table `transaksi_beli`
--
ALTER TABLE `transaksi_beli`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40026;

--
-- AUTO_INCREMENT for table `transaksi_jual`
--
ALTER TABLE `transaksi_jual`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40026;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
