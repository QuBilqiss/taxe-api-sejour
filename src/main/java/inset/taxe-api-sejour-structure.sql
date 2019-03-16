-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  lun. 11 mars 2019 à 23:04
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

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `taux_taxe_sejour`
--

CREATE TABLE `taux_taxe_sejour` (
  `id` bigint(20) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `pourcentage` double NOT NULL,
  `ref_categorie` varchar(255) DEFAULT NULL,
  `reference_local` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `taxe_sejour_annuelle`
--

CREATE TABLE `taxe_sejour_annuelle` (
  `id` bigint(20) NOT NULL,
  `annee` bigint(20) DEFAULT NULL,
  `chiffre_affaire` double NOT NULL,
  `date_presentation` date DEFAULT NULL,
  `date_validation` date DEFAULT NULL,
  `montant_base` double NOT NULL,
  `montant_majoration` double NOT NULL,
  `montant_penalite` double NOT NULL,
  `montant_taxe` double NOT NULL,
  `nomber_mois_retard` int(11) NOT NULL,
  `numero_trimester` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `taxe_sejour_trimestrielle`
--

CREATE TABLE `taxe_sejour_trimestrielle` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) NOT NULL,
  `chiffre_affaire` double NOT NULL,
  `date_presentation` date DEFAULT NULL,
  `majoration` double NOT NULL,
  `montant_base` double NOT NULL,
  `montant_majoration` double NOT NULL,
  `montant_penalite` double NOT NULL,
  `montant_taxe` double NOT NULL,
  `nomber_mois_retard` bigint(20) NOT NULL,
  `numero_trimester` int(11) NOT NULL,
  `penalite` double NOT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `reference_local` varchar(255) DEFAULT NULL,
  `taxe_sejour_annuelle` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `taux_taxe_sejour`
--
ALTER TABLE `taux_taxe_sejour`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `taxe_sejour_annuelle`
--
ALTER TABLE `taxe_sejour_annuelle`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `taxe_sejour_trimestrielle`
--
ALTER TABLE `taxe_sejour_trimestrielle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKroke32ftb94g50p9tgb7cst30` (`taxe_sejour_annuelle`);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
