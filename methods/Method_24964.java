static private List<Range> getCommentBlocks(String code){
  List<Range> commentBlocks=new ArrayList<>();
  int lastBlockStart=0;
  boolean lookForEnd=false;
  for (int pos=0; pos < code.length() - 1; pos++) {
    if (lookForEnd) {
      if (code.charAt(pos) == '*' && code.charAt(pos + 1) == '/') {
        commentBlocks.add(new Range(lastBlockStart,pos + 1));
        lookForEnd=false;
      }
    }
 else {
      if (code.charAt(pos) == '/' && code.charAt(pos + 1) == '*') {
        lastBlockStart=pos;
        lookForEnd=true;
      }
 else       if (code.charAt(pos) == '/' && code.charAt(pos + 1) == '/') {
        commentBlocks.add(new Range(pos,getEndOfLine(pos,code)));
      }
    }
  }
  return commentBlocks;
}
