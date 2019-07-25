@SuppressWarnings("unchecked") public <E,J extends JcrObject>List<E> select(Session session,String typeName,Class<E> type,Class<J> jcrClass){
  HashMap<String,Object> params=new HashMap<>();
  StringBuilder queryStr=new StringBuilder("select e.* from [" + typeName + "] as e ");
  applyFilter(queryStr,params);
  applyLimit(queryStr);
  Map<String,String> bindParams=new HashMap<>();
  for (  Map.Entry<String,Object> entry : params.entrySet()) {
    bindParams.put(entry.getKey(),entry.getValue() != null ? entry.getValue().toString() : null);
  }
  QueryResult result=null;
  try {
    result=JcrQueryUtil.query(session,queryStr.toString(),bindParams);
    return (List<E>)JcrQueryUtil.queryResultToList(result,jcrClass);
  }
 catch (  RepositoryException e) {
    throw new MetadataRepositoryException("Unable to execute Criteria Query for " + type + ".  Query is: " + queryStr.toString(),e);
  }
}
