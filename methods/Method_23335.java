protected void bezierVertexCheck(int shape,int vertexCount){
  if (shape == 0 || shape != POLYGON) {
    throw new RuntimeException("beginShape() or beginShape(POLYGON) " + "must be used before bezierVertex() or quadraticVertex()");
  }
  if (vertexCount == 0) {
    throw new RuntimeException("vertex() must be used at least once " + "before bezierVertex() or quadraticVertex()");
  }
}
