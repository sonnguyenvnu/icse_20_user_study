/** 
 * Returns true if this node has a descendant of any type among the provided types.
 * @param types Types to test
 * @deprecated Use {@link #hasDescendantOfAnyType(Class[])}
 */
@Deprecated public final boolean hasDecendantOfAnyType(final Class<?>... types){
  return hasDescendantOfAnyType(types);
}
