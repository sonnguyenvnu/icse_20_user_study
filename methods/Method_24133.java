static protected void invRotate(PMatrix3D matrix,float angle,float v0,float v1,float v2){
  float c=PApplet.cos(-angle);
  float s=PApplet.sin(-angle);
  float t=1.0f - c;
  matrix.preApply((t * v0 * v0) + c,(t * v0 * v1) - (s * v2),(t * v0 * v2) + (s * v1),0,(t * v0 * v1) + (s * v2),(t * v1 * v1) + c,(t * v1 * v2) - (s * v0),0,(t * v0 * v2) - (s * v1),(t * v1 * v2) + (s * v0),(t * v2 * v2) + c,0,0,0,0,1);
}
