protected int getVertexSum(PVector sum,int count){
  updateTessellation();
  if (family == GROUP) {
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      count+=child.getVertexSum(sum,count);
    }
  }
 else {
    if (hasPolys) {
      count+=tessGeo.getPolyVertexSum(sum,firstPolyVertex,lastPolyVertex);
    }
    if (is3D()) {
      if (hasLines) {
        count+=tessGeo.getLineVertexSum(sum,firstLineVertex,lastLineVertex);
      }
      if (hasPoints) {
        count+=tessGeo.getPointVertexSum(sum,firstPointVertex,lastPointVertex);
      }
    }
  }
  return count;
}
