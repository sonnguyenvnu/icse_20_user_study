private void makeViolations(RuleContext ctx,Set<DataPoint> p){
  for (  DataPoint point : p) {
    rule.addViolationWithMessage(ctx,point.getNode(),point.getMessage(),((StatisticalRule)rule).getViolationParameters(point));
  }
}
