CREATE DATABASE IF NOT EXISTS store;

USE store;

CREATE TABLE IF NOT EXISTS customers (
  customer_id VARCHAR(36) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  phone_number VARCHAR(20) NOT NULL,
  address VARCHAR(100) NOT NULL,
  city VARCHAR(50) NOT NULL,
  state VARCHAR(50) NOT NULL,
  zip_code VARCHAR(20) NOT NULL,
  PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS orders (
  order_id VARCHAR(36) NOT NULL,
  customer_id VARCHAR(36) NOT NULL,
  order_date DATE NOT NULL,
  total_price DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (order_id),
  FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE IF NOT EXISTS products (
  product_id VARCHAR(36) NOT NULL,
  product_name VARCHAR(100) NOT NULL,
  description VARCHAR(500) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (product_id)
);

CREATE TABLE IF NOT EXISTS categories (
  category_id VARCHAR(36) NOT NULL,
  category_name VARCHAR(100) NOT NULL,
  PRIMARY KEY (category_id)
);


CREATE TABLE IF NOT EXISTS order_items (
  order_item_id VARCHAR(36) NOT NULL,
  order_id VARCHAR(36) NOT NULL,
  product_id VARCHAR(36) NOT NULL,
  quantity INT NOT NULL,
  price_per_unit DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (order_item_id),
  FOREIGN KEY (order_id) REFERENCES orders(order_id),
  FOREIGN KEY (product_id) REFERENCES products(product_id)
);


CREATE TABLE IF NOT EXISTS product_categories (
  product_id VARCHAR(36) NOT NULL,
  category_id VARCHAR(36) NOT NULL,
  PRIMARY KEY (product_id, category_id),
  FOREIGN KEY (product_id) REFERENCES products(product_id),
  FOREIGN KEY (category_id) REFERENCES categories(category_id)
);
