/** 
 * Create a new deque by pushing a variable number of arguments to the end of it.
 */
@SafeVarargs public static <KType>KTypeArrayDeque<KType> from(KType... elements){
  final KTypeArrayDeque<KType> coll=new KTypeArrayDeque<KType>(elements.length);
  coll.addLast(elements);
  return coll;
}
