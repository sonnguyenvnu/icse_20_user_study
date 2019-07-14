@Override public int getVertexCount(){
  if (family == GROUP)   return 0;
 else {
    if (family == PRIMITIVE || family == PATH) {
      updateTessellation();
    }
    return inGeo.vertexCount;
  }
}
