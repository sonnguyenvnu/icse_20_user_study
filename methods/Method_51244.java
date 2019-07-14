protected String expandVariables(String message){
  if (message.indexOf("${") < 0) {
    return message;
  }
  StringBuilder buf=new StringBuilder(message);
  int startIndex=-1;
  while ((startIndex=buf.indexOf("${",startIndex + 1)) >= 0) {
    final int endIndex=buf.indexOf("}",startIndex);
    if (endIndex >= 0) {
      final String name=buf.substring(startIndex + 2,endIndex);
      if (isVariable(name)) {
        buf.replace(startIndex,endIndex + 1,getVariableValue(name));
      }
    }
  }
  return buf.toString();
}
