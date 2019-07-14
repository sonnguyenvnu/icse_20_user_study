private static String buildOracle(final String tableName){
  return "CREATE TABLE IF NOT EXISTS `" + tableName + "` (" + "  `trans_id` varchar(64) NOT NULL," + "  `target_class` varchar(256) ," + "  `target_method` varchar(128) ," + "  `confirm_method` varchar(128) ," + "  `cancel_method` varchar(128) ," + "  `retried_count` int(3) NOT NULL," + "  `create_time` date NOT NULL," + "  `last_time` date NOT NULL," + "  `version` int(6) NOT NULL," + "  `status` int(2) NOT NULL," + "  `invocation` BLOB ," + "  `role` int(2) NOT NULL," + "  `pattern` int(2)," + "  PRIMARY KEY (`trans_id`))";
}
