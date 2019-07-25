public final IActionItemList cons(SemanticNode pred,Context con,CostModel cm,int kind){
  return new ActionItemList(pred,con,kind,this,coverage ? cm.get(pred) : cm);
}
