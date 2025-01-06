-- Table `time_slot`
CREATE TABLE time_slot (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    delivery_mode VARCHAR(255) NOT NULL,
    reserved BOOLEAN NOT NULL DEFAULT FALSE,
    time_slot VARCHAR(255),
    date DATE NOT NULL
);

-- Table `order`
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    delivery_mode VARCHAR(255) NOT NULL,
    created_at DATE NOT NULL
);
