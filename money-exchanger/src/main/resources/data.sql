DROP TABLE IF EXISTS currency_rates;

CREATE TABLE currency_rates (
  currecy VARCHAR(250) NOT NULL,
  buy DOUBLE NOT NULL,
  sell DOUBLE NOT NULL
);


insert into currency_rates values('USD', '1.3392','1.3574');
insert into currency_rates  values('HKD', '0.1738','0.1698');


CREATE TABLE transactions (

);
