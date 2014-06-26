ALTER TABLE STERTWI.SAL
 DROP PRIMARY KEY CASCADE;

DROP TABLE STERTWI.SAL CASCADE CONSTRAINTS;

CREATE TABLE STERTWI.SAL
(
  ID                 NUMBER,
  PIANO              NUMBER,
  STUDENTE           NUMBER,
  START_STUDIO       DATE,
  FINISH_STUDIO      DATE,
  LAST_UPDATE        DATE,
  ORE_EFFETTUATE     FLOAT(126),
  PIANIFICAZIONEXML  SYS.XMLTYPE,
  PIANIFICAZIONE     FLOAT(126)
)
XMLTYPE PIANIFICAZIONEXML STORE AS CLOB (
  TABLESPACE APP
  ENABLE       STORAGE IN ROW
  CHUNK       8192
  RETENTION
  NOCACHE
  LOGGING
  INDEX       (
        TABLESPACE APP
        STORAGE    (
                    INITIAL          64K
                    NEXT             1M
                    MINEXTENTS       1
                    MAXEXTENTS       UNLIMITED
                    PCTINCREASE      0
                    BUFFER_POOL      DEFAULT
                   ))
      STORAGE    (
                  INITIAL          64K
                  NEXT             1M
                  MINEXTENTS       1
                  MAXEXTENTS       UNLIMITED
                  PCTINCREASE      0
                  BUFFER_POOL      DEFAULT
                 ))
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


CREATE UNIQUE INDEX STERTWI.SAL_PK ON STERTWI.SAL
(ID)
LOGGING
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
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE OR REPLACE TRIGGER STERTWI.SAL_UPDATE_TRG
before insert or UPDATE
ON STERTWI.SAL FOR EACH ROW
BEGIN
  :new.last_update := systimestamp;
END SAL_UPDATE_TRG;
/


ALTER TABLE STERTWI.SAL ADD (
  CONSTRAINT SAL_PK
  PRIMARY KEY
  (ID)
  USING INDEX STERTWI.SAL_PK);

ALTER TABLE STERTWI.SAL ADD (
  CONSTRAINT SAL_PIANO 
  FOREIGN KEY (PIANO) 
  REFERENCES STERTWI.PIANO (ID),
  CONSTRAINT SAL_STUDENTE 
  FOREIGN KEY (STUDENTE) 
  REFERENCES STERTWI.STUDENTE (ID));
