/** 
 * Matches an AST node if its type is a primitive type, or a boxed version of a primitive type.
 */
public static <T extends Tree>Matcher<T> isPrimitiveOrBoxedPrimitiveType(){
  return typePredicateMatcher((type,state) -> state.getTypes().unboxedTypeOrType(type).isPrimitive());
}
