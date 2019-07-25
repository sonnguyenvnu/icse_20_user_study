/** 
 * Create a set from a variable number of arguments or an array of <code>KType</code>. The elements are copied from the argument to the internal buffer.
 */
@SafeVarargs public static <KType>KTypeScatterSet<KType> from(KType... elements){
  final KTypeScatterSet<KType> set=new KTypeScatterSet<KType>(elements.length);
  set.addAll(elements);
  return set;
}
