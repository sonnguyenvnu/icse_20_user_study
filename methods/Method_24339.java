@Override public PVector getNormal(int index,PVector vec){
  if (vec == null) {
    vec=new PVector();
  }
  vec.x=inGeo.normals[3 * index + 0];
  vec.y=inGeo.normals[3 * index + 1];
  vec.z=inGeo.normals[3 * index + 2];
  return vec;
}
