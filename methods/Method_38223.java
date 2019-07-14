/** 
 * Resolves table name from a type. If type is annotated, table name will be read from annotation value. If this value is empty or if type is not annotated, table name will be set to wildcard pattern '*' (to match all tables).
 */
public static String resolveTableName(final Class<?> type,final TableNamingStrategy tableNamingStrategy){
  String tableName=null;
  final DbTable dbTable=type.getAnnotation(DbTable.class);
  if (dbTable != null) {
    tableName=dbTable.value().trim();
  }
  if ((tableName == null) || (tableName.length() == 0)) {
    tableName=tableNamingStrategy.convertEntityNameToTableName(type);
  }
 else {
    if (!tableNamingStrategy.isStrictAnnotationNames()) {
      tableName=tableNamingStrategy.applyToTableName(tableName);
    }
  }
  return quoteIfRequired(tableName,tableNamingStrategy.isAlwaysQuoteNames(),tableNamingStrategy.getQuoteChar());
}
