/** 
 * Returns true if `y` is equal to any of the values in `xs`, where equality is determined by a given function.
 */
public static <T>boolean contains(final @NonNull List<T> xs,final @NonNull T y,final @NonNull Func2<T,T,Boolean> equality){
  return ListUtils.indexOf(xs,y,equality) != -1;
}
