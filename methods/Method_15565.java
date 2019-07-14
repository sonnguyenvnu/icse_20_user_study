protected Object getValue(@NotNull SQLConfig config,@NotNull ResultSet rs,@NotNull ResultSetMetaData rsmd,final int tablePosition,@NotNull JSONObject table,final int columnIndex,Map<String,JSONObject> childMap) throws Exception {
  Object value=rs.getObject(columnIndex);
  if (value instanceof Timestamp) {
    value=((Timestamp)value).toString();
  }
 else   if (value instanceof String && isJSONType(rsmd,columnIndex)) {
    value=JSON.parse((String)value);
  }
  return value;
}
