@Override void analyze(Locals locals){
  expression.analyze(locals);
  expression.expected=expression.actual;
  expression=expression.cast(locals);
  final Type type;
  try {
    type=locals.getDefinition().getType(this.type);
  }
 catch (  IllegalArgumentException exception) {
    throw createError(new IllegalArgumentException("Not a type [" + this.type + "]."));
  }
  locals=Locals.newLocalScope(locals);
  Variable variable=locals.addVariable(location,type,name,true);
  if (expression.actual.dimensions > 0) {
    sub=new SSubEachArray(location,variable,expression,block);
  }
 else   if (expression.actual.dynamic || Iterable.class.isAssignableFrom(expression.actual.clazz)) {
    sub=new SSubEachIterable(location,variable,expression,block);
  }
 else {
    throw createError(new IllegalArgumentException("Illegal for each type [" + expression.actual.name + "]."));
  }
  sub.analyze(locals);
  if (block == null) {
    throw createError(new IllegalArgumentException("Extraneous for each loop."));
  }
  block.beginLoop=true;
  block.inLoop=true;
  block.analyze(locals);
  block.statementCount=Math.max(1,block.statementCount);
  if (block.loopEscape && !block.anyContinue) {
    throw createError(new IllegalArgumentException("Extraneous for loop."));
  }
  statementCount=1;
  if (locals.hasVariable(Locals.LOOP)) {
    sub.loopCounter=locals.getVariable(location,Locals.LOOP);
  }
}
