public void append(String source){
  checkState(!finished);
  String indentedSource=source.replace(LINE_SEPARATOR,LINE_SEPARATOR + Strings.repeat(INDENT,currentIndentation));
  sb.append(indentedSource);
  currentLine+=CharMatcher.is(LINE_SEPARATOR_CHAR).countIn(indentedSource);
  currentColumn=sb.length() - sb.lastIndexOf(LINE_SEPARATOR) - 1;
}
