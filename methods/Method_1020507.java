private static String extract(SourcePosition sourcePosition,List<String> lines,boolean condense){
  int startLine=sourcePosition.getStartFilePosition().getLine();
  int endLine=sourcePosition.getEndFilePosition().getLine();
  String fragment=lines.get(startLine);
  int endColumn=sourcePosition.getEndFilePosition().getColumn();
  int startColumn=sourcePosition.getStartFilePosition().getColumn();
  if (endLine != startLine || endColumn == -1) {
    StringBuilder content=new StringBuilder(trimTrailingWhitespace(fragment.substring(startColumn)));
    if (condense && startLine + 3 < endLine) {
      content.append("\n").append(trimTrailingWhitespace(lines.get(startLine + 1))).append("\n...").append(trimTrailingWhitespace(lines.get(endLine - 1)));
    }
 else {
      for (int line=startLine + 1; line < endLine; line++) {
        content.append("\n").append(trimTrailingWhitespace(lines.get(line)));
      }
    }
    content.append("\n").append((trimTrailingWhitespace(lines.get(endLine).substring(0,endColumn))));
    return "[" + content.toString() + "]";
  }
  return "[" + fragment.substring(startColumn,endColumn) + "]";
}
