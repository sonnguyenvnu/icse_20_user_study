public void first(SNode sl){
  ListSequence.fromList(SLinkOperations.getChildren(sl,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b200L,0xf8cc6bf961L,"statement"))).first();
  Iterable<SNode> nodes=ListSequence.fromList(SLinkOperations.getChildren(sl,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b200L,0xf8cc6bf961L,"statement"))).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return SNodeOperations.isInstanceOf(it,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b217L,"jetbrains.mps.baseLanguage.structure.IfStatement"));
    }
  }
);
}
