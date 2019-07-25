/** 
 * Re-creates the full text index for this database. Calling this method is usually not needed, as the index is kept up-to-date automatically.
 * @param conn the connection
 */
public static void reindex(Connection conn) throws SQLException {
  init(conn);
  removeAllTriggers(conn,TRIGGER_PREFIX);
  removeIndexFiles(conn);
  Statement stat=conn.createStatement();
  ResultSet rs=stat.executeQuery("SELECT * FROM " + SCHEMA + ".INDEXES");
  while (rs.next()) {
    String schema=rs.getString("SCHEMA");
    String table=rs.getString("TABLE");
    createTrigger(conn,schema,table);
    indexExistingRows(conn,schema,table);
  }
}
