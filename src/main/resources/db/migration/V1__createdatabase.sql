CREATE TABLE IF NOT EXISTS anime (
    animeid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    year text,
    image text
);

CREATE TABLE IF NOT EXISTS authors (
    authorid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    image text
);

INSERT INTO authors(name, image) VALUES
    ('Matias','fotoMatias'),
    ('Joan','fotoMatias'),
    ('JoseMiguel','fotoMatias'),
    ('Isidro','fotoMatias'),
    ('Ramon','fotoMatias'),
    ('Genis','fotoMatias'),
    ('Yoko Ono','fotoMatias'),
    ('Miyazaki','fotoMatias'),
    ('Amanda','fotoMatias'),
    ('Maria','fotoMatias'),
    ('Isabel','fotoMatias'),
    ('Mizuki','fotoMatias'),
    ('Matilde','fotoMatias'),
    ('Paula','fotoMatias');

CREATE TABLE IF NOT EXISTS authors_anime (
    animeid UUID NOT NULL DEFAULT gen_random_uuid(),
    authorid UUID NOT NULL DEFAULT gen_random_uuid(),
    PRIMARY KEY(animeid, authorid)
);

INSERT INTO anime(name, description, type, year, image) VALUES
    ('Shingeki No Kyoigin', 'Enano titan', 'tv', 2009, 'fotodeltitanmartillo'),
    ('A2','A2desc','A2type','2000','A2image'),
    ('A3','A3desc','A3type','2000','A3image'),
    ('A4','A4desc','A4type','2000','A4image'),
    ('A5','A5desc','A5type','2000','A5image'),
    ('A6','A6desc','A6type','2000','A6image'),
    ('A7','A7desc','A7type','2000','A7image'),
    ('A8','A8desc','A8type','2000','A8image'),
    ('A9','A9desc','A9type','2000','A9image'),
    ('A10','A10desc','A10type','2000','A10image'),
    ('A11','A11desc','A11type','2000','A11image'),
    ('A12','A12desc','A12type','2000','A12image'),
    ('A13','A13desc','A13type','2000','A13image'),
    ('A14','A14desc','A14type','2000','A14image'),
    ('A15','A15desc','A15type','2000','A15image');


CREATE TABLE IF NOT EXISTS users (
    userid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    username text,
    password text
);

CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO users (username, password) VALUES
    ( 'user1' , crypt('pass', gen_salt('bf'))),
    ( 'user2' , crypt('pass', gen_salt('bf'))),
    ( 'user3' , crypt('pass', gen_salt('bf'))),
    ( 'user4' , crypt('pass', gen_salt('bf'))),
    ( 'user5' , crypt('pass', gen_salt('bf'))),
    ( 'user6' , crypt('pass', gen_salt('bf'))),
    ( 'user7' , crypt('pass', gen_salt('bf'))),
    ( 'user8' , crypt('pass', gen_salt('bf'))),
    ( 'user9' , crypt('pass', gen_salt('bf'))),
    ( 'user10' , crypt('pass', gen_salt('bf'))),
    ( 'user11' , crypt('pass', gen_salt('bf'))),
    ( 'user12' , crypt('pass', gen_salt('bf'))),
    ( 'user13' , crypt('pass', gen_salt('bf'))),
    ( 'user14' , crypt('pass', gen_salt('bf'))),
    ( 'user15' , crypt('pass', gen_salt('bf'))),
    ( 'user16' , crypt('pass', gen_salt('bf')))
;

CREATE TABLE files (
    fileid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    contenttype TEXT,
    data bytea
);

