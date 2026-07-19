
create table delivery_attempts(
    id uuid primary key default gen_random_uuid(),
    event_id uuid not null references webhook_events(id),
    status varchar(20) not null,
    response_code integer,
    response_body text,
    error_message varchar(500),
    latency_ms bigint,
    attempted_at timestamp with time zone not null default now()
);

create index idx_delivery_attempts_event_id on delivery_attempts(event_id);