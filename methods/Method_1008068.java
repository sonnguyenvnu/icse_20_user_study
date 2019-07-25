@Override void analyze(Locals locals){
  variable=locals.getVariable(location,name);
  if (write && variable.readonly) {
    throw createError(new IllegalArgumentException("Variable [" + variable.name + "] is read-only."));
  }
  actual=variable.type;
}
