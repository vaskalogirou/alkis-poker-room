CREATE TABLE result (
   id BIGSERIAL PRIMARY KEY NOT NULL,
   player_id BIGINT NOT NULL,
   cash_in INTEGER,
   cash_out INTEGER,
   FOREIGN KEY (player_id) REFERENCES player (id)
);