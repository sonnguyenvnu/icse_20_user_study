@Override public float getWidth(){
  PVector min=new PVector(Float.POSITIVE_INFINITY,Float.POSITIVE_INFINITY,Float.POSITIVE_INFINITY);
  PVector max=new PVector(Float.NEGATIVE_INFINITY,Float.NEGATIVE_INFINITY,Float.NEGATIVE_INFINITY);
  if (shapeCreated) {
    getVertexMin(min);
    getVertexMax(max);
  }
  width=max.x - min.x;
  return width;
}
