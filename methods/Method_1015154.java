/** 
 * @return a list containing all the entries within the map
 */
default IList<IEntry<K,V>> entries(){
  return Lists.from(size(),this::nth,this::iterator);
}
