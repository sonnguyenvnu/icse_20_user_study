@Override String kindDisplayName(ASTVariableDeclaratorId node,PropertyDescriptor<Pattern> descriptor){
  if (node.isExceptionBlockParameter()) {
    return "exception block parameter";
  }
 else   if (node.isLocalVariable()) {
    return node.isFinal() ? "final local variable" : "local variable";
  }
  throw new UnsupportedOperationException("This rule doesn't handle this case");
}
