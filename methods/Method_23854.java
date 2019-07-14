@Override public void beginShape(int kind){
  shape=kind;
  vertexCount=0;
  curveVertexCount=0;
  workPath.reset();
  auxPath.reset();
  flushPixels();
  if (drawingThinLines()) {
    adjustedForThinLines=true;
    translate(0.5f,0.5f);
  }
}
