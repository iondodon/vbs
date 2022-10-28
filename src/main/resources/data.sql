INSERT INTO vehicle_category(id, category, price_per_day)
VALUES
(1, 'SMALL_CAR', 1.01);

INSERT INTO vehicle(id, uuid, fuel_type, make, model, registration_number, vehicle_category_id)
VALUES
(1, '2fb0434f-83eb-43b2-adf8-e90e630072e1', 'PETROL', 'Honda', '123', 'ABC 123', 1);