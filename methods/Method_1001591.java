public PSystemDitaa init(String startLine){
  boolean performSeparationOfCommonEdges=true;
  if (startLine != null && (startLine.contains("-E") || startLine.contains("--no-separation"))) {
    performSeparationOfCommonEdges=false;
  }
  boolean dropShadows=true;
  if (startLine != null && (startLine.contains("-S") || startLine.contains("--no-shadows"))) {
    dropShadows=false;
  }
  final float scale=extractScale(startLine);
  if (getDiagramType() == DiagramType.UML) {
    return null;
  }
 else   if (getDiagramType() == DiagramType.DITAA) {
    return new PSystemDitaa("",performSeparationOfCommonEdges,dropShadows,scale);
  }
 else {
    throw new IllegalStateException(getDiagramType().name());
  }
}
