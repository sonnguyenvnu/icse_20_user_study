/** 
 * @param m      another map
 * @param equals a predicate which checks value equalities
 * @return true, if the maps are equivalent
 */
default boolean equals(IMap<K,V> m,BiPredicate<V,V> equals){
  return Maps.equals(this,m,equals);
}
