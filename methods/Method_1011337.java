public void execute(SNode node){
  SNode val=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x4bb51009d20c8e1aL,"jetbrains.mps.lang.quotation.structure.NodeBuilderInitLink"));
  SLinkOperations.setTarget(val,MetaAdapterFactory.getReferenceLink(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x4bb51009d20c8e1aL,0x4bb51009d20c8e1cL,"link"),((SNode)addLinkValue_QuickFix.this.getField("link")[0]));
  ListSequence.fromList(SLinkOperations.getChildren(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x4bb51009d20a4aa0L,"jetbrains.mps.lang.quotation.structure.NodeBuilderNode")),MetaAdapterFactory.getContainmentLink(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x4bb51009d20a4aa0L,0x4bb51009d20b033bL,"values"))).addElement(val);
}
