public void prepend(TLAExpr expr,int spaces) throws TLAExprException {
  int i=0;
  while (i < expr.tokens.size() - 1) {
    this.tokens.add(i,expr.tokens.elementAt(i));
    i=i + 1;
  }
  ;
  Vector exprLine=(Vector)expr.tokens.elementAt(i);
  Vector thisLine=(Vector)this.tokens.elementAt(i);
  TLAToken tok=(TLAToken)exprLine.lastElement();
  int incr=tok.column + tok.getWidth() + spaces;
  int j=0;
  while (j < thisLine.size()) {
    tok=(TLAToken)thisLine.elementAt(j);
    tok.column=tok.column + incr;
    j=j + 1;
  }
  ;
  j=0;
  while (j < exprLine.size()) {
    thisLine.add(j,exprLine.elementAt(j));
    j=j + 1;
  }
  ;
  TLAToken[] newAToks=new TLAToken[this.tokens.size()];
  int[] newATCol=new int[this.tokens.size()];
  j=0;
  while (j < expr.tokens.size()) {
    newAToks[j]=expr.anchorTokens[j];
    newATCol[j]=expr.anchorTokCol[j];
    j=j + 1;
  }
  ;
  while (j < this.tokens.size()) {
    newAToks[j]=this.anchorTokens[j - expr.tokens.size() + 1];
    newATCol[j]=this.anchorTokCol[j - expr.tokens.size() + 1];
    j=j + 1;
  }
  ;
  this.anchorTokens=newAToks;
  this.anchorTokCol=newATCol;
  this.renormalize();
  return;
}
