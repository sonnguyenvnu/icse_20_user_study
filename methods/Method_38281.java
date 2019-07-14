/** 
 * Lookups for entity name and throws an exception if entity type is invalid.
 */
protected DbEntityDescriptor lookupType(final Class entity){
  final DbEntityDescriptor ded=dbEntityManager.lookupType(entity);
  if (ded == null) {
    throw new DbSqlBuilderException("Invalid or not-persistent entity: " + entity.getName());
  }
  return ded;
}
