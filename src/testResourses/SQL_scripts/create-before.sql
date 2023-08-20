INSERT INTO "SkyEngTest".public.address(id, address_line1, address_line2, city, country, index)
VALUES (DEFAULT, 'street1 st.', '', 'Moscow', 'Russia', 152055),
       (DEFAULT, 'street2 st.', '', 'Ivanovo', 'Russia', 155869),
       (DEFAULT, 'street3 st.', '', 'Perm', 'Russia', 199550),
       (DEFAULT, 'street4 st.', '', 'Minsk', 'Belorussia', 123550);

INSERT INTO "SkyEngTest".public.person (id, first_name, second_name, adress_id, passport_number)
VALUES (DEFAULT, 'Pavel', 'Kuznetsov', null, 123456);


INSERT INTO "SkyEngTest".public.post_office(id, index, name, adress_id)
VALUES (DEFAULT, 152055, 'main VAO', null),
       (DEFAULT, 155869, 'Ivanovo post', null),
       (DEFAULT, 199550, 'main Perm', null),
       (DEFAULT, 123550, 'ChicCherick', null);