public PSystemCute init(String startLine){
  if (getDiagramType() == DiagramType.CUTE) {
    return new PSystemCute();
  }
  return null;
}
