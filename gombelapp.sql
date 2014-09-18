-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 14, 2014 at 05:04 PM
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `gombelapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_event`
--

CREATE TABLE IF NOT EXISTS `tbl_event` (
  `id_event` varchar(5) NOT NULL,
  `judul` varchar(25) NOT NULL,
  `isi_event` text NOT NULL,
  `waktu_event` varchar(15) NOT NULL,
  `tanggal_event` date NOT NULL,
  `tempat_event` varchar(20) NOT NULL,
  PRIMARY KEY (`id_event`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_event`
--

INSERT INTO `tbl_event` (`id_event`, `judul`, `isi_event`, `waktu_event`, `tanggal_event`, `tempat_event`) VALUES
('E01', 'Ulang Tahun Ani', 'Ani mengundang teman - teman untuk datang ke pesta ulang tahun nya pada waktu yang sudah ditetapkan.', '19.00', '2014-08-08', 'Rumah Ani'),
('E02', 'Khitanan Anak Pak Budi', 'Pada Sabtu besok akan di adakan Khitanan dari anak bapak Budi. Datang ya ... :)', '08.00', '2014-08-12', 'Rumah Bapak Budi');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_info`
--

CREATE TABLE IF NOT EXISTS `tbl_info` (
  `kode` varchar(5) NOT NULL,
  `judul` text NOT NULL,
  `isi` text NOT NULL,
  `waktu` date NOT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_info`
--

INSERT INTO `tbl_info` (`kode`, `judul`, `isi`, `waktu`) VALUES
('N01', 'Rumah Mewah Harga Murah', 'Dijual rumah mewah di samping tol', '2014-08-07'),
('N02', 'Harga Listrik Naik', 'Dikarenankan pasokan listrik kurang Harga naik', '2014-08-04'),
('N03', 'Lakalantas di Simpang Lima', 'Sabtu esok seorang pengendara bermotor tertabrak truk di persimpangan jalan simpang lima, untungnya sang pengendara bermotor tidak lecet sedikit pun, malah truk yang menabraknya ringsek.', '2014-08-14'),
('N04', 'Banjir Bandang di Hilir Sungai Kaligarang', 'Minggu esok warga desa Tinjomoyo dikagetkan dengan datangnya banjir bandang dari arah Kaligarang.', '2014-08-21');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_komentar_info`
--

CREATE TABLE IF NOT EXISTS `tbl_komentar_info` (
  `info_id` varchar(5) DEFAULT NULL,
  `komentar` text NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nama_user` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_komentar_info`
--

INSERT INTO `tbl_komentar_info` (`info_id`, `komentar`, `waktu`, `nama_user`) VALUES
('N04', 'Bahaya', '2014-08-10 17:19:22', 'Aldy Hernawan'),
('N04', 'Untung rumah ane jauh dari situ gan.........', '2014-08-11 19:03:02', 'Dani Pratama'),
('N04', 'Wuih ngeri banget banjirnya.', '2014-08-10 17:17:36', 'Bangjeko'),
('N01', 'Murah banget bro', '2014-08-10 21:07:49', 'Dani Pratama'),
('N04', 'Serem vroh', '2014-08-10 21:08:19', 'Bangjeko'),
('N04', 'Medeni Tog', '2014-08-10 23:23:53', 'Dani Pratama'),
('N03', 'Inalilahi wa inailaihi rojiun', '2014-08-10 23:27:13', ' Aldy aja'),
('N02', 'Listrik naik terus, kapan turunnya??', '2014-08-10 23:27:49', 'Bangjeko'),
('N04', 'Bahaya banget', '2014-08-12 12:45:05', 'Aldy Hernawan'),
('N04', 'AW', '2014-08-13 01:15:29', 'Aldy Hernawan');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_saran`
--

CREATE TABLE IF NOT EXISTS `tbl_saran` (
  `id_saran` int(5) NOT NULL AUTO_INCREMENT,
  `judul` varchar(25) NOT NULL,
  `isi_saran` text NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nama_user` varchar(25) NOT NULL,
  PRIMARY KEY (`id_saran`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `tbl_saran`
--

INSERT INTO `tbl_saran` (`id_saran`, `judul`, `isi_saran`, `waktu`, `nama_user`) VALUES
(1, 'Perbaikan Jalan ', 'Tolong dilakukan perbaikan jalan di jalan Gombel V. Terdapat lubang besar yang dapat membahayakan keselamatan pengguna jalan, terima Kasih.', '2014-08-12 20:02:10', 'Aldy Hernawan'),
(2, 'Sampah Menumpuk', 'Tempat sampah di tempat pembuangan sampah sudah terjadi penumpukan, tolong segera dibersihkan untuk menjaga kebersihan dan kesehatan lingkungan.', '2014-08-25 19:35:20', 'Dani Pratama'),
(3, 'Ronda Malam', 'Karena akhir - akhir ini banyak kejadian pembegalan di wilayah sekitar kompleks, tolong diadakan ronda malam setiap hari.', '2014-08-11 19:59:01', 'Bangjeko');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE IF NOT EXISTS `tbl_user` (
  `kode` int(11) NOT NULL AUTO_INCREMENT,
  `alamat_email` varchar(30) NOT NULL,
  `password_user` varchar(30) NOT NULL,
  `nama_user` varchar(30) NOT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`kode`, `alamat_email`, `password_user`, `nama_user`) VALUES
(1, 'Bangjeko@gmail.com', '1234', 'Aldy Hernawan'),
(2, 'Aldy@gmail.com', '1234', 'Bangjeko'),
(3, 'Dani@gmail.com', '1234', 'Dani Pratama'),
(4, 'Jeko@gmail.com', '1234', 'Jeko'),
(5, 'Aldy@yahoo.com', '1234', 'Aldy aja'),
(11, 'AldyHernawan@yahoo.com', '1234', 'Aldy Hernawan');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
