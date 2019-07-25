public TopVulnerableRepo map(int i,ResultSet resultSet,StatementContext statementContext) throws SQLException {
  TopVulnerableRepo topVulnerabilityRepo=new TopVulnerableRepo();
  topVulnerabilityRepo.setCount(resultSet.getLong("count"));
  topVulnerabilityRepo.setRepoId(resultSet.getLong("repoId"));
  return topVulnerabilityRepo;
}
