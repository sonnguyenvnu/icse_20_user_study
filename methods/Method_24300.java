protected void getVertexMax(PVector max){
  updateTessellation();
  if (family == GROUP) {
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      child.getVertexMax(max);
    }
  }
 else {
    if (hasPolys) {
      tessGeo.getPolyVertexMax(max,firstPolyVertex,lastPolyVertex);
    }
    if (is3D()) {
      if (hasLines) {
        tessGeo.getLineVertexMax(max,firstLineVertex,lastLineVertex);
      }
      if (hasPoints) {
        tessGeo.getPointVertexMax(max,firstPointVertex,lastPointVertex);
      }
    }
  }
}
