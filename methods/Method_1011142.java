public void execute(SNode node){
  ListSequence.fromList(SLinkOperations.getChildren(((SNode)RemoveDefaultModifier_QuickFix.this.getField("member")[0]),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x203eeb62af522fa5L,0x203eeb62af522fb1L,"modifiers"))).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return SNodeOperations.isInstanceOf(it,MetaAdapterFactory.getConcept(0xfdcdc48fbfd84831L,0xaa765abac2ffa010L,0x40ed0df0ef40a332L,"jetbrains.mps.baseLanguage.jdk8.structure.DefaultModifier"));
    }
  }
).visitAll(new IVisitor<SNode>(){
    public void visit(    SNode it){
      SNodeOperations.deleteNode(it);
    }
  }
);
}
