/** 
 * Add elements from the source to the target as long as they don't already exist there. Return the number of items actually added.
 * @param source
 * @param target
 * @return int
 */
public static <T>int addWithoutDuplicates(Collection<T> source,Collection<T> target){
  int added=0;
  for (  T item : source) {
    if (target.contains(item)) {
      continue;
    }
    target.add(item);
    added++;
  }
  return added;
}
