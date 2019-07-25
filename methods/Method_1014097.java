@Override public String interpret(Locale locale,String text) throws InterpretationException {
  ResourceBundle language=ResourceBundle.getBundle(LANGUAGE_SUPPORT,locale);
  Rule[] rules=getRules(locale);
  if (language == null || rules.length == 0) {
    throw new InterpretationException(locale.getDisplayLanguage(Locale.ENGLISH) + " is not supported at the moment.");
  }
  TokenList tokens=new TokenList(tokenize(locale,text));
  if (tokens.eof()) {
    throw new InterpretationException(language.getString(SORRY));
  }
  InterpretationResult result;
  InterpretationResult lastResult=null;
  for (  Rule rule : rules) {
    if ((result=rule.execute(language,tokens)).isSuccess()) {
      return result.getResponse();
    }
 else {
      if (result != InterpretationResult.SYNTAX_ERROR) {
        lastResult=result;
      }
    }
  }
  if (lastResult == null) {
    throw new InterpretationException(language.getString(SORRY));
  }
 else {
    throw lastResult.getException();
  }
}
