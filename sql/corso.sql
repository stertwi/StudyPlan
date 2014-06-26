ALTER TABLE STERTWI.CORSO
 DROP PRIMARY KEY CASCADE;

DROP TABLE STERTWI.CORSO CASCADE CONSTRAINTS;

CREATE TABLE STERTWI.CORSO
(
  ID              NUMBER                        NOT NULL,
  NOME            VARCHAR2(256 BYTE),
  LAST_UPDATE     DATE,
  PIANO           NUMBER,
  DATAESAME       DATE,
  ARGOMENTI       VARCHAR2(1024 BYTE),
  CFU             FLOAT(126),
  ORE_EFFETTUATE  FLOAT(126)
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


--  There is no statement for index STERTWI.SYS_C0011542.
--  The object is created automatically by Oracle when the parent object is created.

CREATE OR REPLACE TRIGGER STERTWI.CORSO_TRG
BEFORE INSERT
ON STERTWI.CORSO REFERENCING NEW AS New OLD AS Old
FOR EACH ROW
DISABLE
BEGIN
-- For Toad:  Highlight column ID
  :new.ID := CORSO_SEQ.nextval;
END CORSO_TRG;
/


CREATE OR REPLACE TRIGGER STERTWI.CORSO_UPDATE_TRG
before insert or UPDATE
ON STERTWI.CORSO FOR EACH ROW
BEGIN
  :new.last_update := systimestamp;
END CORSO_UPDATE_TRG;
/


ALTER TABLE STERTWI.CORSO ADD (
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

ALTER TABLE STERTWI.CORSO ADD (
  CONSTRAINT CORSO_PIANO_FK 
  FOREIGN KEY (PIANO) 
  REFERENCES STERTWI.PIANO (ID));
