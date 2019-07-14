protected void aggregate(){
  if (root == this && parent == null) {
    polyIndexOffset=0;
    polyVertexOffset=0;
    polyVertexAbs=0;
    polyVertexRel=0;
    lineIndexOffset=0;
    lineVertexOffset=0;
    lineVertexAbs=0;
    lineVertexRel=0;
    pointIndexOffset=0;
    pointVertexOffset=0;
    pointVertexAbs=0;
    pointVertexRel=0;
    aggregateImpl();
  }
}
