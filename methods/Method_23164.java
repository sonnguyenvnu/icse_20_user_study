@Override public PMatrix2D getMatrix(PMatrix2D target){
  if (target == null) {
    target=new PMatrix2D();
  }
  g2.getTransform().getMatrix(transform);
  target.set((float)transform[0],(float)transform[2],(float)transform[4],(float)transform[1],(float)transform[3],(float)transform[5]);
  return target;
}
