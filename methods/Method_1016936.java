public void cleanup() throws Exception {
  String sqlState="";
  connection.close();
  try {
    DriverManager.getConnection("jdbc:derby:;shutdown=true");
  }
 catch (  SQLException se) {
    sqlState=se.getSQLState();
  }
  if (sqlState.equals("XJ015")) {
    System.err.println("shutdown successful: " + sqlState);
  }
}
