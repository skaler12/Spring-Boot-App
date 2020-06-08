INSERT INTO `user` (`email`, `first_name`, `last_name`, `password`, `username`) VALUES ('admin@admin.com', 'admin', 'admin', '$2a$04$n6WIRDQlIByVFi.5rtQwEOTAzpzLPzIIG/O6quaxRKY2LlIHG8uty', 'admin');


INSERT INTO `role` (`description`, `name`) VALUES ('ROLE_ADMIN', 'ADMIN');
INSERT INTO `role` (`description`, `name`) VALUES ('ROLE_USER', 'USER');
INSERT INTO `role` (`description`, `name`) VALUES ('ROLE_SUPERVISOR', 'SUPERVISOR');

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES ('1', '1');

INSERT INTO `music_store` (`equipment_type`,`store_name`,`street`, `street_number`)
VALUES ('Lokalny sklep muzyczny specjalizujący się w asortymencie gitarowym.','MX Music','Gibraltarska',4);
INSERT INTO `music_store` (`equipment_type`,`store_name`,`street`, `street_number`)
VALUES ('Sklep z asortymentem detalicznym, stroiki, kostki, pałki perkusyjne. ','In hurt','Kuflewska',6);
INSERT INTO `music_store` (`equipment_type`,`store_name`,`street`, `street_number`)
VALUES ('Asortyment gitarowy, serwis gitar elektrycznych oraz personalizacja efektów. ','Megascena','Grochowska',175);
INSERT INTO `music_store` (`equipment_type`,`store_name`,`street`, `street_number`)
VALUES ('Sklep z asortymentem perkusyjnym. Instrumenty na każdą kieszeń. ','GuitarProject','Kadetów',20);
INSERT INTO `music_store` (`equipment_type`,`store_name`,`street`, `street_number`)
VALUES ('Sklep muzyczny na Kabatach. Zapraszamy, niedaleko metra. . ','MuzycznyMarket','Wąwozowa',2);
INSERT INTO `music_store` (`equipment_type`,`store_name`,`street`, `street_number`)
VALUES ('Gitarowy świat dla pasjonatów. Nie czekaj, odwiedź nas. ','Riff Megastore','Bokserska',62);
INSERT INTO `music_store` (`equipment_type`,`store_name`,`street`, `street_number`)
VALUES ('Instrumenty klawiszowe oraz dente, jedyny taki sklep. ','SuperSound','Połczyńska',51);
INSERT INTO `music_store` (`equipment_type`,`store_name`,`street`, `street_number`)
VALUES ('Sklep w metrze centrum. Wiesz gdzie nas szukać.. ','Metro','Plac Defilad',1);
INSERT INTO `music_store` (`equipment_type`,`store_name`,`street`, `street_number`)
VALUES ('Sklep z ukulele, zapraszamy! najlepszy wybów w Warszawie. ','GuitarProShop','Solec',52);
INSERT INTO `music_store` (`equipment_type`,`store_name`,`street`, `street_number`)
VALUES ('Sklep z nagłośnieniem koncertowym oraz mikrofonami. ','Pasja','Wiktorska',7);

