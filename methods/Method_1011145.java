public static void renew(final SNode node,SNode descriptor){
  Sequence.fromIterable(SNodeOperations.ofConcept(SLinkOperations.getChildren(node,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,0x4a9a46de59132803L,"member")),MetaAdapterFactory.getConcept(0xc7d5b9dda05f4be2L,0xbc73f2e16994cc67L,0x59e9926e840d7db2L,"jetbrains.mps.baseLanguage.lightweightdsl.structure.MemberPlaceholder"))).toListSequence().visitAll(new IVisitor<SNode>(){
    public void visit(    SNode it){
      SNodeOperations.deleteNode(it);
    }
  }
);
  final SNode first=ListSequence.fromList(SLinkOperations.getChildren(node,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,0x4a9a46de59132803L,"member"))).first();
  Sequence.fromIterable(DSLDescriptor__BehaviorDescriptor.getClassLikeMembers_id2iCqkkxuhoj.invoke(descriptor)).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return !(SNodeOperations.isInstanceOf(it,MetaAdapterFactory.getConcept(0xc7d5b9dda05f4be2L,0xbc73f2e16994cc67L,0x50c63f9f4a0dea5fL,"jetbrains.mps.baseLanguage.lightweightdsl.structure.EmptyMemberDescriptor")));
    }
  }
).visitAll(new IVisitor<SNode>(){
    public void visit(    SNode it){
      SNode newMember=DSLClassMember__BehaviorDescriptor.createForClass_id5BD$AU437jJ.invoke(it,node,SNodeOperations.getModel(node));
      if ((newMember == null)) {
        return;
      }
      if ((first == null)) {
        ListSequence.fromList(SLinkOperations.getChildren(node,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,0x4a9a46de59132803L,"member"))).addElement(newMember);
      }
 else {
        SNodeOperations.insertPrevSiblingChild(first,newMember);
      }
    }
  }
);
}
