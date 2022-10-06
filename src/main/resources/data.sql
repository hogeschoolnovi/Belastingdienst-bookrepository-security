
INSERT INTO authors (name) VALUES ('William Shakespear'),
                                  ('Paul van Loon');

INSERT INTO books (id, title, published, author_name) values (1001, 'initialized', '2003/09/09', 'William Shakespear'),
                                                             (1002, 'testbook', '2003/08/07', 'Paul van Loon');


INSERT INTO covers(id, color, material) VALUES (2001, 'blue', 'plastic');

UPDATE books SET cover=2001 WHERE id=1001;

UPDATE books SET author_name='Paul van Loon' WHERE id=1001;

INSERT INTO users (username, password, enabled, email) VALUES ('test', '$2a$12$aoyOCxY0e1LmiTSAV4hQtelwLwLURBdeSLGH1oibivgSslWjI0dSa', true, 'test@testing.tst');
INSERT INTO authorities (id, username, authority) VALUES (3001, 'test', 'ROLE_USER');
INSERT INTO authorities (id, username, authority) VALUES (3002, 'test', 'ROLE_ADMIN');
