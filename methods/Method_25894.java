/** 
 * Returns the method that 'directly' contains the leaf element of the given path. <p>By 'direct', we mean that if the leaf is part of a field initializer of a class, then it is considered to not be part of any method.
 */
private static MethodTree findDirectMethod(TreePath path){
  while (true) {
    path=path.getParentPath();
    if (path != null) {
      Tree leaf=path.getLeaf();
      if (leaf instanceof MethodTree) {
        return (MethodTree)leaf;
      }
      if (leaf instanceof ClassTree) {
        return null;
      }
    }
 else {
      return null;
    }
  }
}
