CREATE TABLE fx_deals (
  deal_id VARCHAR(255) PRIMARY KEY,
  from_currency_code VARCHAR(3),
  to_currency_code VARCHAR(3),
  deal_timestamp TIMESTAMP,
  deal_amount DECIMAL(18, 2)
);