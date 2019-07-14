public PVector getNormal(int index,PVector vec){
  if (vec == null) {
    vec=new PVector();
  }
  vec.x=vertices[index][PGraphics.NX];
  vec.y=vertices[index][PGraphics.NY];
  vec.z=vertices[index][PGraphics.NZ];
  return vec;
}
