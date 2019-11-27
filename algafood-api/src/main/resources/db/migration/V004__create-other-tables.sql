create table group_permission (
	grp_id bigint not null, 
	permission_id bigint not null
) engine=InnoDB default charset=utf8;

create table grp (
	id bigint not null auto_increment, 
	name varchar(60) not null, 
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table payment_type (
	id bigint not null auto_increment, 
	description varchar(60) not null, 
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table permission (
	id bigint not null auto_increment, 
	description varchar(60) not null, 
	name varchar(100) not null, 
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table product (
	id bigint not null auto_increment, 
	active tinyint(1) not null,
	description text not null,
	name varchar(80), 
	price decimal(10,2), 
	restaurant_id bigint not null, 
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant (
	id bigint not null auto_increment, 
	address_number varchar(20), 
	address_street varchar(100), 
	address_zip_code varchar(5), 
	date_register datetime not null, 
	date_update datetime not null, 
	delivery_fee decimal(10,2) not null, 
	name varchar(80) not null, 
	address_city_id bigint, 
	kitchen_id bigint not null, 
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant_payment_type (
	restaurant_id bigint not null, 
	payment_type_id bigint not null
) engine=InnoDB default charset=utf8;

create table user (
	id bigint not null auto_increment, 
	date_register datetime not null, 
	email varchar(255) not null, 
	name varchar(80) not null, 
	password varchar(255) not null, 
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table user_group (
	user_id bigint not null, 
	grp_id bigint not null
) engine=InnoDB default charset=utf8;

alter table group_permission add constraint fk_group_permission_permission
foreign key (permission_id) references permission (id);

alter table group_permission add constraint fk_group_permission_grp
foreign key (grp_id) references grp (id);

alter table product add constraint fk_product_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table restaurant add constraint fk_restaurant_kitchen
foreign key (kitchen_id) references kitchen (id);

alter table restaurant add constraint fk_restaurant_city
foreign key (address_city_id) references city (id);

alter table restaurant_payment_type add constraint fk_rest_payment_type_payment_type
foreign key (payment_type_id) references payment_type (id);

alter table restaurant_payment_type add constraint fk_rest_payment_type_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table user_group add constraint fk_user_group_grp
foreign key (grp_id) references grp (id);

alter table user_group add constraint fk_user_group_user
foreign key (user_id) references user (id);
