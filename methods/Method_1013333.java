@Override public final TLCState enabled(SemanticNode pred,Context c,TLCState s0,TLCState s1,ExprNode subscript,final int ail){
  ActionItemList acts=(ActionItemList)ActionItemList.Empty.cons(subscript,c,CostModel.DO_NOT_RECORD,ail);
  return enabled(pred,acts,c,s0,s1,CostModel.DO_NOT_RECORD);
}
