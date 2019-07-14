/** 
 * @param vec PVector to assign the data to
 */
public PVector getVertex(int index,PVector vec){
  if (vec == null) {
    vec=new PVector();
  }
  float[] vert=vertices[index];
  vec.x=vert[X];
  vec.y=vert[Y];
  if (vert.length > 2) {
    vec.z=vert[Z];
  }
 else {
    vec.z=0;
  }
  return vec;
}
