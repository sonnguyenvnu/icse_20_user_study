/** 
 * resultList.put(position, table);
 * @param config 
 * @param rs
 * @param rsmd
 * @param resultList
 * @param position
 * @param table
 * @return resultList
 */
protected List<JSONObject> onPutTable(@NotNull SQLConfig config,@NotNull ResultSet rs,@NotNull ResultSetMetaData rsmd,@NotNull List<JSONObject> resultList,int position,@NotNull JSONObject table){
  resultList.add(table);
  return resultList;
}
