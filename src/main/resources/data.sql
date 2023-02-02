-- Fill Currencies
INSERT INTO currency (id_currency, name, code) VALUES
(1, 'U.S. dollar', 'USD'),
(2, 'Euro', 'EUR'),
(3, 'Japanese yen', 'JPY'),
(4, 'Sterling', 'GBP'),
(5, 'Peruvian Sol', 'PEN');

INSERT INTO exchange (id_exchange, date, id_origin_currency, id_destination_currency, exchange_rate) VALUES
(1,	'2023-02-01', 1, 5, 3.845),
(2,	'2023-02-01', 5, 1, 0.2601),
(3,	'2023-02-01', 2, 5, 4.193),
(4,	'2023-02-01', 5, 2, 0.2385),
(5,	'2023-02-01', 3, 5, 0.02973),
(6,	'2023-02-01', 5, 3, 33.640),
(7,	'2023-02-01', 4, 5, 4.745),
(8,	'2023-02-01', 5, 4, 0.2107);





