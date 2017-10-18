-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-10-2017 a las 14:29:57
-- Versión del servidor: 10.1.26-MariaDB
-- Versión de PHP: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `youngsta`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `activaciones`
--

CREATE TABLE `activaciones` (
  `cod_activ` int(11) DEFAULT NULL,
  `cod_usr` int(11) DEFAULT NULL,
  `token` int(11) DEFAULT NULL,
  `creado` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agenciadas`
--

CREATE TABLE `agenciadas` (
  `cod_agencia` int(11) DEFAULT NULL,
  `cod_play` int(11) DEFAULT NULL,
  `cod_art` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `albunes`
--

CREATE TABLE `albunes` (
  `cod_album` int(11) DEFAULT NULL,
  `nombre` char(255) DEFAULT NULL,
  `ano` year(4) DEFAULT NULL,
  `vetado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artistas`
--

CREATE TABLE `artistas` (
  `cod_modafoca` int(11) DEFAULT NULL,
  `nombre` char(64) DEFAULT NULL,
  `ano` year(4) DEFAULT NULL,
  `vetado` int(1) DEFAULT NULL,
  `descripcion` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autoria`
--

CREATE TABLE `autoria` (
  `cod_autoria` int(11) DEFAULT NULL,
  `cod_modofoca` int(11) DEFAULT NULL,
  `cod_artista` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `canciones`
--

CREATE TABLE `canciones` (
  `cod_can` int(11) DEFAULT NULL,
  `titulo` char(255) DEFAULT NULL,
  `ano` year(4) DEFAULT NULL,
  `duracion` char(5) DEFAULT NULL,
  `visible` int(1) DEFAULT NULL,
  `descripcion` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentados`
--

CREATE TABLE `comentados` (
  `cod_comentado` int(11) DEFAULT NULL,
  `cod_usr` int(11) DEFAULT NULL,
  `cod_comen` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `cod_comen` int(11) DEFAULT NULL,
  `contenido` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `momento`
--

CREATE TABLE `momento` (
  `comen` int(11) DEFAULT NULL,
  `nombre` char(48) DEFAULT NULL,
  `inicio` int(11) DEFAULT NULL,
  `fin` int(11) DEFAULT NULL,
  `habilitado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `momento_can`
--

CREATE TABLE `momento_can` (
  `cod_rella` int(11) DEFAULT NULL,
  `cod_can` int(11) DEFAULT NULL,
  `cod_momen` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `playlist`
--

CREATE TABLE `playlist` (
  `cod_playlist` int(11) DEFAULT NULL,
  `nombre` char(32) DEFAULT NULL,
  `autor` int(11) DEFAULT NULL,
  `creado` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temas`
--

CREATE TABLE `temas` (
  `cod_tema` int(11) DEFAULT NULL,
  `nombre` char(24) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `cod_usr` int(11) DEFAULT NULL,
  `nombre` char(255) DEFAULT NULL,
  `apelldios` char(255) DEFAULT NULL,
  `mail` char(255) DEFAULT NULL,
  `pss` char(255) DEFAULT NULL,
  `activo` int(1) DEFAULT NULL,
  `tema` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`cod_usr`, `nombre`, `apelldios`, `mail`, `pss`, `activo`, `tema`) VALUES
(1, 'blac', 'youngsta', 'youngsta@gmail.com', 'e62fa6948d64a1206c5f3871ff60385e', 1, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
