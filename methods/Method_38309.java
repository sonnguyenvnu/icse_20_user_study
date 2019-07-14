/** 
 * Creates UPDATE that increases/decreases column by some delta value.
 */
public DbSqlBuilder increaseColumn(final Class entity,final Object id,final String columnRef,final Number delta,final boolean increase){
  final String tableRef=createTableRefName(entity);
  return sql().$(UPDATE).table(entity,null,tableRef).$(SET).ref(null,columnRef).$(EQUALS).ref(null,columnRef).$(increase ? StringPool.PLUS : StringPool.DASH).columnValue(delta).$(WHERE).refId(tableRef).$(EQUALS).columnValue(id);
}
