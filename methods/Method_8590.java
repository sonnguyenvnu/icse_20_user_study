protected float getXForTime(float time){
  float x=time;
  float z;
  for (int i=1; i < 14; i++) {
    z=getBezierCoordinateX(x) - time;
    if (Math.abs(z) < 1e-3) {
      break;
    }
    x-=z / getXDerivate(x);
  }
  return x;
}
