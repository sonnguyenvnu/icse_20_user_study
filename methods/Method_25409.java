/** 
 * Matches if this Tree is enclosed by either a synchronized block or a synchronized method. 
 */
public static <T extends Tree>Matcher<T> inSynchronized(){
  return (tree,state) -> {
    SynchronizedTree synchronizedTree=ASTHelpers.findEnclosingNode(state.getPath(),SynchronizedTree.class);
    if (synchronizedTree != null) {
      return true;
    }
    MethodTree methodTree=ASTHelpers.findEnclosingNode(state.getPath(),MethodTree.class);
    return methodTree != null && methodTree.getModifiers().getFlags().contains(Modifier.SYNCHRONIZED);
  }
;
}
