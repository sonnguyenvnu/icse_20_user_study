public ScanTool map(int index,ResultSet resultSet,StatementContext statementContext) throws SQLException {
  ScanTool scanTool=new ScanTool();
  scanTool.setToolId(resultSet.getLong("toolId"));
  scanTool.setScanId(resultSet.getLong("scanId"));
  scanTool.setStatus(resultSet.getString("status"));
  scanTool.setToolInstanceId(resultSet.getLong("toolInstanceId"));
  return scanTool;
}
