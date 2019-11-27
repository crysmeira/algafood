set foreign_key_checks = 0;

delete from kitchen;
delete from city;
delete from state;
delete from payment_type;
delete from grp;
delete from group_permission;
delete from permission;
delete from product;
delete from restaurant;
delete from restaurant_payment_type;
delete from user;
delete from user_group;
delete from order_table;
delete from order_item;

set foreign_key_checks = 1;

alter table kitchen auto_increment = 1;
alter table city auto_increment = 1;
alter table state auto_increment = 1;
alter table payment_type auto_increment = 1;
alter table grp auto_increment = 1;
alter table group_permission auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table restaurant_payment_type auto_increment = 1;
alter table user auto_increment = 1;
alter table user_group auto_increment = 1;
alter table order_table auto_increment = 1;
alter table order_item auto_increment = 1;

insert into kitchen (id, name) values (1, 'Thai');
insert into kitchen (id, name) values (2, 'Mexican');

insert into state (id, name) values (1, 'California');
insert into state (id, name) values (2, 'Oregon');

insert into city (id, name, state_id) values (1, 'San Francisco', 1);
insert into city (id, name, state_id) values (2, 'Oakland', 1);
insert into city (id, name, state_id) values (3, 'Portland', 2);

insert into restaurant (id, name, delivery_fee, kitchen_id, date_register, date_update, address_city_id, address_street, address_number, address_zip_code) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, 'El Camino', '12', '93533');
insert into restaurant (id, name, delivery_fee, kitchen_id, date_register, date_update) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, delivery_fee, kitchen_id, date_register, date_update) values (3, 'Chipotle', 15, 2, utc_timestamp, utc_timestamp);

insert into payment_type (id, description) values (1, 'Credit card');
insert into payment_type (id, description) values (2, 'Debit card');
insert into payment_type (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'Search kitchen', 'Allow search for kitchen');
insert into permission (id, name, description) values (2, 'Edit kitchen', 'Allow edit kitchen');

insert into restaurant_payment_type (restaurant_id, payment_type_id) values (1, 1);
insert into restaurant_payment_type (restaurant_id, payment_type_id) values (1, 2);
insert into restaurant_payment_type (restaurant_id, payment_type_id) values (2, 3);
insert into restaurant_payment_type (restaurant_id, payment_type_id) values (3, 1);
insert into restaurant_payment_type (restaurant_id, payment_type_id) values (3, 3);

insert into product (id, name, description, price, active, restaurant_id) values (1, 'Lasagna', 'Delicious lasagna', 18.50, true, 3);
insert into product (id, name, description, price, active, restaurant_id) values (2, 'Yellow Curry', 'Yellow Curry', 15.00, true, 1);

insert into grp (id, name) values (1, 'ADMIN');
insert into grp (id, name) values (2, 'OTHER');

insert into user (id, name, email, password, date_register) values (1, 'Name 1', 'name1@email.com', 'myPass', utc_timestamp);
insert into user (id, name, email, password, date_register) values (2, 'Name 2', 'name2@email.com', 'yourPass', utc_timestamp);

insert into group_permission (grp_id, permission_id) values (1, 1);
insert into group_permission (grp_id, permission_id) values (1, 2);
insert into group_permission (grp_id, permission_id) values (2, 1);

insert into user_group (user_id, grp_id) values (1, 1);
insert into user_group (user_id, grp_id) values (2, 2);

insert into order_table (id, subtotal, delivery_fee, total_value, order_status, creation_date, restaurant_id, payment_type_id, user_client_id, address_number, address_street, address_zip_code, address_city_id) values (1, 13.99, 2.99, 16.98, 'CONFIRMED', utc_timestamp, 1, 1, 1, 13, 'A street', 32323, 1);

insert into order_item (id, quantity, unit_price, total_price, observation, product_id, order_id) values (1, 2, 3.99, 7.98, 'obs', 1, 1);