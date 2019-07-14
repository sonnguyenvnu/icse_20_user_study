/** 
 * Match types that are exactly equal to any of the given types. 
 */
public static TypePredicate isExactTypeAny(Iterable<String> types){
  return new ExactAny(Iterables.transform(types,GET_TYPE));
}
