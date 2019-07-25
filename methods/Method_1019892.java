public SeverityCountChart map(int i,ResultSet resultSet,StatementContext statementContext) throws SQLException {
  SeverityCountChart severityCountDashboard=new SeverityCountChart();
  severityCountDashboard.setCount(resultSet.getLong("count"));
  severityCountDashboard.setSeverity(resultSet.getString("severity"));
  return severityCountDashboard;
}
