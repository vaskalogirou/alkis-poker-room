BEGIN
TRANSACTION;

ALTER TABLE player
ADD COLUMN color character varying(50);

ALTER TABLE player
ADD COLUMN active boolean NOT NULL DEFAULT TRUE;

END
TRANSACTION;