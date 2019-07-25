public static void delete(Connection connection,long schema_id) throws SQLException {
  connection.createStatement().execute("update `schemas` set deleted = 1 where id = " + schema_id);
}
