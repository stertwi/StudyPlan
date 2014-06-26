ALTER TABLE STERTWI.STUDENTE
 DROP PRIMARY KEY CASCADE;

DROP TABLE STERTWI.STUDENTE CASCADE CONSTRAINTS;

CREATE TABLE STERTWI.STUDENTE
(
  ID             NUMBER                         NOT NULL,
  NOME           VARCHAR2(256 BYTE),
  COGNOME        VARCHAR2(256 BYTE),
  PWD            VARCHAR2(256 BYTE),
  MATRICOLA      VARCHAR2(256 BYTE),
  CORSODILAUREA  VARCHAR2(256 BYTE),
  LAST_UPDATE    DATE
)
TABLESPACE APP
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


--  There is no statement for index STERTWI.SYS_C0011810.
--  The object is created automatically by Oracle when the parent object is created.

CREATE OR REPLACE TRIGGER STERTWI.STUDENTE_UPDATE_TRG
before insert or UPDATE
ON STERTWI.STUDENTE FOR EACH ROW
BEGIN
  :new.last_update := systimestamp;
END STUDENTE_UPDATE_TRG;
/


ALTER TABLE STERTWI.STUDENTE ADD (
  PRIMARY KEY
  (ID)
  USING INDEX
    TABLESPACE APP
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));
