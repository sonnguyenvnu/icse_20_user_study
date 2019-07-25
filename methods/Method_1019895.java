public TopVulnerableType map(int i,ResultSet resultSet,StatementContext statementContext) throws SQLException {
  TopVulnerableType topVulnerableType=new TopVulnerableType();
  topVulnerableType.setCount(resultSet.getLong("count"));
  topVulnerableType.setVulnerabilityType(resultSet.getString("vulnerabilityType"));
  topVulnerableType.setSeverity(resultSet.getString("severity"));
  return topVulnerableType;
}
