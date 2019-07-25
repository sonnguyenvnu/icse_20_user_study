/** 
 * Retrieve all the mapped keys. <p> Note that unlike with  {@link Map#keySet()}, the resulting key set is not "live"; in fact that is, alterations to the resulting key set have no effect on the backing  {@link HMap}.
 * @return a {@link Set} of all the mapped keys
 */
public Set<TypeSafeKey<?,?>> keys(){
  return new HashSet<>(table.keySet());
}
