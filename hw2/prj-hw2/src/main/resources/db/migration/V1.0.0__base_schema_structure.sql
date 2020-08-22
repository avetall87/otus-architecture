-- base schema structures version 1.0.0

create table users (
    id bigserial constraint user_pk PRIMARY KEY,
    first_name varchar(1024) not null,
    last_name varchar(1024),
    patronymic varchar(1024),
    birth_date timestamp,
    description text,
    phone varchar(100),
    email varchar(1024) not null,
    password varchar(4000) not null,
    creation_date timestamp default localtimestamp,
    modification_date timestamp
);

create unique index uidx_users_email on users (email);
create index idx_users_phone on users (phone);
create index idx_users_password on users (password);
create unique index uidx_users_email_password on users (email, password);
create index idx_users_creation_date on users (creation_date);
create index fidx_users_creation_date on users (date_trunc('day',creation_date));
create index idx_modification_date on users (modification_date);
create index fidx_modification_date on users (date_trunc('day',modification_date));
