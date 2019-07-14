@Override public PVector getVertex(int index,PVector vec){
  if (vec == null) {
    vec=new PVector();
  }
  vec.x=inGeo.vertices[3 * index + 0];
  vec.y=inGeo.vertices[3 * index + 1];
  vec.z=inGeo.vertices[3 * index + 2];
  return vec;
}
