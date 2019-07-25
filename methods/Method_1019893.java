public Tool map(int index,ResultSet resultSet,StatementContext statementContext) throws SQLException {
  Tool tool=new Tool();
  tool.setId(resultSet.getLong("id"));
  tool.setName(resultSet.getString("name"));
  tool.setCreatedAt(resultSet.getTimestamp("createdAt"));
  tool.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
  tool.setLanguageId(resultSet.getLong("languageId"));
  tool.setScanTypeId(resultSet.getLong("scanTypeId"));
  tool.setIsEnabled(resultSet.getBoolean("isEnabled"));
  tool.setManifestJson(resultSet.getString("manifestJson"));
  tool.setStatus(resultSet.getString("status"));
  tool.setInstanceCount(resultSet.getLong("instanceCount"));
  return tool;
}
