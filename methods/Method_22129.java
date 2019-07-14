private void createTaskResultTable(final Connection conn,final StatisticInterval statisticInterval) throws SQLException {
  String dbSchema="CREATE TABLE `" + TABLE_TASK_RESULT_STATISTICS + "_" + statisticInterval + "` (" + "`id` BIGINT NOT NULL AUTO_INCREMENT, " + "`success_count` INT(11)," + "`failed_count` INT(11)," + "`statistics_time` TIMESTAMP NOT NULL," + "`creation_time` TIMESTAMP NOT NULL," + "PRIMARY KEY (`id`));";
  try (PreparedStatement preparedStatement=conn.prepareStatement(dbSchema)){
    preparedStatement.execute();
  }
 }
