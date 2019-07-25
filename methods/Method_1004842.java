public static BinlogPosition capture(Connection c,boolean gtidMode) throws SQLException {
  ResultSet rs;
  rs=c.createStatement().executeQuery("SHOW MASTER STATUS");
  rs.next();
  long l=rs.getInt(POSITION_COLUMN);
  String file=rs.getString(FILE_COLUMN);
  String gtidSetStr=null;
  if (gtidMode) {
    gtidSetStr=rs.getString(GTID_COLUMN);
  }
  return new BinlogPosition(gtidSetStr,null,l,file);
}
