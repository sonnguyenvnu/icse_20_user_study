private static String stripIndentation(String description){
  if (description == null || description.isEmpty()) {
    return "";
  }
  String stripped=StringUtils.stripStart(description,"\n\r");
  stripped=StringUtils.stripEnd(stripped,"\n\r ");
  int indentation=0;
  int strLen=stripped.length();
  while (Character.isWhitespace(stripped.charAt(indentation)) && indentation < strLen) {
    indentation++;
  }
  String[] lines=stripped.split("\\n");
  String prefix=StringUtils.repeat(' ',indentation);
  StringBuilder result=new StringBuilder(stripped.length());
  if (StringUtils.isNotEmpty(prefix)) {
    for (int i=0; i < lines.length; i++) {
      String line=lines[i];
      if (i > 0) {
        result.append(StringUtils.LF);
      }
      result.append(StringUtils.removeStart(line,prefix));
    }
  }
 else {
    result.append(stripped);
  }
  return result.toString();
}
