/** 
 * lexer
 * @return a token or null if file ends
 */
@Nullable private Node nextToken(){
  boolean seenComment=true;
  while (seenComment) {
    seenComment=false;
    while (offset < text.length() && Character.isWhitespace(text.charAt(offset))) {
      forward();
    }
    if (offset + Constants.LINE_COMMENT.length() <= text.length() && text.substring(offset,offset + Constants.LINE_COMMENT.length()).equals(Constants.LINE_COMMENT)) {
      while (offset < text.length() && text.charAt(offset) != '\n') {
        forward();
      }
      if (offset < text.length()) {
        forward();
      }
      seenComment=true;
    }
  }
  if (offset >= text.length()) {
    return null;
  }
  char cur=text.charAt(offset);
  if (isDelimiter(cur)) {
    Node ret=new Delimeter(Character.toString(cur),file,offset,offset + 1,line,col);
    forward();
    return ret;
  }
  if (text.charAt(offset) == '"' && (offset == 0 || text.charAt(offset - 1) != '\\')) {
    int start=offset;
    int startLine=line;
    int startCol=col;
    forward();
    while (offset < text.length() && !(text.charAt(offset) == '"' && text.charAt(offset - 1) != '\\')) {
      if (text.charAt(offset) == '\n') {
        _.abort(file + ":" + startLine + ":" + startCol + ": runaway string");
        return null;
      }
      forward();
    }
    if (offset >= text.length()) {
      _.abort(file + ":" + startLine + ":" + startCol + ": runaway string");
      return null;
    }
    forward();
    int end=offset;
    String content=text.substring(start + 1,end - 1);
    return new Str(content,file,start,end,startLine,startCol);
  }
  int start=offset;
  int startLine=line;
  int startCol=col;
  if (Character.isDigit(text.charAt(start)) || ((text.charAt(start) == '+' || text.charAt(start) == '-') && Character.isDigit(text.charAt(start + 1)))) {
    while (offset < text.length() && !Character.isWhitespace(cur) && !(isDelimiter(cur) && cur != '.')) {
      forward();
      if (offset < text.length()) {
        cur=text.charAt(offset);
      }
    }
    String content=text.substring(start,offset);
    IntNum intNum=IntNum.parse(content,file,start,offset,startLine,startCol);
    if (intNum != null) {
      return intNum;
    }
 else {
      FloatNum floatNum=FloatNum.parse(content,file,start,offset,startLine,startCol);
      if (floatNum != null) {
        return floatNum;
      }
 else {
        _.abort(file + ":" + startLine + ":" + startCol + " : incorrect number format: " + content);
        return null;
      }
    }
  }
 else {
    while (offset < text.length() && !Character.isWhitespace(cur) && !isDelimiter(cur)) {
      forward();
      if (offset < text.length()) {
        cur=text.charAt(offset);
      }
    }
    String content=text.substring(start,offset);
    if (content.matches(":\\w.*")) {
      return new Keyword(content.substring(1),file,start,offset,startLine,startCol);
    }
 else {
      return new Name(content,file,start,offset,startLine,startCol);
    }
  }
}
