public void popContext(){
  if (lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
    return;
  }
  this.context=this.context.parent;
  if (contextArrayIndex <= 0) {
    return;
  }
  contextArrayIndex--;
  contextArray[contextArrayIndex]=null;
}
