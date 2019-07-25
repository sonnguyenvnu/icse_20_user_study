public final TBPar append(LiveExprNode ln1,LiveExprNode ln2){
  TBPar res=new TBPar(this.size() + 2);
  for (int i=0; i < this.size(); i++) {
    res.addElement(this.exprAt(i));
  }
  res.addElement(ln1);
  res.addElement(ln2);
  return res;
}
