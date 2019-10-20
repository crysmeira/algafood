insert into kitchen (id, name) values (1, 'Thai');
insert into kitchen (id, name) values (2, 'Mexican');

insert into restaurant (id, name, delivery_fee, kitchen_id) values (1, 'Thai Gourmet', 10, 1);
insert into restaurant (id, name, delivery_fee, kitchen_id) values (2, 'Thai Delivery', 9.50, 1);
insert into restaurant (id, name, delivery_fee, kitchen_id) values (3, 'Chipotle', 15, 2);

insert into state (id, name) values (1, 'California');
insert into state (id, name) values (2, 'Oregon');

insert into city (id, name, state_id) values (1, 'San Francisco', 1);
insert into city (id, name, state_id) values (2, 'Oakland', 1);
insert into city (id, name, state_id) values (3, 'Portland', 2);

insert into payment_type (id, description) values (1, 'Credit card');
insert into payment_type (id, description) values (2, 'Debit card');
insert into payment_type (id, description) values (3, 'Money');

insert into permission (id, name, description) values (1, 'Search kitchen', 'Allow search for kitchen');
insert into permission (id, name, description) values (2, 'Edit kitchen', 'Allow edit kitchen');