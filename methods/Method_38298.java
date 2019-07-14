/** 
 * Reads property value and updates the DB.
 */
public DbSqlBuilder updateColumn(final Object entity,final String columnRef){
  final Object value=BeanUtil.pojo.getProperty(entity,columnRef);
  return updateColumn(entity,columnRef,value);
}
