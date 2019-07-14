private boolean hasTooManyLines(Comment comment){
  String[] lines=comment.getImage().split(CR);
  int start=0;
  for (; start < lines.length; start++) {
    if (hasRealText(lines[start])) {
      break;
    }
  }
  int end=lines.length - 1;
  for (; end > 0; end--) {
    if (hasRealText(lines[end])) {
      break;
    }
  }
  int lineCount=end - start + 1;
  return lineCount > getProperty(MAX_LINES);
}
