@Override void analyze(Locals locals){
  if (!read) {
    throw createError(new IllegalArgumentException("Must read from null constant."));
  }
  isNull=true;
  if (expected != null) {
    if (expected.clazz.isPrimitive()) {
      throw createError(new IllegalArgumentException("Cannot cast null to a primitive type [" + expected.name + "]."));
    }
    actual=expected;
  }
 else {
    actual=locals.getDefinition().ObjectType;
  }
}
