private void postprocess(){
  for (  SNode node : ListSequence.fromList(SNodeOperations.getNodeDescendants(myWhatToEvaluate,null,false,new SAbstractConcept[]{}))) {
    if ((AttributeOperations.getAttribute(node,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0x7da4580f9d754603L,0x816251a896d78375L,0x50b810dd5c871ea2L,"jetbrains.mps.debugger.java.evaluation.structure.UnprocessedAnnotation"))) != null)) {
      SNodeOperations.deleteNode(AttributeOperations.getAttribute(node,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0x7da4580f9d754603L,0x816251a896d78375L,0x50b810dd5c871ea2L,"jetbrains.mps.debugger.java.evaluation.structure.UnprocessedAnnotation"))));
    }
  }
  for (  SNode node : ListSequence.fromList(SNodeOperations.getNodeDescendants(myWhatToEvaluate,null,false,new SAbstractConcept[]{}))) {
    node.putUserObject(LTYPE,null);
    node.putUserObject(RTYPE,null);
    node.putUserObject(CTYPE,null);
  }
  for (  SNode var : ListSequence.fromList(SNodeOperations.getNodeDescendants(SNodeOperations.getContainingRoot(myWhatToEvaluate),MetaAdapterFactory.getConcept(0x7da4580f9d754603L,0x816251a896d78375L,0x53c5060c6b18d926L,"jetbrains.mps.debugger.java.evaluation.structure.LowLevelVariable"),false,new SAbstractConcept[]{}))) {
    SNodeOperations.deleteNode(var);
  }
}
