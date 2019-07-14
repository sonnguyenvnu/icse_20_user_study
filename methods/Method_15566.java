/** 
 * ?????JSON??
 * @param rsmd
 * @param position
 * @return
 */
@Override public boolean isJSONType(ResultSetMetaData rsmd,int position){
  try {
    return rsmd.getColumnTypeName(position).toLowerCase().contains("json");
  }
 catch (  SQLException e) {
    e.printStackTrace();
  }
  return false;
}
