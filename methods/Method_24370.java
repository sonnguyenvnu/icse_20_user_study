public float[] getTessellation(int kind,int data){
  updateTessellation();
  if (kind == TRIANGLES) {
    if (data == POSITION) {
      if (is3D()) {
        root.setModifiedPolyVertices(firstPolyVertex,lastPolyVertex);
      }
 else       if (is2D()) {
        int last1=lastPolyVertex + 1;
        if (-1 < firstLineVertex)         last1=firstLineVertex;
        if (-1 < firstPointVertex)         last1=firstPointVertex;
        root.setModifiedPolyVertices(firstPolyVertex,last1 - 1);
      }
      return tessGeo.polyVertices;
    }
 else     if (data == NORMAL) {
      if (is3D()) {
        root.setModifiedPolyNormals(firstPolyVertex,lastPolyVertex);
      }
 else       if (is2D()) {
        int last1=lastPolyVertex + 1;
        if (-1 < firstLineVertex)         last1=firstLineVertex;
        if (-1 < firstPointVertex)         last1=firstPointVertex;
        root.setModifiedPolyNormals(firstPolyVertex,last1 - 1);
      }
      return tessGeo.polyNormals;
    }
 else     if (data == TEXCOORD) {
      if (is3D()) {
        root.setModifiedPolyTexCoords(firstPolyVertex,lastPolyVertex);
      }
 else       if (is2D()) {
        int last1=lastPolyVertex + 1;
        if (-1 < firstLineVertex)         last1=firstLineVertex;
        if (-1 < firstPointVertex)         last1=firstPointVertex;
        root.setModifiedPolyTexCoords(firstPolyVertex,last1 - 1);
      }
      return tessGeo.polyTexCoords;
    }
  }
 else   if (kind == LINES) {
    if (data == POSITION) {
      if (is3D()) {
        root.setModifiedLineVertices(firstLineVertex,lastLineVertex);
      }
 else       if (is2D()) {
        root.setModifiedPolyVertices(firstLineVertex,lastLineVertex);
      }
      return tessGeo.lineVertices;
    }
 else     if (data == DIRECTION) {
      if (is2D()) {
        root.setModifiedLineAttributes(firstLineVertex,lastLineVertex);
      }
      return tessGeo.lineDirections;
    }
  }
 else   if (kind == POINTS) {
    if (data == POSITION) {
      if (is3D()) {
        root.setModifiedPointVertices(firstPointVertex,lastPointVertex);
      }
 else       if (is2D()) {
        root.setModifiedPolyVertices(firstPointVertex,lastPointVertex);
      }
      return tessGeo.pointVertices;
    }
 else     if (data == OFFSET) {
      if (is2D()) {
        root.setModifiedPointAttributes(firstPointVertex,lastPointVertex);
      }
      return tessGeo.pointOffsets;
    }
  }
  return null;
}
