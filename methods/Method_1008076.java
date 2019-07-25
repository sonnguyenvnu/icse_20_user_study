@Override void analyze(Locals locals){
  final Type type;
  try {
    type=locals.getDefinition().getType(this.type);
  }
 catch (  IllegalArgumentException exception) {
    throw createError(new IllegalArgumentException("Not a type [" + this.type + "]."));
  }
  if (expression != null) {
    expression.expected=type;
    expression.analyze(locals);
    expression=expression.cast(locals);
  }
  variable=locals.addVariable(location,type,name,false);
}
