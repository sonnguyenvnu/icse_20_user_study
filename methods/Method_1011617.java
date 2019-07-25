public static Iterable<Scope> imported(Iterable<SNode> importDeclarations,final SAbstractConcept concept,final SNode child){
  return Sequence.fromIterable(importDeclarations).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return ListSequence.fromList(SNodeOperations.getReferences(it)).count() == 1 && SNodeOperations.isInstanceOf(SLinkOperations.getTargetNode(ListSequence.fromList(SNodeOperations.getReferences(it)).first()),MetaAdapterFactory.getInterfaceConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x33d23ee961a0cbf3L,"jetbrains.mps.lang.core.structure.ScopeProvider"));
    }
  }
).select(new ISelector<SNode,SNode>(){
    public SNode select(    SNode it){
      return SNodeOperations.cast(SLinkOperations.getTargetNode(ListSequence.fromList(SNodeOperations.getReferences(it)).first()),MetaAdapterFactory.getInterfaceConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x33d23ee961a0cbf3L,"jetbrains.mps.lang.core.structure.ScopeProvider"));
    }
  }
).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return !(ListSequence.fromList(SNodeOperations.getNodeDescendants(it,null,false,new SAbstractConcept[]{})).contains(child));
    }
  }
).select(new ISelector<SNode,Scope>(){
    public Scope select(    SNode it){
      return (Scope)ScopeProvider__BehaviorDescriptor.getScope_id52_Geb4QDV$.invoke(it,concept,child);
    }
  }
);
}
