CREATE TABLE team (
id serial PRIMARY KEY NOT NULL,
name VARCHAR(30) NOT NULL,
rank INT CHECK(rank>0)
);

CREATE TABLE footballer (
id serial PRIMARY KEY NOT NULL,
firstname VARCHAR(30) NOT NULL,
lastname VARCHAR(30) NOT NULL,
goals INT CHECK(goals>0),
fk_team INT REFERENCES team(id)
);