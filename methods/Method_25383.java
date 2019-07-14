/** 
 * Matches an AST node if its type is either a primitive type or a  {@code void} type. 
 */
public static <T extends Tree>Matcher<T> isPrimitiveOrVoidType(){
  return typePredicateMatcher((type,state) -> type.isPrimitiveOrVoid());
}
