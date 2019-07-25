private synchronized void init() throws SQLException {
  if (isClosed)   return;
  Statement stmt=conn.createStatement();
  ResultSet rs=stmt.executeQuery(TABLE_NAMES_SELECT_STMT);
  ArrayList<String> missingTables=new ArrayList<>(TABLES.keySet());
  while (rs.next()) {
    String tableName=rs.getString("name");
    missingTables.remove(tableName);
  }
  for (  String missingTable : missingTables) {
    try {
      Statement createStmt=conn.createStatement();
      createStmt.executeUpdate(TABLES.get(missingTable));
      createStmt.close();
    }
 catch (    Exception e) {
      LOG.severe(e.getClass().getName() + ": " + e.getMessage());
    }
  }
}