INSERT INTO authors_anime VALUES
((SELECT animeid FROM anime WHERE name='Shingeki No Kyoigin'),(SELECT authorid FROM authors WHERE name='Matias')),
((SELECT animeid FROM anime WHERE name='Shingeki No Kyoigin'),(SELECT authorid FROM authors WHERE name='Isabel')),
((SELECT animeid FROM anime WHERE name='Shingeki No Kyoigin'),(SELECT authorid FROM authors WHERE name='Amanda')),
((SELECT animeid FROM anime WHERE name='A2'),(SELECT authorid FROM authors WHERE name='Joan')),
((SELECT animeid FROM anime WHERE name='A2'),(SELECT authorid FROM authors WHERE name='JoseMiguel')),
((SELECT animeid FROM anime WHERE name='A3'),(SELECT authorid FROM authors WHERE name='Matias')),
((SELECT animeid FROM anime WHERE name='A3'),(SELECT authorid FROM authors WHERE name='Isidro')),
((SELECT animeid FROM anime WHERE name='A3'),(SELECT authorid FROM authors WHERE name='Miyazaki')),
((SELECT animeid FROM anime WHERE name='A4'),(SELECT authorid FROM authors WHERE name='Joan')),
((SELECT animeid FROM anime WHERE name='A4'),(SELECT authorid FROM authors WHERE name='Genis')),
((SELECT animeid FROM anime WHERE name='A5'),(SELECT authorid FROM authors WHERE name='Ramon')),
((SELECT animeid FROM anime WHERE name='A5'),(SELECT authorid FROM authors WHERE name='Yoko Ono')),
((SELECT animeid FROM anime WHERE name='A5'),(SELECT authorid FROM authors WHERE name='Amanda')),
((SELECT animeid FROM anime WHERE name='A6'),(SELECT authorid FROM authors WHERE name='Matias')),
((SELECT animeid FROM anime WHERE name='A6'),(SELECT authorid FROM authors WHERE name='JoseMiguel')),
((SELECT animeid FROM anime WHERE name='A7'),(SELECT authorid FROM authors WHERE name='Paula')),
((SELECT animeid FROM anime WHERE name='A7'),(SELECT authorid FROM authors WHERE name='Joan')),
((SELECT animeid FROM anime WHERE name='A7'),(SELECT authorid FROM authors WHERE name='Isidro')),
((SELECT animeid FROM anime WHERE name='A8'),(SELECT authorid FROM authors WHERE name='Isabel')),
((SELECT animeid FROM anime WHERE name='A8'),(SELECT authorid FROM authors WHERE name='JoseMiguel')),
((SELECT animeid FROM anime WHERE name='A9'),(SELECT authorid FROM authors WHERE name='Mizuki')),
((SELECT animeid FROM anime WHERE name='A9'),(SELECT authorid FROM authors WHERE name='Matilde')),
((SELECT animeid FROM anime WHERE name='A9'),(SELECT authorid FROM authors WHERE name='Isabel')),
((SELECT animeid FROM anime WHERE name='A10'),(SELECT authorid FROM authors WHERE name='Matilde')),
((SELECT animeid FROM anime WHERE name='A10'),(SELECT authorid FROM authors WHERE name='Genis')),
((SELECT animeid FROM anime WHERE name='A10'),(SELECT authorid FROM authors WHERE name='JoseMiguel')),
((SELECT animeid FROM anime WHERE name='A11'),(SELECT authorid FROM authors WHERE name='Matias')),
((SELECT animeid FROM anime WHERE name='A11'),(SELECT authorid FROM authors WHERE name='Mizuki')),
((SELECT animeid FROM anime WHERE name='A12'),(SELECT authorid FROM authors WHERE name='Joan')),
((SELECT animeid FROM anime WHERE name='A12'),(SELECT authorid FROM authors WHERE name='Maria')),
((SELECT animeid FROM anime WHERE name='A13'),(SELECT authorid FROM authors WHERE name='Amanda')),
((SELECT animeid FROM anime WHERE name='A14'),(SELECT authorid FROM authors WHERE name='Ramon')),
((SELECT animeid FROM anime WHERE name='A14'),(SELECT authorid FROM authors WHERE name='Maria')),
((SELECT animeid FROM anime WHERE name='A15'),(SELECT authorid FROM authors WHERE name='Isidro'));

CREATE TABLE IF NOT EXISTS genres (
    genreid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    label text
);

INSERT INTO genres(label) VALUES
    ('Genere 1'),
    ('Genere 2'),
    ('Genere 3'),
    ('Genere 4'),
    ('Genere 5'),
    ('Genere 6');

CREATE TABLE IF NOT EXISTS genres_anime (
    animeid UUID NOT NULL DEFAULT gen_random_uuid(),
    genreid UUID NOT NULL DEFAULT gen_random_uuid(),
    PRIMARY KEY(animeid, genreid)
);

