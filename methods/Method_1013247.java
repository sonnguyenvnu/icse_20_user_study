public final Integer put(SymbolNode param,int position,int level){
  ParamAndPosition pap=new ParamAndPosition(param,position);
  return this.put(pap,new Integer(level));
}
