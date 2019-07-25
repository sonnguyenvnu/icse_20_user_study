@Override void analyze(Locals locals){
  if (constant instanceof String) {
    actual=locals.getDefinition().StringType;
  }
 else   if (constant instanceof Double) {
    actual=locals.getDefinition().doubleType;
  }
 else   if (constant instanceof Float) {
    actual=locals.getDefinition().floatType;
  }
 else   if (constant instanceof Long) {
    actual=locals.getDefinition().longType;
  }
 else   if (constant instanceof Integer) {
    actual=locals.getDefinition().intType;
  }
 else   if (constant instanceof Character) {
    actual=locals.getDefinition().charType;
  }
 else   if (constant instanceof Short) {
    actual=locals.getDefinition().shortType;
  }
 else   if (constant instanceof Byte) {
    actual=locals.getDefinition().byteType;
  }
 else   if (constant instanceof Boolean) {
    actual=locals.getDefinition().booleanType;
  }
 else {
    throw createError(new IllegalStateException("Illegal tree structure."));
  }
}
