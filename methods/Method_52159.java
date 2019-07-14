@Override String kindDisplayName(ASTVariableDeclaratorId node,PropertyDescriptor<Pattern> descriptor){
  if (node.isLambdaParameter()) {
    return node.isTypeInferred() ? "lambda parameter" : "explicitly-typed lambda parameter";
  }
 else   if (node.isFormalParameter()) {
    return node.isFinal() ? "final method parameter" : "method parameter";
  }
  throw new UnsupportedOperationException("This rule doesn't handle this case");
}
