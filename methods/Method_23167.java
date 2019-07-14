@Override public float screenX(float x,float y){
  g2.getTransform().getMatrix(transform);
  return (float)transform[0] * x + (float)transform[2] * y + (float)transform[4];
}
