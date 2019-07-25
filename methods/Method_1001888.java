@Override public void execute(TContext context,TMemory memory) throws EaterException {
  skipSpaces();
  checkAndEatChar("!");
  skipSpaces();
  String varname=eatAndGetVarname();
  TVariableScope scope=null;
  skipSpaces();
  if (peekChar() != '=') {
    scope=TVariableScope.valueOf(varname.toUpperCase());
    varname=eatAndGetVarname();
    skipSpaces();
  }
  checkAndEatChar('=');
  skipSpaces();
  final TValue value=eatExpression(context,memory);
  memory.putVariable(varname,new TVariable(value),scope);
}
