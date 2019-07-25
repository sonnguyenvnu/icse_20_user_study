public void normalize(){
  while ((tokens.size() > 0) && (((Vector)tokens.elementAt(0)).size() == 0)) {
    tokens.removeElementAt(0);
  }
  ;
  while ((tokens.size() > 0) && (((Vector)tokens.elementAt(tokens.size() - 1)).size() == 0)) {
    tokens.removeElementAt(tokens.size() - 1);
  }
  ;
  int minCol=999999;
  int i=0;
  while (i < tokens.size()) {
    if (((Vector)tokens.elementAt(i)).size() > 0) {
      TLAToken tok=(TLAToken)((Vector)tokens.elementAt(i)).elementAt(0);
      if (tok.column < minCol) {
        minCol=tok.column;
      }
      ;
    }
    i=i + 1;
  }
  ;
  i=0;
  while (i < tokens.size()) {
    int j=0;
    Vector curLine=(Vector)tokens.elementAt(i);
    while (j < curLine.size()) {
      TLAToken tok=(TLAToken)curLine.elementAt(j);
      tok.column=tok.column - minCol;
      j=j + 1;
    }
    ;
    i=i + 1;
  }
  ;
  anchorTokens=new TLAToken[tokens.size()];
  anchorTokCol=new int[tokens.size()];
  i=0;
  while (i < tokens.size()) {
    Vector curLine=(Vector)tokens.elementAt(i);
    if (curLine.size() > 0) {
      int curLineFirstCol=((TLAToken)curLine.elementAt(0)).column;
      int j=i - 1;
      boolean lineNotFound=true;
      while ((j >= 0) && lineNotFound) {
        Vector ancLine=(Vector)tokens.elementAt(j);
        if ((ancLine.size() > 0) && (((TLAToken)ancLine.elementAt(0)).column <= curLineFirstCol)) {
          lineNotFound=false;
          int k=0;
          while ((k + 1 < ancLine.size()) && (((TLAToken)ancLine.elementAt(k + 1)).column <= curLineFirstCol)) {
            k=k + 1;
          }
          ;
          TLAToken tok=(TLAToken)ancLine.elementAt(k);
          anchorTokens[i]=tok;
          anchorTokCol[i]=tok.column;
        }
        ;
        j=j - 1;
      }
      ;
    }
    ;
    i=i + 1;
  }
  ;
}
