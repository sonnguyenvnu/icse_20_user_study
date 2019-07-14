/** 
 * Concats the second argument onto the end of the first, but also mutates the first argument.
 */
public static <T>List<T> mutatingConcat(final @NonNull List<T> xs,final @NonNull List<T> ys){
  xs.addAll(ys);
  return xs;
}
