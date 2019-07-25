/** 
 * Puts a typeMap into store
 * @throws IllegalArgumentException if {@link TypePair} of typeMap is already exists in the store
 */
public void put(TypeMap<?,?> typeMap){
  TypePair<?,?> typePair=TypePair.of(typeMap.getSourceType(),typeMap.getDestinationType(),typeMap.getName());
synchronized (lock) {
    if (typeMaps.containsKey(typePair))     throw new IllegalArgumentException("TypeMap exists in the store: " + typePair.toString());
    typeMaps.put(typePair,typeMap);
  }
}
