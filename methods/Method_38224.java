/** 
 * Resolves schema name from a type. Uses default schema name if not specified.
 */
public static String resolveSchemaName(final Class<?> type,final String defaultSchemaName){
  String schemaName=null;
  final DbTable dbTable=type.getAnnotation(DbTable.class);
  if (dbTable != null) {
    schemaName=dbTable.schema().trim();
  }
  if ((schemaName == null) || (schemaName.length() == 0)) {
    schemaName=defaultSchemaName;
  }
  return schemaName;
}
