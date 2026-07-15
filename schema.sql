Result Set Batch 1 - Query 1
========================================

CreateTableScript                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE [lktechno_farmestate].[tblCategory] (
    [ID] int NULL,
    [Category] varchar(100) NULL
);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
CREATE TABLE [lktechno_farmestate].[tblFeedBack] (
    [ID] int NULL,
    [PropertyID] varchar(20) NULL,
    [UserName] varchar(100) NULL,
    [FeedBack] varchar(300) NULL
);                                                                                                                                                                                                                                                                                                                                                                                                                                           
CREATE TABLE [lktechno_farmestate].[tblLogin] (
    [UserID] varchar(20) NULL,
    [Password] varchar(20) NULL,
    [UserType] varchar(20) NULL,
    [UserName] varchar(100) NULL
);                                                                                                                                                                                                                                                                                                                                                                                                                                     
CREATE TABLE [lktechno_farmestate].[tblOwners] (
    [ID] varchar(20) NULL,
    [OwnerName] varchar(100) NULL,
    [AddressLine1] varchar(100) NULL,
    [AddressLine2] varchar(100) NULL,
    [City] varchar(100) NULL,
    [State] varchar(100) NULL,
    [Mobile] varchar(20) NULL,
    [EmailID] varchar(100) NULL
);                                                                                                                                                                                                                                                                                                
CREATE TABLE [lktechno_farmestate].[tblProperties] (
    [ID] varchar(20) NULL,
    [Category] varchar(100) NULL,
    [AddressLine1] varchar(100) NULL,
    [AddressLine2] varchar(100) NULL,
    [City] varchar(100) NULL,
    [State] varchar(100) NULL,
    [Area] varchar(300) NULL,
    [Description] varchar(500) NULL,
    [Features] varchar(500) NULL,
    [Remarks] varchar(500) NULL,
    [Type] varchar(50) NULL,
    [Price] varchar(200) NULL,
    [LatAd] varchar(20) NULL,
    [LongAd] varchar(20) NULL,
    [Status] varchar(50) NULL,
    [OwnerID] varchar(20) NULL,
    [UserID] varchar(20) NULL
);
CREATE TABLE [lktechno_farmestate].[tblPropertyImages] (
    [ID] int NULL,
    [PropertyID] varchar(20) NULL,
    [Image] text NULL,
    [Subject] varchar(100) NULL,
    [Description] varchar(300) NULL
);                                                                                                                                                                                                                                                                                                                                                                                                            
CREATE TABLE [lktechno_farmestate].[tblType] (
    [ID] int NULL,
    [Type] varchar(50) NULL
);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
CREATE TABLE [lktechno_farmestate].[tblUsers] (
    [ID] varchar(20) NULL,
    [UserName] varchar(100) NULL,
    [AddressLine1] varchar(100) NULL,
    [AddressLine2] varchar(100) NULL,
    [City] varchar(100) NULL,
    [State] varchar(1000) NULL,
    [Mobile] varchar(20) NULL,
    [EmailID] varchar(100) NULL
);                                                                                                                                                                                                                                                                                                 
(8 rows affected)

