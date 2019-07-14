/** 
 * Performs search for the scope class and returns it's instance.
 */
protected MadvocScope getOrInitScope(final Class<? extends MadvocScope> madvocScopeType){
  for (  final MadvocScope s : allScopes) {
    if (s.getClass().equals(madvocScopeType)) {
      return s;
    }
  }
  final MadvocScope newScope;
  try {
    newScope=madpc.createBean(madvocScopeType);
  }
 catch (  Exception ex) {
    throw new MadvocException("Unable to create scope: " + madvocScopeType,ex);
  }
  allScopes.add(newScope);
  return newScope;
}
