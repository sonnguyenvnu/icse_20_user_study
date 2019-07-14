private static Optional<ExpressionTree> findArgument(AnnotationTree annotation,String parameter){
  for (  ExpressionTree argument : annotation.getArguments()) {
    if (argument.getKind().equals(ASSIGNMENT)) {
      AssignmentTree assignment=(AssignmentTree)argument;
      if (assignment.getVariable().toString().equals(parameter)) {
        return Optional.of(ASTHelpers.stripParentheses(assignment.getExpression()));
      }
    }
  }
  return Optional.empty();
}
