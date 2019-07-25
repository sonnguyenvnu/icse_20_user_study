public final boolean eval(ITool tool,TLCState s1,TLCState s2){
  if (this.isBox && this.subscript != null) {
    return true;
  }
  TLCState sfun=TLCStateFun.Empty;
  Context c1=Context.branch(getContext());
  if (this.subscript != null) {
    sfun=tool.enabled(this.pred,c1,s1,sfun,this.subscript,IActionItemList.CHANGED);
  }
 else {
    sfun=tool.enabled(this.pred,c1,s1,sfun);
  }
  return sfun != null;
}
