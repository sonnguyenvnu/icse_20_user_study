/** 
 * Matches an AST node if its type is a  {@code void} type. 
 */
public static <T extends Tree>Matcher<T> isVoidType(){
  return typePredicateMatcher((type,state) -> state.getTypes().isSameType(type,state.getSymtab().voidType));
}
