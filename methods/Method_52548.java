@Override public JavaTypeDefinition withDimensions(int numDimensions){
  if (numDimensions < 0) {
    throw new IllegalArgumentException("Negative array dimension");
  }
  return numDimensions == 0 ? this : forClass(Array.newInstance(getType(),(int[])Array.newInstance(int.class,numDimensions)).getClass());
}
