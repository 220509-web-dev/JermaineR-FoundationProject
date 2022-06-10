--establish a 'namespace' for related DB entities to exist within
create schema store_app;

-- convenience command that will help us to reference the schema created above
set search_path to store_app; 

create table user_roles (
  id    int generated always as identity primary key,
  name  varchar unique not null
);

create table app_users (
  id            int generated always as identity,
  first_name    varchar not null,
  last_name     varchar not null,
  email         varchar unique not null,
  username      varchar unique not null check (length(username) >= 4),
  password      varchar not null check (length(password) >= 8),
  role_id       int,

  constraint app_users_pk
  primary key (id),

  constraint user_roles_fk
  foreign key (role_id)
  references user_roles(id)


);

