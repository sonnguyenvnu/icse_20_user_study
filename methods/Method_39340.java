/** 
 * Delegates to  {@link jodd.petite.scope.Scope#lookup(String)}. 
 */
protected Object scopeLookup(){
  if (scope == null) {
    throw new PetiteException("Scope not defined");
  }
  return scope.lookup(name);
}
