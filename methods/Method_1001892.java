@Override public void execute(TContext context,TMemory memory) throws EaterException {
  skipSpaces();
  checkAndEatChar("!return");
  skipSpaces();
  this.value=eatExpression(context,memory);
}
