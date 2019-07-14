/** 
 * Returns <code>true</code> if class is annotated with <code>DbTable</code> annotation.
 */
public static boolean resolveIsAnnotated(final Class<?> type){
  DbTable dbTable=type.getAnnotation(DbTable.class);
  return dbTable != null;
}
