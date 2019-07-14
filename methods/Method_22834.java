protected boolean doKeyword(MarkerState ms,Segment line,int i,char c){
  int i1=i + 1;
  int len=i - ms.lastKeyword;
  boolean paren=Editor.checkParen(line.array,i,line.array.length);
  byte id=keywordColoring.lookup(line,ms.lastKeyword,len,paren);
  if (id != Token.NULL) {
    if (ms.lastKeyword != ms.lastOffset) {
      addToken(ms.lastKeyword - ms.lastOffset,Token.NULL);
    }
    addToken(len,id);
    ms.lastOffset=i;
  }
  ms.lastKeyword=i1;
  return false;
}
