CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Primary Key ID',
                      user_name VARCHAR(50) NOT NULL COMMENT 'Username',
                      mobile VARCHAR(15) NOT NULL COMMENT 'Mobile Number',
                      email VARCHAR(100) NOT NULL COMMENT 'Email Address',
                      create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Date',
                      update_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update Date',
                      del_flag VARCHAR(1) NOT NULL DEFAULT '0' COMMENT 'Deletion Flag (0 for not deleted, 1 for deleted)'
) COMMENT 'User Table';
