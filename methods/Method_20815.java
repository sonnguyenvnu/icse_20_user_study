/** 
 * Combines a list of lists into a new single, flat list.
 */
public static @NonNull <T>List<T> flatten(final @NonNull List<List<T>> xss){
  final List<T> result=new ArrayList<>();
  for (  final List<T> xs : xss) {
    result.addAll(xs);
  }
  return result;
}
