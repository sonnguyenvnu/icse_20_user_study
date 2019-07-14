/** 
 * Retrieves all the types in this schema.
 * @return All types in the schema.
 */
protected final Type[] allTypes(){
  ResultSet resultSet=null;
  try {
    resultSet=database.jdbcMetaData.getUDTs(null,name,null,null);
    List<Type> types=new ArrayList<>();
    while (resultSet.next()) {
      types.add(getType(resultSet.getString("TYPE_NAME")));
    }
    return types.toArray(new Type[0]);
  }
 catch (  SQLException e) {
    throw new FlywaySqlException("Unable to retrieve all types in schema " + this,e);
  }
 finally {
    JdbcUtils.closeResultSet(resultSet);
  }
}
