-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema qlbanhang
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema qlbanhang
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `qlbanhang` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema qlhang
-- -----------------------------------------------------
USE `qlbanhang` ;

-- -----------------------------------------------------
-- Table `qlbanhang`.`MATHANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `qlbanhang`.`MATHANG` (
  `mahang` VARCHAR(5) NOT NULL,
  `ten` VARCHAR(45) NULL,
  `donvitinh` VARCHAR(45) NULL,
  `soluonghienco` INT NULL,
  `giabanhientai` FLOAT NULL,
  PRIMARY KEY (`mahang`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qlbanhang`.`HOADONXUAT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `qlbanhang`.`HOADONXUAT` (
  `sohoadon` VARCHAR(5) NOT NULL,
  `ngayban` DATE NULL,
  `tennguoimua` VARCHAR(45) NULL,
  PRIMARY KEY (`sohoadon`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qlbanhang`.`XUATHANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `qlbanhang`.`XUATHANG` (
  `soluongxuat` INT NULL,
  `giaban` FLOAT NULL,
  `MATHANG_mahang` VARCHAR(5) NOT NULL,
  `HOADON_sohoadon` VARCHAR(5) NOT NULL,
  INDEX `fk_XUATHANG_MATHANG_idx` (`MATHANG_mahang` ASC) ,
  INDEX `fk_XUATHANG_HOADON1_idx` (`HOADON_sohoadon` ASC) ,
  CONSTRAINT `fk_XUATHANG_MATHANG`
    FOREIGN KEY (`MATHANG_mahang`)
    REFERENCES `qlbanhang`.`MATHANG` (`mahang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_XUATHANG_HOADON1`
    FOREIGN KEY (`HOADON_sohoadon`)
    REFERENCES `qlbanhang`.`HOADONXUAT` (`sohoadon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qlbanhang`.`PHIEUNHAP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `qlbanhang`.`PHIEUNHAP` (
  `sophieu` VARCHAR(5) NOT NULL,
  `ngaynhap` DATE NULL,
  `tennhacungcap` VARCHAR(45) NULL,
  PRIMARY KEY (`sophieu`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qlbanhang`.`NHAPHANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `qlbanhang`.`NHAPHANG` (
  `soluongnhap` INT NULL,
  `giaban` FLOAT NULL,
  `MATHANG_mahang` VARCHAR(5) NOT NULL,
  `PHIEUNHAP_sophieu` VARCHAR(5) NOT NULL,
  INDEX `fk_NHAPHANG_MATHANG1_idx` (`MATHANG_mahang` ASC),
  INDEX `fk_NHAPHANG_PHIEUNHAP1_idx` (`PHIEUNHAP_sophieu` ASC),
  CONSTRAINT `fk_NHAPHANG_MATHANG1`
    FOREIGN KEY (`MATHANG_mahang`)
    REFERENCES `qlbanhang`.`MATHANG` (`mahang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NHAPHANG_PHIEUNHAP1`
    FOREIGN KEY (`PHIEUNHAP_sophieu`)
    REFERENCES `qlbanhang`.`PHIEUNHAP` (`sophieu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
