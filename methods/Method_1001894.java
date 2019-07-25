@Override public void execute(TContext context,TMemory memory) throws EaterException {
  skipSpaces();
  checkAndEatChar("!undef");
  skipSpaces();
  final String varname=eatAndGetVarname();
  memory.removeVariable(varname);
}
