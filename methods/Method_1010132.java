public void record(SNode inputNode,String mappingLabel,SNode outputNode){
  SNodeId origin=getInputOrigin(inputNode);
  if (origin == null) {
    return;
  }
  myMemento.addOutputNodeByInputNodeAndMappingName(origin,mappingLabel,outputNode);
}
