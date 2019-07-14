/** 
 * Returns the value of the  {@code @Generated} annotation on the top-level class, if present. 
 */
public static ImmutableSet<String> getGeneratedBy(VisitorState state){
  ClassTree outerClass=null;
  for (  Tree enclosing : state.getPath()) {
    if (enclosing instanceof ClassTree) {
      outerClass=(ClassTree)enclosing;
    }
  }
  return getGeneratedBy(getSymbol(outerClass),state);
}
