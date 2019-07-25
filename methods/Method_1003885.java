@Override public void verify(Number number,Annotation annotation){
  checkArgument(number.doubleValue() > 0,"Value must be positive.");
}
