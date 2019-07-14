@Override public List<Approval> getApprovals(String userName,String clientId){
  return jdbcTemplate.query(findApprovalStatement,rowMapper,userName,clientId);
}
