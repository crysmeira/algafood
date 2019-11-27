create table order_table (
	id bigint not null auto_increment,
	subtotal decimal(10,2) not null,
	delivery_fee decimal(10,2) not null,
	total_value decimal(10,2) not null,
	
	order_status varchar(10) not null,
	creation_date datetime not null,
	confirmation_date datetime,
	cancelation_date datetime,
	delivery_date datetime,
	
	restaurant_id bigint not null,
	payment_type_id bigint not null,
	user_client_id bigint not null,
	
	address_number varchar(20) not null, 
	address_street varchar(100) not null, 
	address_zip_code varchar(5) not null,
	address_city_id bigint not null, 
	
	primary key (id),
	
	constraint fk_order_restaurant foreign key (restaurant_id) references restaurant (id),
	constraint fk_order_user_client foreign key (user_client_id) references user (id),
	constraint fk_order_payment_type foreign key (payment_type_id) references payment_type (id)
) engine=InnoDB default charset=utf8;

create table order_item (
	id bigint not null auto_increment,
	quantity smallint(6) not null,
	unit_price decimal(10,2) not null,
	total_price decimal(10,2) not null,
	observation varchar(255),
	product_id bigint not null,
	order_id bigint not null,
	
	primary key (id),
	
	unique key uk_order_item_product (order_id, product_id),

	constraint fk_order_item_order foreign key (order_id) references order_table (id),
	constraint fk_order_item_product foreign key (product_id) references product (id)
) engine=InnoDB default charset=utf8;



