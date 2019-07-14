/** 
 * Resolved mapped type names for each type.
 */
protected String[][] resolveMappedTypesTableNames(final Class[] types){
  if (cachedMappedNames == null) {
    String[][] names=new String[types.length][];
    for (int i=0; i < types.length; i++) {
      Class type=types[i];
      if (type != null) {
        DbEntityDescriptor ded=cachedDbEntityDescriptors[i];
        if (ded != null) {
          Class[] mappedTypes=ded.getMappedTypes();
          if (mappedTypes != null) {
            names[i]=createTypesTableNames(mappedTypes);
          }
        }
      }
    }
    cachedMappedNames=names;
  }
  return cachedMappedNames;
}
