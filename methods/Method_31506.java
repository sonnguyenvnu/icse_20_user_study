/** 
 * Query objects with any of the given types and parent (if non-null).
 * @param parent the parent object or {@code null} if unspecified
 * @param types  the object types to be queried
 * @return the found objects
 * @throws SQLException when the retrieval failed
 */
private List<DBObject> queryDBObjectsWithParent(DBObject parent,ObjectType... types) throws SQLException {
  StringBuilder query=new StringBuilder("SELECT obj.object_id, obj.name FROM sys.objects AS obj " + "LEFT JOIN sys.extended_properties AS eps " + "ON obj.object_id = eps.major_id " + "AND eps.class = 1 " + "AND eps.minor_id = 0 " + "AND eps.name='microsoft_database_tools_support' " + "WHERE SCHEMA_NAME(obj.schema_id) = '" + name + "'  " + "AND eps.major_id IS NULL " + "AND obj.is_ms_shipped = 0 " + "AND obj.type IN (");
  boolean first=true;
  for (  ObjectType type : types) {
    if (!first) {
      query.append(", ");
    }
    query.append("'").append(type.code).append("'");
    first=false;
  }
  query.append(")");
  if (parent != null) {
    query.append(" AND obj.parent_object_id = ").append(parent.objectId);
  }
  query.append(" order by create_date desc");
  return jdbcTemplate.query(query.toString(),new RowMapper<DBObject>(){
    @Override public DBObject mapRow(    ResultSet rs) throws SQLException {
      return new DBObject(rs.getLong("object_id"),rs.getString("name"));
    }
  }
);
}
