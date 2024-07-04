CREATE TABLE poker_session (
   id BIGSERIAL PRIMARY KEY NOT NULL,
   poker_date DATE NOT NULL DEFAULT CURRENT_DATE,
   host_id BIGINT,
   notes TEXT,
   FOREIGN KEY (host_id) REFERENCES player (id)
);