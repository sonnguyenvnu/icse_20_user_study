private boolean isBooleanType(ASTType node){
  return "boolean".equalsIgnoreCase(node.getTypeImage()) || TypeHelper.isA(node,"java.util.concurrent.atomic.AtomicBoolean");
}
