/** 
 * Sets the designated parameter to the given Java <code>boolean</code> value. This translates the boolean to 1/0 for true/false.
 */
@Override protected void setBoolean(PreparedStatement ps,int index,boolean val) throws SQLException {
  ps.setString(index,((val) ? "1" : "0"));
}
