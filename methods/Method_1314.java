/** 
 * Creates a <i>mutable</i>  {@code HashSet} instance containing the givenelements in unspecified order.
 * @param elements the elements that the set should contain
 * @return a new {@code HashSet} containing those elements (minus duplicates)
 */
public static <E>HashSet<E> newHashSet(E... elements){
  HashSet<E> set=newHashSetWithCapacity(elements.length);
  Collections.addAll(set,elements);
  return set;
}
