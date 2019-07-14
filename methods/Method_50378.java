private String buildDelSql(final String tableName,final String id){
  return "DELETE FROM " + tableName + " WHERE trans_id=" + id;
}
