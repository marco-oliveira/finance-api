CREATE TABLE billing (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL,
    due_date DATE NOT NULL,
    payment_date DATE,
    value DECIMAL(10,2) NOT NULL,
    note VARCHAR(100),
    type VARCHAR(20) NOT NULL,
    id_category BIGINT(20) NOT NULL,
    id_person BIGINT(20) NOT NULL,
    FOREIGN KEY (id_category) REFERENCES category(id),
    FOREIGN KEY (id_person) REFERENCES person(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO billing (description, due_date, payment_date, value, note, type, id_category, id_person)
    VALUES ('Salário mensal', '2020-01-07', null, 6500.00, 'Distribuição dos lucros', 'RECEITA', 5, 1);
INSERT INTO billing (description, due_date, payment_date, value, note, type, id_category, id_person)
    VALUES ('Escola', '2020-01-15', '2020-01-15', 410.00, 'Mensalidade da escola', 'DESPESA', 5, 1);
INSERT INTO billing (description, due_date, payment_date, value, note, type, id_category, id_person)
    VALUES ('Tia da marmita', '2020-01-07', '2020-01-15', 130.00, 'Almoço diário', 'DESPESA', 2, 1);
INSERT INTO billing (description, due_date, payment_date, value, note, type, id_category, id_person)
    VALUES ('Salário mensal', '2020-01-07', null, 3000.00, 'Distribuição dos lucros', 'RECEITA', 5, 2);
INSERT INTO billing (description, due_date, payment_date, value, note, type, id_category, id_person)
    VALUES ('Salário mensal', '2020-01-07', null, 2500.00, 'Distribuição dos lucros', 'RECEITA', 5, 3);
INSERT INTO billing (description, due_date, payment_date, value, note, type, id_category, id_person)
    VALUES ('Remédio', '2020-01-07', '2020-01-07', 100.00, null, 'DESPESA', 4, 2);