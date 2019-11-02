CREATE TABLE person (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL,
    street VARCHAR(255),
    number VARCHAR(50),
    complement VARCHAR(255),
    neighborhood VARCHAR(50),
    cep VARCHAR(50),
    city VARCHAR(100),
    state VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person (
    name, active,
    street, number,
    complement, neighborhood,
    cep, city, state
) VALUES (
    'João Ninguém', TRUE,
    'Rua dos Bobos', '0',
    'QD15 LT16', 'Centro',
    '45000252', 'Anápolis', 'Goiás'
);

INSERT INTO person (
    name, active,
    street, number,
    complement, neighborhood,
    cep, city, state
) VALUES (
    'Fulano de Tal', TRUE,
    'Rua Siclano de tal', '522',
    'QD1 LT18', 'Vila Nova',
    '45852241', 'São Paulo', 'São Paulo'
);

INSERT INTO person (
    name, active,
    neighborhood,
    cep, city
) VALUES (
    'João Ninguém', FALSE,
    'Centro',
    '45000882', 'Anápolis'
);