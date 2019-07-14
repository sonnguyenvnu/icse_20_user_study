/** 
 * Match sub-types of the given type. 
 */
public static TypePredicate isDescendantOf(String type){
  return isDescendantOf(Suppliers.typeFromString(type));
}
