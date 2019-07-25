@Override public String interpret(String text,String hliId) throws InterpretationException {
  HumanLanguageInterpreter interpreter;
  if (hliId == null) {
    interpreter=getHLI();
    if (interpreter == null) {
      throw new InterpretationException("No human language interpreter available!");
    }
  }
 else {
    interpreter=getHLI(hliId);
    if (interpreter == null) {
      throw new InterpretationException("No human language interpreter can be found for " + hliId);
    }
  }
  return interpreter.interpret(localeProvider.getLocale(),text);
}
