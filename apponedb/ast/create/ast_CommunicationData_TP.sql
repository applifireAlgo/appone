DROP TABLE IF EXISTS `ast_CommunicationData_TP`;

CREATE TABLE `ast_CommunicationData_TP` ( `commDataId` VARCHAR(64) NOT NULL, `commGroupId` VARCHAR(64) NOT NULL, `commType` VARCHAR(64) NOT NULL, `commData` TEXT NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2016-04-11 15:22:47', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2016-04-11 15:22:47', `versionId` INT(11) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(11) NULL DEFAULT NULL, PRIMARY KEY (`commDataId`));

