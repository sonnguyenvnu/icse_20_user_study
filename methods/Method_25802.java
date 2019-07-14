private String valueArgumentFromCompatibleWithAnnotation(AnnotationTree tree){
  ExpressionTree argumentValue=Iterables.getOnlyElement(tree.getArguments());
  if (argumentValue.getKind() != Kind.ASSIGNMENT) {
    return null;
  }
  return ASTHelpers.constValue(((AssignmentTree)argumentValue).getExpression(),String.class);
}
