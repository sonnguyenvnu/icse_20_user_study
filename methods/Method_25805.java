private void reportAnyViolations(List<? extends ExpressionTree> arguments,List<RequiredType> requiredArgumentTypes,VisitorState state){
  Types types=state.getTypes();
  for (int i=0; i < requiredArgumentTypes.size(); i++) {
    RequiredType requiredType=requiredArgumentTypes.get(i);
    if (requiredType == null) {
      continue;
    }
    ExpressionTree argument=arguments.get(i);
    Type argType=ASTHelpers.getType(argument);
    if (requiredType.type() != null) {
      EqualsIncompatibleType.TypeCompatibilityReport report=EqualsIncompatibleType.compatibilityOfTypes(requiredType.type(),argType,state);
      if (!report.compatible()) {
        state.reportMatch(describeViolation(argument,argType,requiredType.type(),types));
      }
    }
  }
}
