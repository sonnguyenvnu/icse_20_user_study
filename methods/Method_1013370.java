public final TBPar union(TBPar par){
  TBPar res=new TBPar(this.size() + par.size());
  for (int i=0; i < this.size(); i++) {
    if (!par.member(this.exprAt(i))) {
      res.addElement(this.exprAt(i));
    }
  }
  for (int i=0; i < par.size(); i++) {
    res.addElement(par.exprAt(i));
  }
  return res;
}
