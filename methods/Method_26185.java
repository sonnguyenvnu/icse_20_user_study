@Nullable private VariableTree findDeclaration(VisitorState state,Symbol parameter){
  JavacProcessingEnvironment javacEnv=JavacProcessingEnvironment.instance(state.context);
  TreePath declPath=Trees.instance(javacEnv).getPath(parameter);
  if (declPath != null && declPath.getCompilationUnit() == state.getPath().getCompilationUnit() && (declPath.getLeaf() instanceof VariableTree)) {
    return (VariableTree)declPath.getLeaf();
  }
  return null;
}
