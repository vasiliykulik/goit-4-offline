ALTER TABLE pizzeria."order" ADD COLUMN phone text;
ALTER TABLE pizzeria."order" ADD COLUMN address text;
ALTER TABLE pizzeria."order" ADD COLUMN order_date timestamp without time zone;
ALTER TABLE pizzeria."order" ADD COLUMN status integer;
ALTER TABLE pizzeria."order" ADD COLUMN comment text;