private Optional<Nullness> getInferredNullness(MethodInvocationNode node){
  if (inferenceResults == null) {
    TreePath pathToNode=node.getTreePath();
    Tree procedureTree=enclosingOfClass(pathToNode,LambdaExpressionTree.class);
    if (procedureTree == null) {
      procedureTree=enclosingOfClass(pathToNode,MethodTree.class);
    }
    if (procedureTree == null) {
      procedureTree=enclosingOfClass(pathToNode,BlockTree.class);
    }
    if (procedureTree == null) {
      procedureTree=enclosingOfClass(pathToNode,VariableTree.class);
    }
    inferenceResults=NullnessQualifierInference.getInferredNullability(checkNotNull(procedureTree,"Call `%s` is not contained in an lambda, initializer or method.",node));
  }
  return inferenceResults.getExprNullness(node.getTree());
}
