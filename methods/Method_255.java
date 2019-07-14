/** 
 * Returns the first duplicate element inside an array, null if there are no duplicates. 
 */
private static @Nullable Integer findDuplicate(int[] array){
  Set<Integer> seenElements=new LinkedHashSet<>();
  for (  int element : array) {
    if (!seenElements.add(element)) {
      return element;
    }
  }
  return null;
}
