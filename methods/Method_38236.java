/** 
 * Defines entity-aware mode for entities tracking in result collection.
 */
public DbOomQuery entityAwareMode(final boolean entityAware){
  if (entityAware) {
    this.cacheEntities=true;
  }
  this.entityAwareMode=entityAware;
  return this;
}
