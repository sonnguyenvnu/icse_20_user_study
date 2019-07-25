@Override void analyze(Locals locals){
  try {
    actual=locals.getDefinition().getType(type);
  }
 catch (  IllegalArgumentException exception) {
    throw createError(new IllegalArgumentException("Not a type [" + type + "]."));
  }
}
