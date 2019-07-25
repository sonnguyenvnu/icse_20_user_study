/** 
 * @return an {@code IList} containing all the elements in the set
 */
default IList<V> elements(){
  return Lists.from(size(),this::nth,this::iterator);
}
