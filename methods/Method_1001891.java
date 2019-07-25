@Override public void execute(TContext context,TMemory memory) throws EaterException {
  skipSpaces();
  checkAndEatChar("!if");
  skipSpaces();
  final TValue value=eatExpression(context,memory);
  this.booleanValue=value.toBoolean();
}
