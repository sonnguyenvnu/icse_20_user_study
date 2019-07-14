protected void getVertexMin(PVector min){
  updateTessellation();
  if (family == GROUP) {
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      child.getVertexMin(min);
    }
  }
 else {
    if (hasPolys) {
      tessGeo.getPolyVertexMin(min,firstPolyVertex,lastPolyVertex);
    }
    if (is3D()) {
      if (hasLines) {
        tessGeo.getLineVertexMin(min,firstLineVertex,lastLineVertex);
      }
      if (hasPoints) {
        tessGeo.getPointVertexMin(min,firstPointVertex,lastPointVertex);
      }
    }
  }
}
