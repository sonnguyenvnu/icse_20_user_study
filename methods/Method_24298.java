@Override public float getDepth(){
  PVector min=new PVector(Float.POSITIVE_INFINITY,Float.POSITIVE_INFINITY,Float.POSITIVE_INFINITY);
  PVector max=new PVector(Float.NEGATIVE_INFINITY,Float.NEGATIVE_INFINITY,Float.NEGATIVE_INFINITY);
  if (shapeCreated) {
    getVertexMin(min);
    getVertexMax(max);
  }
  depth=max.z - min.z;
  return depth;
}
