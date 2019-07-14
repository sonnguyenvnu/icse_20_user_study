/** 
 * Match sub-types of the given type. 
 */
public static TypePredicate isDescendantOf(Supplier<Type> type){
  return new DescendantOf(type);
}
