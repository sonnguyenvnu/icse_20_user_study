public void rotate(float angle,float v0,float v1,float v2){
  float norm2=v0 * v0 + v1 * v1 + v2 * v2;
  if (norm2 < PConstants.EPSILON) {
    return;
  }
  if (Math.abs(norm2 - 1) > PConstants.EPSILON) {
    float norm=PApplet.sqrt(norm2);
    v0/=norm;
    v1/=norm;
    v2/=norm;
  }
  float c=cos(angle);
  float s=sin(angle);
  float t=1.0f - c;
  apply((t * v0 * v0) + c,(t * v0 * v1) - (s * v2),(t * v0 * v2) + (s * v1),0,(t * v0 * v1) + (s * v2),(t * v1 * v1) + c,(t * v1 * v2) - (s * v0),0,(t * v0 * v2) - (s * v1),(t * v1 * v2) + (s * v0),(t * v2 * v2) + c,0,0,0,0,1);
}
