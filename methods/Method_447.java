public void setContext(ParseContext context){
  if (lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
    return;
  }
  this.context=context;
}
