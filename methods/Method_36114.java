private void captureHelpTextIfRequested(OptionParser optionParser){
  if (optionSet.has(HELP)) {
    StringWriter out=new StringWriter();
    try {
      optionParser.printHelpOn(out);
    }
 catch (    IOException e) {
      throw new RuntimeException(e);
    }
    helpText=out.toString();
  }
}
