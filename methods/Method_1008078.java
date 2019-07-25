@Override void analyze(Locals locals){
  expression.expected=locals.getReturnType();
  expression.internal=true;
  expression.analyze(locals);
  expression=expression.cast(locals);
  methodEscape=true;
  loopEscape=true;
  allEscape=true;
  statementCount=1;
}
