public void execute(SNode node){
  SNode variableDeclaration=SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37a7f6eL,"jetbrains.mps.baseLanguage.structure.VariableDeclaration"));
  SNode varStmd=SNodeOperations.insertPrevSiblingChild(ListSequence.fromList(SLinkOperations.getChildren(SLinkOperations.getTarget(((SNode)ConvertFieldToLocalVariable_QuickFix.this.getField("method")[0]),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1ffL,"body")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b200L,0xf8cc6bf961L,"statement"))).first(),SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc67c7f0L,"jetbrains.mps.baseLanguage.structure.LocalVariableDeclarationStatement")));
  final SNode var=SLinkOperations.setTarget(varStmd,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc67c7f0L,0xf8cc67c7f1L,"localVariableDeclaration"),SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc67c7efL,"jetbrains.mps.baseLanguage.structure.LocalVariableDeclaration")));
  SLinkOperations.setTarget(var,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x450368d90ce15bc3L,0x4ed4d318133c80ceL,"type"),SLinkOperations.getTarget(variableDeclaration,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x450368d90ce15bc3L,0x4ed4d318133c80ceL,"type")));
  SPropertyOperations.assign(var,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name"),SPropertyOperations.getString(variableDeclaration,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")));
  SLinkOperations.setTarget(var,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37a7f6eL,0xf8c37f506eL,"initializer"),SLinkOperations.getTarget(variableDeclaration,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37a7f6eL,0xf8c37f506eL,"initializer")));
  if ((SLinkOperations.getTarget(var,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37a7f6eL,0xf8c37f506eL,"initializer")) == null) && SPropertyOperations.getBoolean(variableDeclaration,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37a7f6eL,0x111f9e9f00cL,"isFinal"))) {
    if (Sequence.fromIterable(((Iterable<SNode>)ConvertFieldToLocalVariable_QuickFix.this.getField("alls")[0])).count() == 1 && SNodeOperations.hasRole(Sequence.fromIterable(((Iterable<SNode>)ConvertFieldToLocalVariable_QuickFix.this.getField("alls")[0])).first(),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11b0d00332cL,0xf8c77f1e97L,"lValue"))) {
      SPropertyOperations.assign(var,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37a7f6eL,0x111f9e9f00cL,"isFinal"),true);
      SNode assignment=SNodeOperations.cast(SNodeOperations.getParent(Sequence.fromIterable(((Iterable<SNode>)ConvertFieldToLocalVariable_QuickFix.this.getField("alls")[0])).first()),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11b0d00332cL,"jetbrains.mps.baseLanguage.structure.BaseAssignmentExpression"));
      SLinkOperations.setTarget(var,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37a7f6eL,0xf8c37f506eL,"initializer"),SLinkOperations.getTarget(assignment,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11b0d00332cL,0xf8c77f1e99L,"rValue")));
      SNodeOperations.deleteNode(SNodeOperations.getNodeAncestor(assignment,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b215L,"jetbrains.mps.baseLanguage.structure.Statement"),false,false));
      SNodeOperations.deleteNode(variableDeclaration);
      return;
    }
  }
 else {
    SPropertyOperations.assign(var,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37a7f6eL,0x111f9e9f00cL,"isFinal"),SPropertyOperations.getBoolean(variableDeclaration,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37a7f6eL,0x111f9e9f00cL,"isFinal")));
  }
  Sequence.fromIterable(((Iterable<SNode>)ConvertFieldToLocalVariable_QuickFix.this.getField("alls")[0])).visitAll(new IVisitor<SNode>(){
    public void visit(    SNode it){
      SNode ref=SNodeOperations.replaceWithNewChild(it,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c77f1e98L,"jetbrains.mps.baseLanguage.structure.VariableReference"));
      SLinkOperations.setTarget(ref,MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c77f1e98L,0xf8cc6bf960L,"variableDeclaration"),var);
    }
  }
);
  SNodeOperations.deleteNode(node);
}
