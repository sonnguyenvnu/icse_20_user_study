public DefaultRole map(int index,ResultSet resultSet,StatementContext statementContext) throws SQLException {
  DefaultRole defaultRole=new DefaultRole();
  defaultRole.setId(resultSet.getLong("id"));
  defaultRole.setRoleId(resultSet.getLong("roleId"));
  return defaultRole;
}
