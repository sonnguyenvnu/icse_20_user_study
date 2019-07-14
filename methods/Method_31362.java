private void resetUserVariables() throws SQLException {
  if (canResetUserVariables) {
    List<String> userVariables=jdbcTemplate.queryForStringList(userVariablesQuery);
    if (!userVariables.isEmpty()) {
      boolean first=true;
      StringBuilder setStatement=new StringBuilder("SET ");
      for (      String userVariable : userVariables) {
        if (first) {
          first=false;
        }
 else {
          setStatement.append(",");
        }
        setStatement.append("@").append(userVariable).append("=NULL");
      }
      jdbcTemplate.executeStatement(setStatement.toString());
    }
  }
}
