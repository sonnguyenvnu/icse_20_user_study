private void dropObjects(String sybaseObjType) throws SQLException {
  List<String> objNames=jdbcTemplate.queryForStringList("select ob.name from sysobjects ob where ob.type=? order by ob.name",sybaseObjType);
  for (  String name : objNames) {
    String sql;
    if ("U".equals(sybaseObjType)) {
      sql="drop table ";
    }
 else     if ("V".equals(sybaseObjType)) {
      sql="drop view ";
    }
 else     if ("P".equals(sybaseObjType)) {
      sql="drop procedure ";
    }
 else     if ("TR".equals(sybaseObjType)) {
      sql="drop trigger ";
    }
 else {
      throw new IllegalArgumentException("Unknown database object type " + sybaseObjType);
    }
    jdbcTemplate.execute(sql + name);
  }
}
