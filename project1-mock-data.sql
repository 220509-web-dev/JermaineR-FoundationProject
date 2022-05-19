set search_path to store_app;

insert into user_roles (name) values ('ADMIN'), ('BASIC_USER'), ('PREMIUM_USER');

insert into app_users (first_name, last_name, email, username, password, role_id)
values ('Omar', 'James', 'simple@example.com', 'hidden', 'greaterthan4', 1);
values ('Emilia', 'Colleran', 'simpe@example.com', 'hidden2', 'greaterthan5', 2);
values ('Gayelord','Wheeler','gwheeler3@google.co.jp','gwheeler3','7Q4BSSAA3k', 3);
values ('Torrin', 'Hought', 'thought4@usa.gov', 'thought4', 'OtqSnJ2EtuV', 3);