static boolean foundAnySOQLorSOSL(final ApexNode<?> node){
  final List<ASTSoqlExpression> dmlSoqlExpression=node.findDescendantsOfType(ASTSoqlExpression.class);
  final List<ASTSoslExpression> dmlSoslExpression=node.findDescendantsOfType(ASTSoslExpression.class);
  return !dmlSoqlExpression.isEmpty() || !dmlSoslExpression.isEmpty();
}
