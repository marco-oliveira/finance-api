CREATE TABLE person (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(255),
    numero VARCHAR(50),
    complemento VARCHAR(255),
    bairro VARCHAR(50),
    cep VARCHAR(50),
    cidade VARCHAR(100),
    estado VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person (
    name, ativo,
    logradouro, numero,
    complemento, bairro,
    cep, cidade, estado
) VALUES (
    'João Ninguém', TRUE,
    'Rua dos Bobos', '0',
    'QD15 LT16', 'Centro',
    '45000252', 'Anápolis', 'Goiás'
);

INSERT INTO person (
    name, ativo,
    logradouro, numero,
    complemento, bairro,
    cep, cidade, estado
) VALUES (
    'Fulano de Tal', TRUE,
    'Rua Siclano de tal', '522',
    'QD1 LT18', 'Vila Nova',
    '45852241', 'São Paulo', 'São Paulo'
);

INSERT INTO person (
    name, ativo,
    bairro,
    cep, cidade
) VALUES (
    'João Ninguém', FALSE,
    'Centro',
    '45000882', 'Anápolis'
);