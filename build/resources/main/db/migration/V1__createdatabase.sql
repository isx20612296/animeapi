CREATE TABLE IF NOT EXISTS anime (
    animeid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    year integer,
    image text
);

INSERT INTO anime(name, description, type, year, image) VALUES (
    'Shingeki No Kyoigin',
    'Enano titan',
    'tv',
    2009,
    'fotodeltitanmartillo');

    CREATE TABLE IF NOT EXISTS users (
        username text,
        password text -- TODO(desar contra de forma més segura)
    );

    INSERT INTO users VALUES ( 'user1' , 'pass' );