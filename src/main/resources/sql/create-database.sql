create database if not exists e_commerce;
use e_commerce;

create table if not exists product_brand (
	id int not null auto_increment,
	uuid varchar(36) not null,
	name varchar(60) not null,
	constraint pk_product_brand primary key(id),
	constraint uk_product_brand unique(name)
);

create table if not exists product_category (
	id int not null auto_increment,
	uuid varchar(36) not null,
	name varchar(60) not null,
	constraint pk_product_category primary key(id),
	constraint uk_product_category unique(name)
);

create table if not exists order_type (
	id int not null auto_increment,
	uuid varchar(36) not null,
	name varchar(30) not null,
	constraint pk_order_type primary key(id),
	constraint uk_order_type unique(name)
);

create table if not exists address (
	id bigint not null auto_increment,
	uuid varchar(36) not null,
	label varchar(30) not null,
	active tinyint(1) not null default '0',
	isprimary tinyint(1) not null,
	zipcode varchar(8) not null,
	streetname varchar(30) not null,
	address_number varchar(15) default 'Sem número',
	neighborhood varchar(50) not null,
	point_of_reference varchar(50),
	constraint pk_address primary key(id),
    constraint uk_address unique (zipcode, streetname, address_number, neighborhood)
);

create table if not exists `user` (
	id bigint not null auto_increment,
	uuid varchar(36) not null,
	name varchar(60) not null,
	email varchar(60) not null,
	created_at datetime not null,
	updated_at datetime,
	nickname varchar(20),
	constraint pk_user primary key(id),
	constraint uk_user unique(name, email)
);

create table if not exists school_focus (
	id int not null auto_increment,
	uuid varchar(36) not null,
	level_name varchar(20) not null,
	school_id int, -- foreign key to school (nullable, so can be created first)
	description varchar(30),
	constraint pk_school_focus primary key(id),
    constraint uk_school_focus unique (level_name)
);

create table if not exists school (
    id int not null auto_increment,
    uuid varchar(36) not null,
    name varchar(60) not null,
    active tinyint(1) not null,
    address varchar(60),
    school_focus_id int not null, -- foreign key to school_focus
    constraint pk_school primary key(id),
    constraint uk_school unique(name),
    constraint fk_school_school_focus foreign key(school_focus_id) references school_focus(id)
);

create table if not exists product (
	id bigint not null auto_increment,
	uuid varchar(36) not null,
	name varchar(60) not null,
	product_brand_id int not null, -- foreign key to product brand
	description varchar(240) not null,
	theme varchar(30),
	color varchar(20),
	dimensions varchar(20),
	weight decimal(5,2) not null,
	suppler_price decimal(6,2) not null,
	final_price decimal(6,2) not null,
	special_price tinyint(1) not null default '0',
	special_price_amount decimal(6,2),
	product_category_id int not null, -- foreign key to product category
	constraint pk_product primary key(id),
	constraint uk_product unique(name, product_brand_id, color, dimensions),
	constraint fk_product_product_brand foreign key(product_brand_id) references product_brand(id),
	constraint fk_product_product_category foreign key(product_category_id) references product_category(id)
);

create table if not exists grade (
    id int not null auto_increment,
    uuid varchar(36) not null,
    name varchar(30) not null,
    school_id int,
    constraint pk_grade primary key(id),
    constraint uk_grade unique(name),
    constraint fk_grade_school foreign key(school_id) references school(id)
);

create table if not exists cart (
	id bigint not null auto_increment,
	uuid varchar(36) not null,
	user_id bigint not null, -- foreign key to user
	created_at datetime not null,
    updated_at datetime,
	constraint pk_cart primary key(id),
	constraint uk_cart unique(user_id),
	constraint fk_cart_user foreign key(user_id) references `user`(id)
);

create table if not exists product_order (
	id bigint not null auto_increment,
	uuid varchar(36) not null,
	order_type_id int not null, -- foreign key to order type
	user_id bigint not null, -- foreign key to user
	address_id bigint not null, -- foreign key to address
	order_amount decimal(10,2) not null,
	shipping_amount decimal(5,2) not null,
	eta date not null, -- estimated time (date) of arrival
	created_at datetime not null,
	updated_at datetime,
	constraint pk_product_order primary key(id),
	constraint uk_product_order unique(user_id, address_id, order_amount, shipping_amount),
	constraint fk_product_order_order_type foreign key(order_type_id) references order_type(id),
	constraint fk_product_order_user foreign key(user_id) references `user`(id),
	constraint fk_product_order_address foreign key(address_id) references address(id)
);

create table if not exists inventory (
    id bigint not null auto_increment,
    uuid varchar(36) not null,
    product_id bigint not null,
    quantity_available int not null default 0,
    quantity_reserved int not null default 0,
    status varchar(30) not null default 'AVAILABLE',
	created_at datetime not null,
    last_updated_at datetime,
    constraint pk_inventory primary key(id),
    constraint uk_inventory_product_location unique(product_id, status),
	constraint fk_inventory_product foreign key(product_id) references product(id)
);

create table if not exists cart_item (
    id bigint not null auto_increment,
    uuid varchar(36) not null,
    cart_id bigint not null, -- foreign key to cart
    product_id bigint not null, -- foreign key to product
    quantity int not null,
    price_at_time_of_addition decimal(6,2) not null,
    constraint pk_cart_item primary key(id),
    constraint uk_cart_item unique(cart_id, product_id),
	constraint fk_cart_item_cart foreign key(cart_id) references cart(id),
	constraint fk_cart_item_product foreign key(product_id) references product(id)
);

create table if not exists order_items (
	id bigint not null auto_increment,
	uuid varchar(36) not null,
	product_id bigint not null, -- foreign key to product
	product_order_id bigint not null, -- foreign key product_order
	quantity int not null,
	price_at_time_of_order decimal(6,2) not null,
	constraint pk_order_items primary key(id),
	constraint uk_order_items unique(product_id, product_order_id),
	constraint fk_order_items_product foreign key(product_id) references product(id),
	constraint fk_order_items_product_order foreign key(product_order_id) references product_order(id)
);

create table if not exists product_list (
    id bigint not null auto_increment,
    uuid varchar(36) not null,
    school_id int not null,
    grade_id int not null,
    name varchar(255) not null,
    description varchar(500),
    price decimal(10,2),
    academic_year varchar(10), -- Ex: '2025/2026'

    constraint pk_product_list primary key(id),
    constraint uk_product_list unique(school_id, grade_id, name, academic_year),
    constraint fk_product_list_school foreign key(school_id) references school(id),
    constraint fk_product_list_grade foreign key(grade_id) references grade(id)
);

create table if not exists product_list_item (
    id bigint not null auto_increment,
    uuid varchar(36) not null,
    product_list_id bigint not null,
    product_id bigint not null,
    quantity int not null,

    constraint pk_product_list_item primary key(id),
    constraint uk_product_list_item unique(product_list_id, product_id),
    constraint fk_product_list_item_package foreign key(product_list_id) references product_list(id),
    constraint fk_product_list_item_product foreign key(product_id) references product(id)
);

commit;