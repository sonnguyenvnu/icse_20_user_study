/** 
 * Returns all values in this tree map.
 * @return All values in this tree map.
 */
public List<V> values(){
  return iterableList(join(tree.toList().map(compose(IterableW.wrap(),P2.__2()))));
}
