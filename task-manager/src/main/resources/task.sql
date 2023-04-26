CREATE TABLE Task (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  due_date DATETIME,
  is_done BOOLEAN DEFAULT false
);

