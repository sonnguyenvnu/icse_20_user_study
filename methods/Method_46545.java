public List<AspectLog> findAll(){
  String sql="SELECT * FROM TXLCN_LOG";
  return h2DbHelper.query(sql,resultSet -> {
    List<AspectLog> list=new ArrayList<>();
    while (resultSet.next()) {
      list.add(fill(resultSet));
    }
    return list;
  }
);
}
