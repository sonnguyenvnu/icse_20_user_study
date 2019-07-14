private void addViolations(List<MethodCall> calls,RuleContext ctx){
  for (  MethodCall method : calls) {
    if (method.isViolation()) {
      addViolationWithMessage(ctx,method.getExpression(),getMessage() + " (" + method.getViolationReason() + ")");
    }
  }
}
