CREATE TABLE  `user_todo_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `item_name` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `created_date` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB;