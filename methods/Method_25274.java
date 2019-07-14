private static String formatMessage(String checkName,JavaFileObject file,DiagnosticPosition pos,Throwable cause){
  DiagnosticSource source=new DiagnosticSource(file,null);
  int column=source.getColumnNumber(pos.getStartPosition(),true);
  int line=source.getLineNumber(pos.getStartPosition());
  String snippet=source.getLine(pos.getStartPosition());
  StringBuilder sb=new StringBuilder();
  sb.append(String.format("\n%s:%d: %s: An exception was thrown by Error Prone: %s\n",source.getFile().getName(),line,checkName,cause.getMessage()));
  sb.append(snippet).append('\n');
  if (column > 0) {
    sb.append(Strings.repeat(" ",column - 1));
  }
  sb.append("^\n");
  return sb.toString();
}
