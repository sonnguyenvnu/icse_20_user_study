/** 
 * Caches returned entities. Replaces new instances with existing ones.
 */
protected void cacheResultSetEntities(final Object[] result){
  if (entitiesCache == null) {
    entitiesCache=new HashMap<>();
  }
  for (int i=0; i < result.length; i++) {
    Object object=result[i];
    if (object == null) {
      continue;
    }
    DbEntityDescriptor ded=cachedDbEntityDescriptors[i];
    if (ded == null) {
      continue;
    }
    Object key;
    if (ded.hasIdColumn()) {
      key=ded.getKeyValue(object);
    }
 else {
      key=object;
    }
    Object cachedObject=entitiesCache.get(key);
    if (cachedObject == null) {
      entitiesCache.put(key,object);
    }
 else {
      result[i]=cachedObject;
    }
  }
}
