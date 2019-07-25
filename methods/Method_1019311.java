/** 
 * Create a set from a variable number of arguments or an array of <code>KType</code>. The elements are copied from the argument to the internal buffer.
 */
@SafeVarargs public static <KType>KTypeHashSet<KType> from(KType... elements){
  final KTypeHashSet<KType> set=new KTypeHashSet<KType>(elements.length);
  set.addAll(elements);
  return set;
}
