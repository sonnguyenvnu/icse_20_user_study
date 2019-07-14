static public Class<? extends AbstractOperation> resolveOperationId(String op){
  if (!op.contains("/")) {
    op="core/" + op;
  }
  List<Class<? extends AbstractOperation>> classes=s_opNameToClass.get(op);
  if (classes != null && classes.size() > 0) {
    return classes.get(classes.size() - 1);
  }
  return null;
}
