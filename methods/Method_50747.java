private ASTMethod resolveMethodCalls(final ASTMethodCallExpression node){
  StringBuilder sb=new StringBuilder().append(node.getNode().getDefiningType().getApexName()).append(":").append(node.getNode().getMethodName()).append(":").append(node.getNode().getInputParameters().size());
  return classMethods.get(sb.toString());
}
