@Override public String format(String fieldText){
  if (fieldText == null) {
    return fieldText;
  }
  String latexCommandFree=removeLatexCommands(fieldText);
  String formattedFieldText=firstFormat(latexCommandFree);
  for (  Map.Entry<String,String> entry : XML_CHARS.entrySet()) {
    String s=entry.getKey();
    String repl=entry.getValue();
    if (repl != null) {
      formattedFieldText=formattedFieldText.replaceAll(s,repl);
    }
  }
  return restFormat(formattedFieldText);
}
