private void createTaskRunningTable(final Connection conn) throws SQLException {
  String dbSchema="CREATE TABLE `" + TABLE_TASK_RUNNING_STATISTICS + "` (" + "`id` BIGINT NOT NULL AUTO_INCREMENT, " + "`running_count` INT(11)," + "`statistics_time` TIMESTAMP NOT NULL," + "`creation_time` TIMESTAMP NOT NULL," + "PRIMARY KEY (`id`));";
  try (PreparedStatement preparedStatement=conn.prepareStatement(dbSchema)){
    preparedStatement.execute();
  }
 }
