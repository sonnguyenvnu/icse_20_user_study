@Override void analyze(Locals locals){
  throw createError(new IllegalStateException("Illegal tree structure."));
}
