public PSystemDefinition init(String startLine){
  if (getDiagramType() == DiagramType.DEFINITION) {
    return new PSystemDefinition(startLine);
  }
  return null;
}
