DROP TABLE IF EXISTS directions;
DROP SEQUENCE IF EXISTS direction_seq;

CREATE SEQUENCE direction_seq start 1 increment 1;

CREATE TABLE directions (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT
);

DROP TABLE IF EXISTS tests;
DROP SEQUENCE IF EXISTS test_seq;

CREATE SEQUENCE test_seq start 1 increment 1;

CREATE TABLE tests (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT
);

DROP TABLE IF EXISTS candidates;
DROP SEQUENCE IF EXISTS candidate_seq;

CREATE SEQUENCE candidate_seq start 1 increment 1;

CREATE TABLE candidates (
    id SERIAL PRIMARY KEY,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    photo BIGINT,
    description TEXT,
    cv_file BIGINT
);

DROP TABLE IF EXISTS candidate_tests;
DROP SEQUENCE IF EXISTS candidate_test_seq;

CREATE SEQUENCE candidate_test_seq start 1 increment 1;

CREATE TABLE candidate_tests (
    id SERIAL PRIMARY KEY,
    candidate_id INT NOT NULL,
    test_id INT NOT NULL,
    FOREIGN KEY (candidate_id) REFERENCES candidates (id),
    FOREIGN KEY (test_id) REFERENCES tests (id)
);

DROP TABLE IF EXISTS test_results;
DROP SEQUENCE IF EXISTS test_results_seq;

CREATE SEQUENCE test_results_seq start 1 increment 1;

CREATE TABLE test_results (
    id SERIAL PRIMARY KEY,
    candidate_test_id INT NOT NULL,
    date DATE NOT NULL,
    score DOUBLE PRECISION,
    FOREIGN KEY (candidate_test_id) REFERENCES candidate_tests (id)
);

DROP TABLE IF EXISTS candidate_directions;

CREATE TABLE candidate_directions (
    candidate_id BIGINT NOT NULL,
    direction_id BIGINT NOT NULL,
    PRIMARY KEY (candidate_id, direction_id),
    FOREIGN KEY (candidate_id) REFERENCES candidates (id),
    FOREIGN KEY (direction_id) REFERENCES directions (id)
);