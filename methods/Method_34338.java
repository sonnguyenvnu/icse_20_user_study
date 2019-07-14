private Set<String> extractScope(Map<String,?> map){
  Set<String> scope=Collections.emptySet();
  if (map.containsKey(scopeAttribute)) {
    Object scopeObj=map.get(scopeAttribute);
    if (String.class.isInstance(scopeObj)) {
      scope=new LinkedHashSet<String>(Arrays.asList(String.class.cast(scopeObj).split(" ")));
    }
 else     if (Collection.class.isAssignableFrom(scopeObj.getClass())) {
      @SuppressWarnings("unchecked") Collection<String> scopeColl=(Collection<String>)scopeObj;
      scope=new LinkedHashSet<String>(scopeColl);
    }
  }
  return scope;
}
