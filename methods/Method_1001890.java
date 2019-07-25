@Override public void execute(TContext context,TMemory memory) throws EaterException {
  skipUntilChar('(');
  checkAndEatChar('(');
  skipSpaces();
  if (peekChar() == ')') {
    checkAndEatChar(')');
    return;
  }
  while (true) {
    skipSpaces();
    if (isLegacyDefine || unquoted) {
      final String tmp=eatAndGetOptionalQuotedString();
      final String tmp2=context.applyFunctionsAndVariables(memory,tmp);
      final TValue result=TValue.fromString(tmp2);
      values.add(result);
    }
 else {
      final TokenStack tokens=TokenStack.eatUntilCloseParenthesisOrComma(this).withoutSpace();
      tokens.guessFunctions();
      final TValue result=tokens.getResult(context,memory);
      values.add(result);
    }
    skipSpaces();
    final char ch=eatOneChar();
    if (ch == ',') {
      continue;
    }
    if (ch == ')') {
      break;
    }
    throw new EaterException("call001");
  }
}
