CREATE TABLE IF NOT EXISTS anime (
    animeid UUID NOT NULL DEFAULT gen_RANDOM_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    year integer,
    image text
);