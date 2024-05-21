CREATE TABLE fx_deals (
    deal_id SERIAL PRIMARY KEY,
    from_currency_code VARCHAR(3) NOT NULL,
    to_currency_code VARCHAR(3) NOT NULL,
    deal_timestamp TIMESTAMP NOT NULL,
    deal_amount DOUBLE PRECISION NOT NULL
);