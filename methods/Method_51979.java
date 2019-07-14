@Override public Object visit(ASTAssertStatement node,Object data){
  int base=((MutableInt)data).getValue();
  super.visit(node,data);
  boolean isAssertAware=base < ((MutableInt)data).getValue();
  if (isAssertAware) {
    int boolCompAssert=CycloMetric.booleanExpressionComplexity(node.getGuardExpressionNode());
    ((MutableInt)data).add(boolCompAssert);
  }
  return data;
}
