public PSystemSalt init(String startLine){
  if (getDiagramType() == DiagramType.UML) {
    return null;
  }
 else   if (getDiagramType() == DiagramType.SALT) {
    return new PSystemSalt();
  }
 else {
    throw new IllegalStateException(getDiagramType().name());
  }
}
