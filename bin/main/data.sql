DROP TABLE IF EXISTS user;
 
CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  surname VARCHAR(250) NOT NULL,
  location VARCHAR(250) DEFAULT NULL
);
 
INSERT INTO user (first_name, surname, location) VALUES
  ('Super Star', 'Rajnikanth', 'India'),
  ('James', 'Bond', 'UK'),
  ('Maximus', 'Decimus Meridius', 'Spain'),
  ('Captain', 'America', 'USA');
