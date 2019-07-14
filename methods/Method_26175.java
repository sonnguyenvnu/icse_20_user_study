@Nullable private VariableTree findDeclaration(VisitorState state,Symbol field){
  JavacProcessingEnvironment javacEnv=JavacProcessingEnvironment.instance(state.context);
  TreePath fieldDeclPath=Trees.instance(javacEnv).getPath(field);
  if (fieldDeclPath != null && fieldDeclPath.getCompilationUnit() == state.getPath().getCompilationUnit() && (fieldDeclPath.getLeaf() instanceof VariableTree)) {
    return (VariableTree)fieldDeclPath.getLeaf();
  }
  return null;
}
