@Override void analyze(Locals locals){
  if ("length".equals(value)) {
    if (write) {
      throw createError(new IllegalArgumentException("Cannot write to read-only field [length] for an array."));
    }
    actual=locals.getDefinition().intType;
  }
 else {
    throw createError(new IllegalArgumentException("Field [" + value + "] does not exist for type [" + type + "]."));
  }
}
