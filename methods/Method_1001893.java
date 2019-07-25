@Override public void execute(TContext context,TMemory memory) throws EaterException {
  skipSpaces();
  checkAndEatChar("!startsub");
  skipSpaces();
  this.subname=eatAllToEnd();
  if (this.subname.matches("\\w+") == false) {
    throw new EaterException("Bad sub name");
  }
}
