protected boolean contains(String cluster_name,Address addr){
  final String addressAsString=addressAsString(addr);
  try (Connection conn=getConnection()){
    try (PreparedStatement ps=conn.prepareStatement(contains_sql)){
      ps.setString(1,cluster_name);
      ps.setString(2,addressAsString);
      try (ResultSet resultSet=ps.executeQuery()){
        if (!resultSet.next())         return false;
        int count=resultSet.getInt("RECORDCOUNT");
        return count > 0;
      }
     }
   }
 catch (  SQLException e) {
    log.error(Util.getMessage("ErrorReadingTable"),e);
  }
  return false;
}
