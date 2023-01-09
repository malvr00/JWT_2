insert into `user` (user_id, username, password, nickname, activated)
values (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);
insert into `user` (user_id, username, password, nickname, activated)
values (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);

INSERT INTO authority (authority_name) values ('ROLE_USER');
INSERT INTO authority (authority_name) values ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (user_id, authority_name) VALUES(1, 'ROLE_USER');
INSERT INTO USER_AUTHORITY (user_id, authority_name) VALUES(1, 'ROLE_ADMIN');
INSERT INTO USER_AUTHORITY (user_id, authority_name) VALUES(2, 'ROLE_USER');