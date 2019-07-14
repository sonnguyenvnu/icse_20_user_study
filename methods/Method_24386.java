protected void setLastStrokeVertex(int vert){
  if (-1 < lastLineVertex) {
    lastLineVertex=vert;
  }
  if (-1 < lastPointVertex) {
    lastPointVertex+=vert;
  }
}
