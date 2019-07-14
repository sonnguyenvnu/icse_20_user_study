private static boolean isAnonymousClassTree(Tree t){
  if (t instanceof ClassTree) {
    ClassTree classTree=(ClassTree)t;
    return classTree.getSimpleName().contentEquals("");
  }
  return false;
}
