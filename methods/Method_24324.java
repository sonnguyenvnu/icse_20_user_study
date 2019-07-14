protected void applyMatrixImpl(PMatrix matrix){
  if (hasPolys) {
    tessGeo.applyMatrixOnPolyGeometry(matrix,firstPolyVertex,lastPolyVertex);
    root.setModifiedPolyVertices(firstPolyVertex,lastPolyVertex);
    root.setModifiedPolyNormals(firstPolyVertex,lastPolyVertex);
    for (    VertexAttribute attrib : polyAttribs.values()) {
      if (attrib.isPosition() || attrib.isNormal()) {
        root.setModifiedPolyAttrib(attrib,firstPolyVertex,lastPolyVertex);
      }
    }
  }
  if (is3D()) {
    if (hasLines) {
      tessGeo.applyMatrixOnLineGeometry(matrix,firstLineVertex,lastLineVertex);
      root.setModifiedLineVertices(firstLineVertex,lastLineVertex);
      root.setModifiedLineAttributes(firstLineVertex,lastLineVertex);
    }
    if (hasPoints) {
      tessGeo.applyMatrixOnPointGeometry(matrix,firstPointVertex,lastPointVertex);
      root.setModifiedPointVertices(firstPointVertex,lastPointVertex);
      root.setModifiedPointAttributes(firstPointVertex,lastPointVertex);
    }
  }
}
