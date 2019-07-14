/** 
 * Match a Tree based solely on the type produced by  {@link ASTHelpers#getType(Tree)}. <p>If  {@code getType} returns {@code null}, the matcher returns false instead of calling  {@code pred}.
 */
public static <T extends Tree>Matcher<T> typePredicateMatcher(TypePredicate pred){
  return (tree,state) -> {
    Type type=getType(tree);
    return type != null && pred.apply(type,state);
  }
;
}
