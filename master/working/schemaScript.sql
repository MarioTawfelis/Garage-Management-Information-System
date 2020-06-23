BEGIN TRANSACTION;



CREATE TABLE `WarrantyCompany` (
`WarrantyID`INTEGER NOT NULL UNIQUE,
`WarrantyName`TEXT NOT NULL,
`WarrantyAddress`TEXT NOT NULL,
`WarrantyExpDate`TEXT NOT NULL,
PRIMARY KEY(`WarrantyID`)
);

INSERT INTO 'WarrantyCompany' VALUES (0, "ING", "Canary Wharf, London, S1A 4JN", "01/03/2019" );
INSERT INTO 'WarrantyCompany' VALUES (1, "LehmanInsurance", "Brighton, Sussex, EC1B 4JN", "01/03/2019" );
INSERT INTO 'WarrantyCompany' VALUES (2, "AXABROKERS", "St Pauls, London, LK1A 4JJ", "01/03/2020" );




CREATE TABLE vehicle_template (
  'ID'                   INTEGER PRIMARY KEY,
  'Type'                 TEXT NOT NULL,
  'template_model'       TEXT NOT NULL,
  'template_make'        TEXT NOT NULL,
  'template_engine_size' TEXT NOT NULL,
  'template_fuel_type'   TEXT NOT NULL
);
INSERT INTO 'vehicle_template' VALUES(0, "Car", "Focus", "Ford", "2.0", "Diesel");
INSERT INTO 'vehicle_template' VALUES(1, "Truck","Blazer", "Chevrolet","5.0", "Diesel") ;
INSERT INTO 'vehicle_template' VALUES(2, "Van","Fiorino", "Fiat", "1.3", "Diesel");



CREATE TABLE `Vehicle` (
'VehicleType' TEXT NOT NULL,
`VehicleRegNo`TEXT NOT NULL UNIQUE,
`VehicleModel`TEXT NOT NULL,
`VehicleMake`TEXT NOT NULL,
'VehicleMileage' TEXT NOT NULL,
`VehicleEngSize`TEXT NOT NULL,
`VehicleFuelType`TEXT NOT NULL,  
`VehicleColour`TEXT NOT NULL,
`VehicleMoTDate`TEXT NOT NULL,
`WarrantyID`INTEGER,
`CustomerID` INTEGER NOT NULL UNIQUE,

  FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID),
  FOREIGN KEY(WarrantyID) REFERENCES WarrantyCompany(WarrantyID)
  PRIMARY KEY(`VehicleRegNo`)
);

INSERT INTO 'Vehicle' VALUES("Car", "A123","Focus", "Ford", "100000", "2.0", "Diesel", "white", "18/10/1997", 0, 0 ) ;
INSERT INTO 'Vehicle' VALUES("Truck", "BB123","Blazer", "Chevrolet", "50000", "5.0", "Diesel", "black", "18/10/2000", 1, 1 ) ;
INSERT INTO 'Vehicle' VALUES("Van", "D234","Fiorino", "Fiat", "10000", "1.3", "Diesel", "Blue", "10/01/2017", 2, 2) ;





CREATE TABLE `User` (
`UserID`TEXT NOT NULL UNIQUE,
`UserFirstName`TEXT NOT NULL,
`UserLastName`TEXT NOT NULL,
`UserPassword`TEXT NOT NULL,
`UserIsAdmin`INTEGER NOT NULL,
PRIMARY KEY(`UserID`)
);






INSERT INTO `User` VALUES ('00000','System','Admin','password',1);



CREATE TABLE `SpecialistRepair` (
`SRID`INTEGER NOT NULL UNIQUE,
`SRReturnDate`TEXT NOT NULL,
`SRDeliveryDate`TEXT NOT NULL,
`PartID`TEXT,
`VehicleRegNo`TEXT,
`SPCID`INTEGER NOT NULL UNIQUE,
FOREIGN KEY(VehicleRegNo) REFERENCES Vehicle(VehicleRegNo)
FOREIGN KEY(PartID) REFERENCES Part(PartID)
FOREIGN KEY(SPCID) REFERENCES SPC(SPCID)
PRIMARY KEY(`SRID`)
);





