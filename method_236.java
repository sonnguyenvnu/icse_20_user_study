public List<Publishment> _XXXXX_(){
  List<Publishment> result=new LinkedList<>();
  Connection connection=null;
  PreparedStatement statement=null;
  ResultSet rs=null;
  try {
    connection=dataSource.getConnection();
    statement=connection.prepareStatement(QUERY_PUBLISHMENT_STATEMENT);
    Map<String,List<String>> publishPolicyMap=new HashedMap();
    rs=statement.executeQuery();
    while (rs.next()) {
      String publishment=rs.getString(1);
      String policyId=rs.getString(2);
      List<String> policyIds=publishPolicyMap.get(publishment);
      if (policyIds == null) {
        policyIds=new ArrayList<>();
        publishPolicyMap.put(publishment,policyIds);
      }
      if (policyId != null) {
        policyIds.add(policyId);
      }
    }
    for (    Map.Entry<String,List<String>> entry : publishPolicyMap.entrySet()) {
      Publishment publishment=mapper.readValue(entry.getKey(),Publishment.class);
      publishment.setPolicyIds(entry.getValue());
      result.add(publishment);
    }
  }
 catch (  Exception ex) {
    LOG.error(ex.getMessage(),ex);
  }
 finally {
    closeResource(rs,statement,connection);
  }
  return result;
}