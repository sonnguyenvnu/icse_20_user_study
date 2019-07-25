@Override public void execute(TContext context,TMemory memory) throws EaterException {
  skipSpaces();
  checkAndEatChar("!assert");
  skipSpaces();
  final TValue value=eatExpressionStopAtColon(context,memory);
  skipSpaces();
  if (value.toBoolean() == false) {
    final char ch=peekChar();
    if (ch == ':') {
      checkAndEatChar(':');
      final TValue message=eatExpression(context,memory);
      throw new EaterException("Assertion error : " + message.toString());
    }
    throw new EaterException("Assertion error");
  }
}
