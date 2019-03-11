-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  lun. 11 mars 2019 à 23:05
-- Version du serveur :  10.1.35-MariaDB
-- Version de PHP :  7.2.9

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `taxe-api-sejour`
--

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(21),
(21),
(21);

--
-- Déchargement des données de la table `taux_taxe_sejour`
--

INSERT INTO `taux_taxe_sejour` (`id`, `date_debut`, `date_fin`, `pourcentage`, `ref_categorie`, `reference_local`) VALUES
(6, '2018-12-25', '2019-12-20', 0.2, 'c5', 'L1'),
(4, '2018-06-25', '2019-06-20', 0.2, 'c2', 'L2'),
(5, '2018-12-25', '2019-12-20', 0.15, 'c4', 'L3'),
(7, '2018-12-25', '2019-12-20', 0.2, 'c3', 'L3'),
(19, '2017-06-30', '2019-06-30', 0.15, 'c2', 'L3');

--
-- Déchargement des données de la table `taxe_sejour_trimestrielle`
--

INSERT INTO `taxe_sejour_trimestrielle` (`id`, `annee`, `chiffre_affaire`, `date_presentation`, `majoration`, `montant_base`, `montant_majoration`, `montant_penalite`, `montant_taxe`, `nomber_mois_retard`, `numero_trimester`, `penalite`, `reference`, `reference_local`, `taxe_sejour_annuelle`) VALUES
(15, 2018, 20000, '2019-03-30', 50, 4000, 200000, 40000, 684000, 12, 4, 10, 't1', 'L3', NULL),
(17, 2018, 10000, '2019-03-30', 50, 2000, 100000, 20000, 456122000, 22801, 4, 10, 't3', 'L3', NULL),
(18, 2018, 18000, '2019-02-28', 100, 3600, 360000, 1800000, 20163600, 11, 4, 500, NULL, 'L3', NULL),
(20, 2018, 15000, NULL, 50, 3000, 0, 0, 0, -1, 3, 10, 't4', 'L3', NULL);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
