@Nullable private static Tree suppressibleNode(TreePath path){
  return StreamSupport.stream(path.spliterator(),false).filter(tree -> tree instanceof MethodTree || (tree instanceof ClassTree && ((ClassTree)tree).getSimpleName().length() != 0) || tree instanceof VariableTree).findFirst().orElse(null);
}
