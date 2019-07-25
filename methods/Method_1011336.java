public SNode convert(){
  SNode quotedNode=SLinkOperations.getTarget(quotation,MetaAdapterFactory.getContainmentLink(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x1168c104659L,0x1168c10465aL,"quotedNode"));
  if (quotedNode == null) {
    return null;
  }
  if ((AttributeOperations.getAttribute(quotedNode,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x1168c104658L,"jetbrains.mps.lang.quotation.structure.Antiquotation"))) != null)) {
    return null;
  }
  if ((AttributeOperations.getAttribute(quotedNode,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x1168c10465eL,"jetbrains.mps.lang.quotation.structure.ListAntiquotation"))) != null)) {
    return null;
  }
  SNode node=convertNode(quotedNode);
  if (!(SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x4bb51009d20a4aa0L,"jetbrains.mps.lang.quotation.structure.NodeBuilderNode")))) {
    return null;
  }
  SNode q=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x4bb51009d20a4a9dL,"jetbrains.mps.lang.quotation.structure.NodeBuilder"));
  SLinkOperations.setTarget(q,MetaAdapterFactory.getContainmentLink(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x4bb51009d20a4a9dL,0x4bb51009d20a4a9eL,"quotedNode"),SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x4bb51009d20a4aa0L,"jetbrains.mps.lang.quotation.structure.NodeBuilderNode")));
  SLinkOperations.setTarget(q,MetaAdapterFactory.getContainmentLink(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x4bb51009d20a4a9dL,0x4bb51009d20a4a9fL,"modelToCreate"),SLinkOperations.getTarget(quotation,MetaAdapterFactory.getContainmentLink(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x1168c104659L,0x1168c10465bL,"modelToCreate")));
  return q;
}
