@Override public boolean contains(float x,float y){
  if (family == PATH) {
    boolean c=false;
    for (int i=0, j=inGeo.vertexCount - 1; i < inGeo.vertexCount; j=i++) {
      if (((inGeo.vertices[3 * i + 1] > y) != (inGeo.vertices[3 * j + 1] > y)) && (x < (inGeo.vertices[3 * j] - inGeo.vertices[3 * i]) * (y - inGeo.vertices[3 * i + 1]) / (inGeo.vertices[3 * j + 1] - inGeo.vertices[3 * i + 1]) + inGeo.vertices[3 * i])) {
        c=!c;
      }
    }
    return c;
  }
 else {
    throw new IllegalArgumentException("The contains() method is only implemented for paths.");
  }
}
