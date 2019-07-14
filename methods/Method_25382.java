/** 
 * Matches an AST node if its type is an array type. 
 */
public static <T extends Tree>Matcher<T> isArrayType(){
  return typePredicateMatcher((type,state) -> state.getTypes().isArray(type));
}
