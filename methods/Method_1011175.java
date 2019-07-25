public void migrate(){
  Iterable<SNode> oldComments=Sequence.fromIterable(((Iterable<SModel>)module.getModels())).translate(new ITranslator2<SModel,SNode>(){
    public Iterable<SNode> translate(    SModel it){
      return it.getRootNodes();
    }
  }
).translate(new ITranslator2<SNode,SNode>(){
    public Iterable<SNode> translate(    SNode it){
      return SNodeOperations.getNodeDescendants(((SNode)it),SNodeOperations.asSConcept(concept),false,new SAbstractConcept[]{});
    }
  }
);
  for (  SNode comment : Sequence.fromIterable(oldComments)) {
    Iterable<SNode> commentedNodes=IOldCommentContainer__BehaviorDescriptor.getCommentedNodes_id3$Sh7m_tmZE.invoke(SNodeOperations.cast(comment,MetaAdapterFactory.getInterfaceConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x39384475a5756fb0L,"jetbrains.mps.lang.core.structure.IOldCommentContainer")));
    if (Sequence.fromIterable(commentedNodes).isNotEmpty()) {
      SNode next=comment;
      for (      SNode commentedNode : Sequence.fromIterable(commentedNodes)) {
        SNodeOperations.insertNextSiblingChild(next,commentedNode);
        next=commentedNode;
      }
      for (      SNode commentedNode : Sequence.fromIterable(commentedNodes)) {
        CommentUtil.commentOut(commentedNode);
      }
    }
    SNodeOperations.deleteNode(comment);
  }
}
