public static ResultMap getResultMap(String id){
  return getSqlSession().getConfiguration().getResultMap(id);
}
