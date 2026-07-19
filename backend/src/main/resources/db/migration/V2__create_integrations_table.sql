
create table integrations(
    id uuid primary key default gen_random_uuid(),
    user_id uuid not null references users(id),
    name varchar(100) not null,
    description varchar(255),
    source_url varchar(500) not null,
    target_url varchar(500) not null,
    secret varchar(255),
    active boolean not null default true,
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now()
);

create index idx_integrations_user_id on integrations(user_id);