public void execute(SNode node){
  SNode repeat=SNodeOperations.replaceWithNewChild(node,MetaAdapterFactory.getConcept(0x49a08c51fe543ccL,0xbd998b46d641d7f5L,0x2de971c785ecd14cL,"jetbrains.mps.samples.Kaja.structure.Repeat"));
  SLinkOperations.setTarget(repeat,MetaAdapterFactory.getContainmentLink(0x49a08c51fe543ccL,0xbd998b46d641d7f5L,0x2de971c785ecd14cL,0x2de971c785ecd14fL,"body"),SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0x49a08c51fe543ccL,0xbd998b46d641d7f5L,0x2de971c785f06a3fL,"jetbrains.mps.samples.Kaja.structure.CommandList")));
  ListSequence.fromList(SLinkOperations.getChildren(SLinkOperations.getTarget(repeat,MetaAdapterFactory.getContainmentLink(0x49a08c51fe543ccL,0xbd998b46d641d7f5L,0x2de971c785ecd14cL,0x2de971c785ecd14fL,"body")),MetaAdapterFactory.getContainmentLink(0x49a08c51fe543ccL,0xbd998b46d641d7f5L,0x2de971c785f06a3fL,0x2de971c785f06a40L,"commands"))).addElement(SNodeOperations.as(node,MetaAdapterFactory.getConcept(0x49a08c51fe543ccL,0xbd998b46d641d7f5L,0x2d523c5e4cc4574aL,"jetbrains.mps.samples.Kaja.structure.AbstractCommand")));
  SPropertyOperations.assign(repeat,MetaAdapterFactory.getProperty(0x49a08c51fe543ccL,0xbd998b46d641d7f5L,0x2de971c785ecd14cL,0x2de971c785ecd14eL,"count"),2);
  SNodeOperations.deleteNode(SNodeOperations.getPrevSibling(repeat));
}
