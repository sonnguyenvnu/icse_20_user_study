/** 
 * Creates SELECT criteria for the entity matched by foreign key. Foreign key is created by concatenating foreign table name and column name.
 */
public DbSqlBuilder findForeign(final Class entity,final Object value){
  final String tableRef=createTableRefName(entity);
  final DbEntityDescriptor dedFk=entityManager.lookupType(value.getClass());
  final String tableName=dbOomConfig.getTableNames().convertTableNameToEntityName(dedFk.getTableName());
  final String columnName=dbOomConfig.getColumnNames().convertColumnNameToPropertyName(dedFk.getIdColumnName());
  final String fkColumn=uncapitalize(tableName) + capitalize(columnName);
  final Object idValue=BeanUtil.pojo.getProperty(value,dedFk.getIdPropertyName());
  return sql().$(SELECT).column(tableRef).$(FROM).table(entity,tableRef).$(WHERE).ref(tableRef,fkColumn).$(EQUALS).columnValue(idValue);
}
