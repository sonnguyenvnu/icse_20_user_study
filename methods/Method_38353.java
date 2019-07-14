/** 
 * Lookups for entity name and throws an exception if entity type is invalid.
 */
protected DbEntityDescriptor lookupType(final Class entity){
  DbEntityDescriptor ded=entityManager.lookupType(entity);
  if (ded == null) {
    throw new DbSqlBuilderException("Invalid or not-persistent entity type: " + entity.getName());
  }
  return ded;
}
