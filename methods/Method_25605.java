/** 
 * Find the first enclosing tree node of one of the given types.
 * @return the node, or {@code null} if there is no match
 */
@Nullable @SuppressWarnings("unchecked") @SafeVarargs public final <T extends Tree>T findEnclosing(Class<? extends T>... classes){
  TreePath pathToEnclosing=findPathToEnclosing(classes);
  return (pathToEnclosing == null) ? null : (T)pathToEnclosing.getLeaf();
}