CREATE TABLE `SPC` (
`SPCID`INTEGER NOT NULL UNIQUE,
`SPCName`TEXT NOT NULL,
`SPCAddress`TEXT NOT NULL,
`SPCPhoneNo`TEXT NOT NULL,
PRIMARY KEY(`SPCID`)
);



CREATE TABLE 'Parts_Installed'(
	
'PartID' INTEGER NOT NULL,
'VehicleRegNo' TEXT NOT NULL,
'PartInstalledDate' TEXT NOT NULL,
'BookingID' TEXT NOT NULL,
FOREIGN KEY(`VehicleRegNo`) REFERENCES `Vehicle`(`VehicleRegNo`),
FOREIGN KEY(`PartID`) REFERENCES `Part`(`PartID`),
FOREIGN KEY('BookingID') REFERENCES 'Booking'('BookingID')
	
);
INSERT INTO `Parts_Installed` VALUES (1, 'A123', '02/10/2016', 0 );
INSERT INTO `Parts_Installed` VALUES (2, 'BB123', '03/12/2016', 1);
INSERT INTO `Parts_Installed` VALUES (3, 'D234', '01/02/2017', 2 );





CREATE TABLE `Part` (
`PartID`INTEGER NOT NULL UNIQUE,
`PartName`TEXT NOT NULL,
`PartDescription`TEXT NOT NULL,
`PartQuantity`INTEGER NOT NULL,
'PartCost' DOUBLE NOT NULL,
PRIMARY KEY(`PartID`)
);





INSERT INTO `Part` VALUES (1,'Handbrakes','whatev.',20,'30.0');
INSERT INTO `Part` VALUES (2,'Engines','whatev.',10,'3.0');
INSERT INTO `Part` VALUES (3,'Turbo','whatev.',50,'34.0');
INSERT INTO `Part` VALUES (4,'Nitrous','whatev.',40,'5.0');
INSERT INTO `Part` VALUES (5,'Brakes','whatev.',30,'5.0');
INSERT INTO `Part` VALUES (6,'Rear Mirrors','whatev.',20,'9.0');
INSERT INTO `Part` VALUES (7,'Hoods','whatev.',500,'4.0');
INSERT INTO `Part` VALUES (8,'Bumpers','whatev.',60,'3.0');
INSERT INTO `Part` VALUES (9,'Doors','whatev.',80,'3.0');
INSERT INTO `Part` VALUES (10,'Tail Lights','whatev.',40,'27.0');






CREATE TABLE "Mechanic" (
	`MechanicID`	INTEGER NOT NULL UNIQUE,
	`MechanicFirstName`	TEXT NOT NULL,
	`MechanicLastName`	TEXT NOT NULL,
	`MechanicHourlyRate`	INTEGER NOT NULL,
	PRIMARY KEY(`MechanicID`)
);






INSERT INTO `Mechanic` VALUES (0,'Neshanathan','Amageus',13);
INSERT INTO `Mechanic` VALUES (1,'Jonathan','Warm',15);
INSERT INTO `Mechanic` VALUES (2,'Mo','Ragheart',14);
INSERT INTO `Mechanic` VALUES (3,'Yarn','Boulderthinn',13);
INSERT INTO `Mechanic` VALUES (4,'Rosh','Dunno',10);




CREATE TABLE `Customer` (
`CustomerID`INTEGER NOT NULL UNIQUE,
`CustomerFirstName`TEXT NOT NULL,
`CustomerLastName`TEXT NOT NULL,
`CustomerAddress`TEXT NOT NULL,
`CustomerPostcode`TEXT NOT NULL,
`CustomerPhoneNo`TEXT NOT NULL,
`CustomerEmail`TEXT NOT NULL,
PRIMARY KEY(`CustomerID`)
);





INSERT INTO `Customer` VALUES (0,'John','Smith','15 Test Road','E3 6GT','07123456789','john.smith@hotmail.com');
INSERT INTO `Customer` VALUES (1,'Julius','Nex','1600 Pennsylvania Ave','DC 20500','07777777777','memes@gmail.com');
INSERT INTO `Customer` VALUES (2,'Lina','Rimy','1600 Pennsylvania Ave','DC 20500','054625462546','linaaaaa@gmail.com');




