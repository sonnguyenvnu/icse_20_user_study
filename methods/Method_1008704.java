/** 
 * Returns a context mapping by its name
 */
public ContextMapping get(String name){
  ContextMapping contextMapping=contextNameMap.get(name);
  if (contextMapping == null) {
    List<String> keys=new ArrayList<>(contextNameMap.keySet());
    Collections.sort(keys);
    throw new IllegalArgumentException("Unknown context name [" + name + "], must be one of " + keys.toString());
  }
  return contextMapping;
}
