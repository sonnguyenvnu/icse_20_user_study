public void renormalize() throws TLAExprException {
  int i=0;
  while (i < tokens.size()) {
    if (anchorTokens[i] != null) {
      Vector line=(Vector)tokens.elementAt(i);
      int k=anchorTokens[i].column - anchorTokCol[i];
      anchorTokCol[i]=anchorTokens[i].column;
      if (k < 0) {
        throw new TLAExprException("TLAExpr.renormalize() found anchor has moved to left.");
      }
      ;
      int j=0;
      while (j < line.size()) {
        TLAToken tok=(TLAToken)line.elementAt(j);
        tok.column=tok.column + k;
        j=j + 1;
      }
      ;
    }
    ;
    i=i + 1;
  }
}
