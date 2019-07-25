public void record(SNode inputNode,String mappingLabel,Collection<SNode> outputNodes){
  SNodeId origin=getInputOrigin(inputNode);
  if (origin == null) {
    return;
  }
  myMemento.addOutputNodeByInputNodeAndMappingName(origin,mappingLabel,outputNodes);
}
