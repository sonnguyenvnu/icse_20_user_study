@Override protected Iterable<? extends AnnotationTree> getChildNodes(T tree,VisitorState state){
  if (tree instanceof ClassTree) {
    return ((ClassTree)tree).getModifiers().getAnnotations();
  }
 else   if (tree instanceof VariableTree) {
    return ((VariableTree)tree).getModifiers().getAnnotations();
  }
 else   if (tree instanceof MethodTree) {
    return ((MethodTree)tree).getModifiers().getAnnotations();
  }
 else   if (tree instanceof CompilationUnitTree) {
    return ((CompilationUnitTree)tree).getPackageAnnotations();
  }
 else   if (tree instanceof AnnotatedTypeTree) {
    return ((AnnotatedTypeTree)tree).getAnnotations();
  }
 else   if (tree instanceof PackageTree) {
    return ((PackageTree)tree).getAnnotations();
  }
 else {
    throw new IllegalArgumentException("Cannot access annotations from tree of type " + tree.getClass());
  }
}