INSERT INTO genres_anime VALUES
    ((SELECT animeid FROM anime WHERE name='Shingeki No Kyoigin'),(SELECT genreid FROM genres WHERE label='Genere 1')),
    ((SELECT animeid FROM anime WHERE name='Shingeki No Kyoigin'),(SELECT genreid FROM genres WHERE label='Genere 2')),
    ((SELECT animeid FROM anime WHERE name='A2'),(SELECT genreid FROM genres WHERE label='Genere 3')),
    ((SELECT animeid FROM anime WHERE name='A2'),(SELECT genreid FROM genres WHERE label='Genere 4')),
    ((SELECT animeid FROM anime WHERE name='A3'),(SELECT genreid FROM genres WHERE label='Genere 5')),
    ((SELECT animeid FROM anime WHERE name='A3'),(SELECT genreid FROM genres WHERE label='Genere 6')),
    ((SELECT animeid FROM anime WHERE name='A4'),(SELECT genreid FROM genres WHERE label='Genere 1')),
    ((SELECT animeid FROM anime WHERE name='A4'),(SELECT genreid FROM genres WHERE label='Genere 4')),
    ((SELECT animeid FROM anime WHERE name='A5'),(SELECT genreid FROM genres WHERE label='Genere 2')),
    ((SELECT animeid FROM anime WHERE name='A5'),(SELECT genreid FROM genres WHERE label='Genere 5')),
    ((SELECT animeid FROM anime WHERE name='A6'),(SELECT genreid FROM genres WHERE label='Genere 3')),
    ((SELECT animeid FROM anime WHERE name='A6'),(SELECT genreid FROM genres WHERE label='Genere 6')),
    ((SELECT animeid FROM anime WHERE name='A7'),(SELECT genreid FROM genres WHERE label='Genere 1')),
    ((SELECT animeid FROM anime WHERE name='A7'),(SELECT genreid FROM genres WHERE label='Genere 3')),
    ((SELECT animeid FROM anime WHERE name='A8'),(SELECT genreid FROM genres WHERE label='Genere 2')),
    ((SELECT animeid FROM anime WHERE name='A8'),(SELECT genreid FROM genres WHERE label='Genere 4')),
    ((SELECT animeid FROM anime WHERE name='A9'),(SELECT genreid FROM genres WHERE label='Genere 3')),
    ((SELECT animeid FROM anime WHERE name='A9'),(SELECT genreid FROM genres WHERE label='Genere 5')),
    ((SELECT animeid FROM anime WHERE name='A10'),(SELECT genreid FROM genres WHERE label='Genere 4')),
    ((SELECT animeid FROM anime WHERE name='A10'),(SELECT genreid FROM genres WHERE label='Genere 6')),
    ((SELECT animeid FROM anime WHERE name='A11'),(SELECT genreid FROM genres WHERE label='Genere 1')),
    ((SELECT animeid FROM anime WHERE name='A11'),(SELECT genreid FROM genres WHERE label='Genere 5')),
    ((SELECT animeid FROM anime WHERE name='A12'),(SELECT genreid FROM genres WHERE label='Genere 2')),
    ((SELECT animeid FROM anime WHERE name='A12'),(SELECT genreid FROM genres WHERE label='Genere 6')),
    ((SELECT animeid FROM anime WHERE name='A13'),(SELECT genreid FROM genres WHERE label='Genere 3')),
    ((SELECT animeid FROM anime WHERE name='A13'),(SELECT genreid FROM genres WHERE label='Genere 1')),
    ((SELECT animeid FROM anime WHERE name='A14'),(SELECT genreid FROM genres WHERE label='Genere 4')),
    ((SELECT animeid FROM anime WHERE name='A14'),(SELECT genreid FROM genres WHERE label='Genere 2')),
    ((SELECT animeid FROM anime WHERE name='A15'),(SELECT genreid FROM genres WHERE label='Genere 5')),
    ((SELECT animeid FROM anime WHERE name='A15'),(SELECT genreid FROM genres WHERE label='Genere 3'));

CREATE TABLE IF NOT EXISTS favorite (
    userid UUID NOT NULL DEFAULT gen_random_uuid(),
    animeid UUID NOT NULL DEFAULT gen_random_uuid(),
    PRIMARY KEY(userid, animeid)
);

