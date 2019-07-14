/** 
 * Resolves mapped types from  {@link jodd.db.oom.meta.DbMapTo} annotation.
 */
public static Class[] resolveMappedTypes(final Class type){
  DbMapTo dbMapTo=(DbMapTo)type.getAnnotation(DbMapTo.class);
  if (dbMapTo == null) {
    return null;
  }
  return dbMapTo.value();
}
