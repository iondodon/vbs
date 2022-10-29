INSERT INTO vehicle_category(id, category, price_per_day)
VALUES
(1, 'SMALL_CAR', 1.01);

INSERT INTO bookingdate(id, bdate)
VALUES
(1, '2022-10-31'),
(2, '2022-10-30');

INSERT INTO vehicle(id, uuid, fuel_type, make, model, registration_number, vehicle_category_id)
VALUES
(1, '2fb0434f-83eb-43b2-adf8-e90e630072e1', 'PETROL', 'Honda', '123', 'ABC 123', 1),
(2, '5e3a0864-1bdb-45e1-a8df-873f7d2ce81b', 'PETROL', 'Mazda', '321', 'VVV 435', 1);

INSERT INTO booking(id, uuid, vehicle_id)
VALUES
(1, '894cda41-1a9f-4fcb-9595-7d518318734e', 1),
(2, 'c755f78a-8c43-47f4-ac17-07d718f75881', 2);

INSERT INTO bookings_bookingdates(booking_id, bookingdate_id)
VALUES
(1, 1),
(2, 2);
