/** 
 * Match types that are a sub-type of one of the given types. 
 */
public static TypePredicate isDescendantOfAny(Iterable<String> types){
  return new DescendantOfAny(Iterables.transform(types,GET_TYPE));
}
