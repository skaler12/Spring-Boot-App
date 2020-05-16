INSERT INTO `user` (`email`, `first_name`, `last_name`, `password`, `username`) VALUES ('admin@admin.com', 'admin', 'admin', '$2a$04$n6WIRDQlIByVFi.5rtQwEOTAzpzLPzIIG/O6quaxRKY2LlIHG8uty', 'admin');


INSERT INTO `role` (`description`, `name`) VALUES ('ROLE_ADMIN', 'ADMIN');
INSERT INTO `role` (`description`, `name`) VALUES ('ROLE_USER', 'USER');
INSERT INTO `role` (`description`, `name`) VALUES ('ROLE_SUPERVISOR', 'SUPERVISOR');

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES ('1', '1');

INSERT INTO `music_store` (`equipment_type`,`store_name`,`street`, `street_number`)
VALUES ('Sklep z artukułami dla Perkusistów. Duży asortyment, produkty dostępne od ręki.','Pro_Drummer ','Czerniakowska',11);
INSERT INTO `music_store` (`equipment_type`,`store_name`,`street`, `street_number`)
VALUES ('Największy sklep muzyczny w Warszawie. Profesjonalna obsługa i najniższe ceny !','Best Music','Marszalkowska',132);



