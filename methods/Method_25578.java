/** 
 * Walks up the given  {@code path} and returns true iff the first node matching {@code predicate1}occurs lower in the AST than the first node node matching  {@code predicate2}. Returns false if no node matches  {@code predicate1} or if no node matches {@code predicate2}.
 * @param predicate1 A {@link BiPredicate} that accepts the current node and its parent
 * @param predicate2 A {@link BiPredicate} that accepts the current node and its parent
 */
private static boolean lowerThan(TreePath path,BiPredicate<Tree,Tree> predicate1,BiPredicate<Tree,Tree> predicate2){
  int index1=-1;
  int index2=-1;
  int count=0;
  path=path.getParentPath();
  while (path != null) {
    Tree curr=path.getLeaf();
    TreePath parentPath=path.getParentPath();
    if (index1 < 0 && predicate1.test(curr,parentPath == null ? null : parentPath.getLeaf())) {
      index1=count;
    }
    if (index2 < 0 && predicate2.test(curr,parentPath == null ? null : parentPath.getLeaf())) {
      index2=count;
    }
    if (index1 >= 0 && index2 >= 0) {
      break;
    }
    path=parentPath;
    count++;
  }
  return (index1 >= 0) && (index1 < index2);
}
