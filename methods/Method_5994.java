/** 
 * Tests whether an  {@code items} array contains an object equal to {@code item}, according to {@link Object#equals(Object)}. <p> If  {@code item} is null then true is returned if and only if {@code items} contains null.
 * @param items The array of items to search.
 * @param item The item to search for.
 * @return True if the array contains an object equal to the item being searched for.
 */
public static boolean contains(Object[] items,Object item){
  for (  Object arrayItem : items) {
    if (areEqual(arrayItem,item)) {
      return true;
    }
  }
  return false;
}
