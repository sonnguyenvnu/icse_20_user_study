/** 
 * Converts the given matcher to one that can be applied to any tree but is only executed when run on a tree of  {@code type} and returns {@code false} for all other tree types.
 */
public static <S extends T,T extends Tree>Matcher<T> toType(Class<S> type,Matcher<? super S> matcher){
  return (tree,state) -> type.isInstance(tree) && matcher.matches(type.cast(tree),state);
}
