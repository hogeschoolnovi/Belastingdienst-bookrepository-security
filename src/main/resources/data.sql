
INSERT INTO authors (name) VALUES ("William Shakespear"),
                                  ("Paul van Loon");

INSERT INTO books (id, title, published, author_name) values (1001, "initialized", "2003/09/09", "William Shakespear"),
                                                             (1002, "testbook", "2003/08/07", "Paul van Loon");


INSERT INTO covers(id, color, material) VALUES (2001, 'blue', 'plastic');

UPDATE books SET books.cover=2001 WHERE books.id=1001;

UPDATE books SET books.author_name="Paul van Loon" WHERE books.id=1001;
