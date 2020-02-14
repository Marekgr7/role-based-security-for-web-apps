
INSERT INTO role(name) values ('ROLE_ADMIN');

INSERT INTO role(name) values ('ROLE_EMPLOYEE');

INSERT INTO role(name) values ('ROLE_CUSTOMER');

INSERT INTO loginandreg.user(username, email ,password , is_enabled)
            VALUES ('marek',
                    'marekgr7@gmail.com',
                    '$2a$10$h76INvsk9JMsZaEpIcjSjeclUxTPm00SHYaKrx4jPBj4I/zJn0T32',
                    true);

INSERT INTO loginandreg.user(username, email ,password , is_enabled)
VALUES ('paulina',
        'paulina@gmail.com',
        '$2a$10$h76INvsk9JMsZaEpIcjSjeclUxTPm00SHYaKrx4jPBj4I/zJn0T32',
        true);

INSERT INTO loginandreg.user(username, email ,password , is_enabled)
VALUES ('piotrek',
        'piotrek@gmail.com',
        '$2a$10$h76INvsk9JMsZaEpIcjSjeclUxTPm00SHYaKrx4jPBj4I/zJn0T32',
        true);

INSERT INTO user_roles(user_id , roles_id)
            values (1 , 1);


INSERT INTO user_roles(user_id , roles_id)
            values (2 , 2);

INSERT INTO user_roles(user_id , roles_id)
            values (3 , 3);

