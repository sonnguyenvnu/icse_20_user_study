protected void aggregateImpl(){
  if (family == GROUP) {
    hasPolys=false;
    hasLines=false;
    hasPoints=false;
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      child.aggregateImpl();
      hasPolys|=child.hasPolys;
      hasLines|=child.hasLines;
      hasPoints|=child.hasPoints;
    }
  }
 else {
    hasPolys=-1 < firstPolyIndexCache && -1 < lastPolyIndexCache;
    hasLines=-1 < firstLineIndexCache && -1 < lastLineIndexCache;
    hasPoints=-1 < firstPointIndexCache && -1 < lastPointIndexCache;
  }
  if (hasPolys) {
    updatePolyIndexCache();
  }
  if (is3D()) {
    if (hasLines)     updateLineIndexCache();
    if (hasPoints)     updatePointIndexCache();
  }
  if (matrix != null) {
    if (hasPolys) {
      tessGeo.applyMatrixOnPolyGeometry(matrix,firstPolyVertex,lastPolyVertex);
    }
    if (is3D()) {
      if (hasLines) {
        tessGeo.applyMatrixOnLineGeometry(matrix,firstLineVertex,lastLineVertex);
      }
      if (hasPoints) {
        tessGeo.applyMatrixOnPointGeometry(matrix,firstPointVertex,lastPointVertex);
      }
    }
  }
}
