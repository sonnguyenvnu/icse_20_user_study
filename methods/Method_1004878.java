/** 
 * Determine whether all of the items in the given  {@link Collection} are unique.
 * @param collection the collection to check
 * @param < T >        the type of object contained in the collection
 * @return {@code true} if all the items in the Collection are unique (as determinedby  {@link Object#equals(Object)}, otherwise  {@code false}
 */
public static <T>boolean distinct(final Collection<T> collection){
  final Set<T> set=new HashSet<>();
  for (  final T t : collection) {
    if (!set.add(t)) {
      return false;
    }
  }
  return true;
}
