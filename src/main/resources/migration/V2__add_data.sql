INSERT INTO public."сlient" (client_id, c_name, c_address, c_phone_number)
VALUES (1, 'Fedor', 'Moscow', '89175972620');
INSERT INTO public."сlient" (client_id, c_name, c_address, c_phone_number)
VALUES (142, 'Kostya', 'London', '89876543211');
INSERT INTO public."сlient" (client_id, c_name, c_address, c_phone_number)
VALUES (613, 'Artur', 'SPB', '89876543211');
INSERT INTO public."сlient" (client_id, c_name, c_address, c_phone_number)
VALUES (164, 'Vova', 'Berlin', '89876543211');
INSERT INTO public."сlient" (client_id, c_name, c_address, c_phone_number)
VALUES (341, 'Emil', 'Kiev', '89876543211');
INSERT INTO public."сlient" (client_id, c_name, c_address, c_phone_number)
VALUES (141, 'Fedor', 'Moscow', '89876543211');

INSERT INTO public."orders" (id_order, client_id, o_date_priem, o_price, o_predopl, o_tel_number)
VALUES (200, 142, '1/1/1999', 1245, 1000, '89876543211');
INSERT INTO public."orders" (id_order, client_id, o_date_priem, o_price, o_predopl, o_tel_number)
VALUES (201, 142, '1/1/1999', 12455, 1030, '89876543211');
INSERT INTO public."orders" (id_order, client_id, o_date_priem, o_price, o_predopl, o_tel_number)
VALUES (202, 341, '1/1/1999', 124535, 1400, '89876543211');
INSERT INTO public."orders" (id_order, client_id, o_date_priem, o_price, o_predopl, o_tel_number)
VALUES (203, 341, '1/1/1999', 124545, 100, '89876543211');
INSERT INTO public."orders" (id_order, client_id, o_date_priem, o_price, o_predopl, o_tel_number)
VALUES (204, 1, '1/1/1999', 1124245, 10, '89876543211');
