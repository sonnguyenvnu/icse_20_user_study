/** 
 * Resolves  {@link jodd.db.oom.DbEntityDescriptor} for all given types,so not to repeat every time.
 */
protected DbEntityDescriptor[] resolveDbEntityDescriptors(final Class[] types){
  if (cachedDbEntityDescriptors == null) {
    DbEntityDescriptor[] descs=new DbEntityDescriptor[types.length];
    for (int i=0; i < types.length; i++) {
      Class type=types[i];
      if (type != null) {
        descs[i]=dbEntityManager.lookupType(type);
      }
    }
    cachedDbEntityDescriptors=descs;
  }
  return cachedDbEntityDescriptors;
}
