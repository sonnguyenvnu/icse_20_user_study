@Override public float screenY(float x,float y){
  g2.getTransform().getMatrix(transform);
  return (float)transform[1] * x + (float)transform[3] * y + (float)transform[5];
}
