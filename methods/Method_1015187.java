/** 
 * @param elements a collection of elements
 * @return a {@code LinearSet} containing the elements in the collection
 */
public static <V>LinearSet<V> from(java.util.Collection<V> elements){
  return elements.stream().collect(Sets.linearCollector(elements.size()));
}
