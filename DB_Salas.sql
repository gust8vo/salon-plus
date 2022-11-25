-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.25-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para salonplus
CREATE DATABASE IF NOT EXISTS `salonplus` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `salonplus`;

-- Copiando estrutura para tabela salonplus.autenticador
CREATE TABLE IF NOT EXISTS `autenticador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(19) NOT NULL DEFAULT '',
  `user` varchar(19) NOT NULL,
  `validacao` varchar(5) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela salonplus.autenticador: ~1 rows (aproximadamente)
INSERT INTO `autenticador` (`id`, `key`, `user`, `validacao`) VALUES
	(1, 'DSA3-18XM-AM1L-MVA1', 'seap294195', 'true');

-- Copiando estrutura para tabela salonplus.reservas
CREATE TABLE IF NOT EXISTS `reservas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reservador` varchar(38) NOT NULL DEFAULT '',
  `sala` varchar(6) NOT NULL,
  `horario` varchar(5) NOT NULL,
  `data` varchar(8) NOT NULL DEFAULT '',
  `tempo` varchar(8) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela salonplus.reservas: ~0 rows (aproximadamente)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
