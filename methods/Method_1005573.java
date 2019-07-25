private Pattern compile(String regex){
  if (regex != null) {
    try {
      return Pattern.compile(regex);
    }
 catch (    PatternSyntaxException e) {
      Debugger.fatal(e);
    }
  }
  return null;
}
