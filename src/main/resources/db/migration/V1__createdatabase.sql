CREATE TABLE IF NOT EXISTS anime (
    animeid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    year integer,
    image text
);