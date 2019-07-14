@Override public void popMatrix(){
  if (transformCount == 0) {
    throw new RuntimeException("missing a pushMatrix() " + "to go with that popMatrix()");
  }
  transformCount--;
  context.setTransform(transformStack[transformCount]);
}
