/** 
 * Returns a new list with all elements in `xs` equal to `x` replaced by `newx`.
 */
public static @NonNull <T>List<T> allReplaced(final @NonNull List<T> xs,final @NonNull T x,final @NonNull T newx){
  final List<T> ys=new ArrayList<>(xs);
  for (int idx=0; idx < xs.size(); idx++) {
    if (x.equals(xs.get(idx))) {
      ys.set(idx,newx);
    }
  }
  return ys;
}
