-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 28, 2018 at 07:27 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `coats`
--

-- --------------------------------------------------------

--
-- Table structure for table `coats_reseller_bulk_order`
--

CREATE TABLE IF NOT EXISTS `coats_reseller_bulk_order` (
`id` int(11) NOT NULL,
  `bulk_order_id` int(11) NOT NULL,
  `order_line` int(11) NOT NULL,
  `source_id` int(11) NOT NULL,
  `order_no` varchar(30) NOT NULL,
  `sales_org_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `requester_id` int(11) NOT NULL,
  `ship_to_party_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `po_number` varchar(30) NOT NULL,
  `surcharge_value` varchar(30) NOT NULL,
  `discount_value` varchar(30) NOT NULL,
  `net_value` varchar(30) NOT NULL,
  `currency` varchar(30) NOT NULL,
  `status_id` int(11) NOT NULL,
  `so_number` int(11) NOT NULL,
  `created` varchar(30) NOT NULL,
  `created_user_id` int(11) NOT NULL,
  `updated` varchar(30) NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  `version` varchar(30) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `coats_reseller_bulk_order`
--

INSERT INTO `coats_reseller_bulk_order` (`id`, `bulk_order_id`, `order_line`, `source_id`, `order_no`, `sales_org_id`, `customer_id`, `requester_id`, `ship_to_party_id`, `buyer_id`, `po_number`, `surcharge_value`, `discount_value`, `net_value`, `currency`, `status_id`, `so_number`, `created`, `created_user_id`, `updated`, `deleted`, `version`, `created_at`, `updated_at`) VALUES
(5, 2222, 333, 444, '6666', 555, 999, 56464, 777, 444, ' PO-85444', '4444-7666 ', 'D-6777 ', 'N-410 ', 'aaa ', 4444, 45454, 'test', 465, 'fds', 1, '1 ', '2018-08-27 05:38:43', '2018-08-27 05:38:43'),
(6, 2000, 333, 444, '44444-1000', 555, 999, 9888, 777, 444, ' PO-4444', '4444-7666 ', 'D-6777 ', 'N-netvalue ', 'fafafa ', 6666, 45454, 'asfsaf', 465, 'fds', 1, 'Initial ', '2018-08-25 08:24:08', '2018-08-25 08:24:08');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `coats_reseller_bulk_order`
--
ALTER TABLE `coats_reseller_bulk_order`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `coats_reseller_bulk_order`
--
ALTER TABLE `coats_reseller_bulk_order`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