INSERT INTO favorite VALUES
((SELECT userid FROM users WHERE username='user1'),(SELECT animeid FROM anime WHERE name='Shingeki No Kyoigin')),
((SELECT userid FROM users WHERE username='user1'),(SELECT animeid FROM anime WHERE name='A3')),
((SELECT userid FROM users WHERE username='user2'),(SELECT animeid FROM anime WHERE name='A2')),
((SELECT userid FROM users WHERE username='user2'),(SELECT animeid FROM anime WHERE name='A4')),
((SELECT userid FROM users WHERE username='user3'),(SELECT animeid FROM anime WHERE name='A3')),
((SELECT userid FROM users WHERE username='user3'),(SELECT animeid FROM anime WHERE name='A5')),
((SELECT userid FROM users WHERE username='user4'),(SELECT animeid FROM anime WHERE name='A7')),
((SELECT userid FROM users WHERE username='user4'),(SELECT animeid FROM anime WHERE name='A8')),
((SELECT userid FROM users WHERE username='user5'),(SELECT animeid FROM anime WHERE name='A6')),
((SELECT userid FROM users WHERE username='user5'),(SELECT animeid FROM anime WHERE name='A9')),
((SELECT userid FROM users WHERE username='user6'),(SELECT animeid FROM anime WHERE name='Shingeki No Kyoigin')),
((SELECT userid FROM users WHERE username='user6'),(SELECT animeid FROM anime WHERE name='A10')),
((SELECT userid FROM users WHERE username='user7'),(SELECT animeid FROM anime WHERE name='A2')),
((SELECT userid FROM users WHERE username='user7'),(SELECT animeid FROM anime WHERE name='A12')),
((SELECT userid FROM users WHERE username='user8'),(SELECT animeid FROM anime WHERE name='A3')),
((SELECT userid FROM users WHERE username='user8'),(SELECT animeid FROM anime WHERE name='A11')),
((SELECT userid FROM users WHERE username='user9'),(SELECT animeid FROM anime WHERE name='A6')),
((SELECT userid FROM users WHERE username='user9'),(SELECT animeid FROM anime WHERE name='A13')),
((SELECT userid FROM users WHERE username='user10'),(SELECT animeid FROM anime WHERE name='A10')),
((SELECT userid FROM users WHERE username='user10'),(SELECT animeid FROM anime WHERE name='A9')),
((SELECT userid FROM users WHERE username='user11'),(SELECT animeid FROM anime WHERE name='A13')),
((SELECT userid FROM users WHERE username='user11'),(SELECT animeid FROM anime WHERE name='A2')),
((SELECT userid FROM users WHERE username='user12'),(SELECT animeid FROM anime WHERE name='A4')),
((SELECT userid FROM users WHERE username='user12'),(SELECT animeid FROM anime WHERE name='A7')),
((SELECT userid FROM users WHERE username='user13'),(SELECT animeid FROM anime WHERE name='A13')),
((SELECT userid FROM users WHERE username='user13'),(SELECT animeid FROM anime WHERE name='A4')),
((SELECT userid FROM users WHERE username='user14'),(SELECT animeid FROM anime WHERE name='A12')),
((SELECT userid FROM users WHERE username='user14'),(SELECT animeid FROM anime WHERE name='A13')),
((SELECT userid FROM users WHERE username='user15'),(SELECT animeid FROM anime WHERE name='A5')),
((SELECT userid FROM users WHERE username='user15'),(SELECT animeid FROM anime WHERE name='A15'));

CREATE TABLE IF NOT EXISTS comment (
    commentid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    userid UUID,
    animeid UUID,
    message text
);

INSERT INTO comment (userid, animeid, message) VALUES
((SELECT userid FROM users WHERE username='user1'),(SELECT animeid FROM anime WHERE name='Shingeki No Kyoigin'),'This anime is so cool I recommend it'),
((SELECT userid FROM users WHERE username='user2'),(SELECT animeid FROM anime WHERE name='A2'),'It had some moments of fun but I''m not feeling satisfied'),
((SELECT userid FROM users WHERE username='user3'),(SELECT animeid FROM anime WHERE name='A3'),'Buen anime recomiendo 10/10'),
((SELECT userid FROM users WHERE username='user4'),(SELECT animeid FROM anime WHERE name='A4'),'This anime is so cool I recommend it'),
((SELECT userid FROM users WHERE username='user5'),(SELECT animeid FROM anime WHERE name='A5'),'It had some moments of fun but I''m not feeling satisfied'),
((SELECT userid FROM users WHERE username='user6'),(SELECT animeid FROM anime WHERE name='A6'),'Buen anime recomiendo 10/10'),
((SELECT userid FROM users WHERE username='user7'),(SELECT animeid FROM anime WHERE name='A7'),'This anime is so cool I recommend it'),
((SELECT userid FROM users WHERE username='user8'),(SELECT animeid FROM anime WHERE name='A8'),'It had some moments of fun but I''m not feeling satisfied'),
((SELECT userid FROM users WHERE username='user9'),(SELECT animeid FROM anime WHERE name='A9'),'Buen anime recomiendo 10/10'),
((SELECT userid FROM users WHERE username='user10'),(SELECT animeid FROM anime WHERE name='A10'),'This anime is so cool I recommend it'),
((SELECT userid FROM users WHERE username='user11'),(SELECT animeid FROM anime WHERE name='A11'),'It had some moments of fun but I''m not feeling satisfied'),
((SELECT userid FROM users WHERE username='user12'),(SELECT animeid FROM anime WHERE name='A12'),'Buen anime recomiendo 10/10'),
((SELECT userid FROM users WHERE username='user13'),(SELECT animeid FROM anime WHERE name='A13'),'This anime is so cool I recommend it'),
((SELECT userid FROM users WHERE username='user14'),(SELECT animeid FROM anime WHERE name='A14'),'It had some moments of fun but I''m not feeling satisfied'),
((SELECT userid FROM users WHERE username='user15'),(SELECT animeid FROM anime WHERE name='A15'),'Buen anime recomiendo 10/10');