INSERT INTO "SkyEng".public.person (id, first_name, second_name, adress_id, passport_number)
VALUES (DEFAULT, 'Pavel', 'Kuznetsov', 2, 123456);

INSERT INTO "SkyEng".public.address(id, address_line1, address_line2, city, country, index)
VALUES (DEFAULT, 'street1 st.', '', 'Moscow', 'Russia', 152055),
       (DEFAULT, 'street2 st.', '', 'Ivanovo', 'Russia', 155869),
       (DEFAULT, 'street3 st.', '', 'Perm', 'Russia', 199550),
       (DEFAULT, 'street4 st.', '', 'Minsk', 'Belorussia', 123550);

INSERT INTO "SkyEng".public.post_office(id, index, name, adress_id)
VALUES (DEFAULT, 152055, 'main VAO', 1),
       (DEFAULT, 155869, 'Ivanovo post', 2),
       (DEFAULT, 199550, 'main Perm', 3),
       (DEFAULT, 123550, 'ChicCherick', 4);