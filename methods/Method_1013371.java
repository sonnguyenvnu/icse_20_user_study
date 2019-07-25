public final TBPar append(LiveExprNode ln){
  TBPar res=new TBPar(this.size() + 1);
  for (int i=0; i < this.size(); i++) {
    res.addElement(this.exprAt(i));
  }
  res.addElement(ln);
  return res;
}
