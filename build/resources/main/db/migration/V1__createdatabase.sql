CREATE TABLE IF NOT EXISTS anime (
    animeid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    year text,
    image text
);

--CREATE TABLE IF NOT EXISTS authors (
--    authorid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
--    name text,
--    image text
--);

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
    password text -- TODO(desar contra de forma més segura)
);

INSERT INTO users (username, password) VALUES
    ( 'user1' , 'pass' ),
    ( 'user2' , 'pass' ),
    ( 'user3' , 'pass' ),
    ( 'user4' , 'pass' ),
    ( 'user5' , 'pass' ),
    ( 'user6' , 'pass' ),
    ( 'user7' , 'pass' ),
    ( 'user8' , 'pass' ),
    ( 'user9' , 'pass' ),
    ( 'user10' , 'pass' ),
    ( 'user11' , 'pass' ),
    ( 'user12' , 'pass' ),
    ( 'user13' , 'pass' ),
    ( 'user14' , 'pass' ),
    ( 'user15' , 'pass' ),
    ( 'user16' , 'pass' )
;

CREATE TABLE files (
    fileid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    contenttype TEXT,
    data bytea
);