CREATE TABLE "BookingIntegrated" (
	`BookingID`	INTEGER NOT NULL UNIQUE,
	`BookingType`	TEXT NOT NULL,
	`BookingDate`	TEXT NOT NULL,
	`BookingDuration`	TEXT NOT NULL,
	`VehicleRegNo`	TEXT NOT NULL,
	`VehicleManufacturer`	TEXT NOT NULL,
	`VehicleMileage`	INTEGER NOT NULL,
	`CustomerID`	INTEGER NOT NULL,
	`CustomerFirstName`	TEXT NOT NULL,
	`CustomerLastName`	TEXT NOT NULL,
	`MechanicID`	INTEGER NOT NULL,
	`MechanicDuration`	TEXT NOT NULL,
	PRIMARY KEY(`BookingID`),
	FOREIGN KEY(`VehicleRegNo`) REFERENCES `Vehicle`(`VehicleRegNo`),
	FOREIGN KEY(`CustomerID`) REFERENCES `Customer`(`CustomerID`),
	FOREIGN KEY(`MechanicID`) REFERENCES `Mechanic`(`MechanicID`)
);





INSERT INTO `BookingIntegrated` VALUES (1,'Repair','09/02/2017 13:00','01:00','YK0 02ML','Audi',1000,1,'Julius','Nex',1,'01:00');
INSERT INTO `BookingIntegrated` VALUES (2,'Maintenance','10/02/2017 15:00','02:30','MC7 AKH8','Ford',10200,1,'Julius','Nex',1,'00:00');
INSERT INTO `BookingIntegrated` VALUES (3,'Repair','27/02/2017 08:30','06:45','AH6 7USK','Mercedes',5060,2,'Lina','Rimy',2,'01:00');
INSERT INTO `BookingIntegrated` VALUES (4,'Repair','01/03/2017 09:00','08:00','NO9 AMS','Ford',9001,3,'Chrono','Rest',3,'00:00');
INSERT INTO `BookingIntegrated` VALUES (5,'Repair','09/02/2017 09:00','01:00','AH6 7USK','TEMP',0,0,'John','Smith',2,'00:00');
INSERT INTO `BookingIntegrated` VALUES (6,'Repair','10/02/2017 09:00','01:00','NO9 AMS','TEMP',0,0,'John','Smith',2,'00:00');
INSERT INTO `BookingIntegrated` VALUES (7,'Maintenance','10/02/2017 09:00','01:00','AH6 7USK','TEMP',0,0,'John','Smith',2,'00:00');



CREATE TABLE "bill" (
  ID             INTEGER PRIMARY KEY ASC,
  bill REAL NOT NULL
);
INSERT INTO 'bill' VALUES(0, '210');
INSERT INTO 'bill' VALUES(1, '210');
INSERT INTO 'bill' VALUES(2, '210');




CREATE TABLE "Booking" (
	`BookingID`	INTEGER NOT NULL UNIQUE,
	`BookingType`	TEXT NOT NULL,
	`BookingDate`	TEXT NOT NULL,
	`BookingDuration`	TEXT NOT NULL,
	`VehicleRegNo`	TEXT NOT NULL,
	`CustomerID`	INTEGER NOT NULL,
	`MechanicID`	INTEGER NOT NULL,
	`MechanicDuration`	TEXT NOT NULL,
	'BillID'        INTEGER NOT NULL,
	PRIMARY KEY(`BookingID`),
	FOREIGN KEY(`VehicleRegNo`) REFERENCES `Vehicle`(`VehicleRegNo`),
	FOREIGN KEY(`CustomerID`) REFERENCES `Customer`(`CustomerID`),
	FOREIGN KEY(`MechanicID`) REFERENCES `Mechanic`(`MechanicID`),
	FOREIGN KEY('BillID') REFERENCES 'bill'(ID)
);





INSERT INTO `Booking` VALUES (0,'Repair','09/02/2017 13:00','01:00:00','A123',0,0,'01:00:00',0);
INSERT INTO `Booking` VALUES (1,'Repair','09/02/2017 13:00','01:00:00','A123',1,1,'01:00:00',1);
INSERT INTO `Booking` VALUES (2,'Repair','09/02/2017 13:00','01:00:00','A123',2,2,'01:00:00',2);
COMMIT;
