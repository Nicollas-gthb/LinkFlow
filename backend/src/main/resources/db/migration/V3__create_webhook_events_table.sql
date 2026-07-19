
create table webhook_events(
    id uuid primary key default gen_random_uuid(),
    integration_id uuid not null references integrations(id),
    payload text not null,
    headers text,
    status varchar(20) not null default 'PENDING',
    received_at timestamp with time zone not null default now()
);

create index idx_webhook_events_integration_id on webhook_events(integration_id);
create index idx_webhook_events_status on webhook_events(status);