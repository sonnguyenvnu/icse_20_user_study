public void execute(SNode node){
  final SNode item=SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xa7d67633e8d9473bL,0x98ce995a7aa66941L,0x4f786d85fe288176L,"jetbrains.mps.samples.heating.structure.Slot"));
  if (SPropertyOperations.getInteger(item,MetaAdapterFactory.getProperty(0xa7d67633e8d9473bL,0x98ce995a7aa66941L,0x4f786d85fe288176L,0x4f786d85fe28827cL,"start")) < 0) {
    return;
  }
  SNode dailyPlan=SNodeOperations.cast(SNodeOperations.getParent(item),MetaAdapterFactory.getConcept(0xa7d67633e8d9473bL,0x98ce995a7aa66941L,0x4644aa4ce08aec4fL,"jetbrains.mps.samples.heating.structure.DailyPlan"));
  SNode nextSibling=ListSequence.fromList(SLinkOperations.getChildren(dailyPlan,MetaAdapterFactory.getContainmentLink(0xa7d67633e8d9473bL,0x98ce995a7aa66941L,0x4644aa4ce08aec4fL,0x4644aa4ce08aec57L,"items"))).findFirst(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return SPropertyOperations.getInteger(it,MetaAdapterFactory.getProperty(0xa7d67633e8d9473bL,0x98ce995a7aa66941L,0x4f786d85fe288176L,0x4f786d85fe28827cL,"start")) > SPropertyOperations.getInteger(item,MetaAdapterFactory.getProperty(0xa7d67633e8d9473bL,0x98ce995a7aa66941L,0x4f786d85fe288176L,0x4f786d85fe28827cL,"start"));
    }
  }
);
  if ((nextSibling != null)) {
    SNodeOperations.insertPrevSiblingChild(nextSibling,item);
  }
}
