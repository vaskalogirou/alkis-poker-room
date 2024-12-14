BEGIN
TRANSACTION;

ALTER TABLE poker_session
ADD COLUMN season character varying(50);

END
TRANSACTION;