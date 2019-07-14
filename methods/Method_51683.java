/** 
 * Creates and returns a map of short type names (without the package prefixes) keyed by the classes themselves.
 * @return Map
 */
public Map<Class<?>,String> asInverseWithShortName(){
  Map<Class<?>,String> inverseMap=new HashMap<>(typesByName.size() / 2);
  Iterator<Map.Entry<String,Class<?>>> iter=typesByName.entrySet().iterator();
  while (iter.hasNext()) {
    Map.Entry<String,Class<?>> entry=iter.next();
    storeShortest(inverseMap,entry.getValue(),entry.getKey());
  }
  return inverseMap;
}
