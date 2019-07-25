@Override void analyze(Locals locals){
  if (!read) {
    throw createError(new IllegalArgumentException("Must read from constant [" + constant + "]."));
  }
  actual=locals.getDefinition().StringType;
}
