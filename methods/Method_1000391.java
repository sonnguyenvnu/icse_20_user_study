public void set(PreparedStatement stat,Object obj,int index) throws SQLException {
  if (null == obj) {
    stat.setNull(index,Types.CLOB);
  }
 else {
    Clob clob=(Clob)obj;
    Jdbcs.setCharacterStream(index,clob.getCharacterStream(),stat);
  }
}
