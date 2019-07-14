@Override protected void bezierVertexCheck(){
  if (shape == 0 || shape != POLYGON) {
    throw new RuntimeException("beginShape() or beginShape(POLYGON) " + "must be used before bezierVertex() or quadraticVertex()");
  }
  if (workPath.getNumCommands() == 0) {
    throw new RuntimeException("vertex() must be used at least once " + "before bezierVertex() or quadraticVertex()");
  }
}
