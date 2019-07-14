static public LanguageSpecificParser createParser(){
  return new LanguageSpecificParser(){
    @Override public Evaluable parse(    String s) throws ParsingException {
      return new JythonEvaluable(s);
    }
  }
;
}
