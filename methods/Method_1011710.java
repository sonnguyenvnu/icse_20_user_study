@NotNull @Override public UpdateResult update(EditorComponent component,boolean incremental,boolean applyQuickFix,Cancellable cancellable){
  SNode editedNode=component.getEditedNode();
  List<EditorMessage> messages=new ArrayList<EditorMessage>(4);
  for (  SNode n : SNodeOperations.getNodeDescendants(editedNode,null,true,new SAbstractConcept[]{})) {
    if (cancellable.isCancelled()) {
      return UpdateResult.CANCELLED;
    }
    if (SNodeOperations.isInstanceOf(n,SNodeOperations.asSConcept(myJavadocComment))) {
      SNode p=SNodeOperations.as(n,SNodeOperations.asSConcept(myJavadocComment));
      spellCheck(SPropertyOperations.getString(p,MetaAdapterFactory.getProperty(0xf280165065d5424eL,0xbb1b463a8781b786L,0x7c7f5b2f31990287L,0x7c7f5b2f31990288L,"text")),n,messages);
    }
 else     if (SNodeOperations.isInstanceOf(n,SNodeOperations.asSConcept(mySingleLineCmment))) {
      SNode p=SNodeOperations.as(n,SNodeOperations.asSConcept(mySingleLineCmment));
      spellCheck(SPropertyOperations.getString(p,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x57d533a7af15ed3dL,0x57d533a7af15ed3eL,"text")),n,messages);
    }
 else     if (SNodeOperations.isInstanceOf(n,SNodeOperations.asSConcept(word))) {
      SNode w=SNodeOperations.as(n,SNodeOperations.asSConcept(word));
      spellCheck(SPropertyOperations.getString(w,MetaAdapterFactory.getProperty(0xc7fb639fbe784307L,0x89b0b5959c3fa8c8L,0x229012ddae35f04L,0x229012ddae35f05L,"value")),n,messages);
    }
 else     if (SNodeOperations.isInstanceOf(n,SNodeOperations.asSConcept(myStringLiteral))) {
      SNode l=SNodeOperations.as(n,SNodeOperations.asSConcept(myStringLiteral));
      spellCheck(SPropertyOperations.getString(l,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf93d565d10L,0xf93d565d11L,"value")),n,messages);
    }
  }
  return new UpdateResult.Completed(true,messages);
}
