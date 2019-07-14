/** 
 * Returns the list of additions in this mutation
 * @return
 */
public List<E> getAdditions(){
  if (additions == null)   return ImmutableList.of();
  return additions;
}
