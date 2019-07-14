@Override protected boolean doEmpty() throws SQLException {
  boolean empty=queryDBObjects(ObjectType.SCALAR_FUNCTION,ObjectType.AGGREGATE,ObjectType.CLR_SCALAR_FUNCTION,ObjectType.CLR_TABLE_VALUED_FUNCTION,ObjectType.TABLE_VALUED_FUNCTION,ObjectType.STORED_PROCEDURE,ObjectType.CLR_STORED_PROCEDURE,ObjectType.USER_TABLE,ObjectType.SYNONYM,ObjectType.SEQUENCE_OBJECT,ObjectType.FOREIGN_KEY,ObjectType.VIEW).isEmpty();
  if (empty) {
    int objectCount=jdbcTemplate.queryForInt("SELECT count(*) FROM " + "( " + "SELECT t.name FROM sys.types t INNER JOIN sys.schemas s ON t.schema_id = s.schema_id" + " WHERE t.is_user_defined = 1 AND s.name = ? " + "Union " + "SELECT name FROM sys.assemblies WHERE is_user_defined=1" + ") R",name);
    empty=objectCount == 0;
  }
  return empty;
}
