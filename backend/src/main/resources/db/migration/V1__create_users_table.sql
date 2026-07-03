
create table users(
    id uuid primary key default gen_random_uuid(),
    name varchar(100) not null,
    email varchar(255) not null unique,
    password varchar(255) not null,
    role varchar(20) not null default 'user',
    active boolean not null default true,
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now()
);

create index idx_user_email on users(email);