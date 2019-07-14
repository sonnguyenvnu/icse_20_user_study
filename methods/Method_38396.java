/** 
 * Detects <code>null</code> before storing the value into the database.
 */
@Override public void storeValue(final PreparedStatement st,final int index,final Object value,final int dbSqlType) throws SQLException {
  if (value == null) {
    st.setNull(index,dbSqlType);
    return;
  }
  super.storeValue(st,index,value,dbSqlType);
}
